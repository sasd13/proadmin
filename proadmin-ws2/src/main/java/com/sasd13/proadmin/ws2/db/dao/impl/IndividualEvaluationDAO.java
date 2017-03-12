/*
 * To change this license header, choose License Headers in individualEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IIndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dao.IStudentDAO;
import com.sasd13.proadmin.ws2.db.dto.IndividualEvaluationDTO;
import com.sasd13.proadmin.ws2.db.dto.StudentDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class IndividualEvaluationDAO extends AbstractDAO implements IIndividualEvaluationDAO, IConditionnal {

	private IStudentDAO studentDAO;

	public IndividualEvaluationDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public IndividualEvaluationDTO create(IndividualEvaluation individualEvaluation) {
		IndividualEvaluationDTO dto = new IndividualEvaluationDTO(individualEvaluation);

		dto.setStudent(readStudent(individualEvaluation.getStudent().getNumber()));
		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	private StudentDTO readStudent(String studentNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.NUMBER.getName(), new String[] { studentNumber });

		return studentDAO.read(parameters).get(0);
	}

	@Override
	public void update(IIndividualEvaluationUpdateWrapper updateWrapper) {
		IndividualEvaluationDTO dto = read(updateWrapper.getReportNumber(), updateWrapper.getStudentNumber());

		dto.setMark(updateWrapper.getWrapped().getMark());
		currentSession().update(dto);
		currentSession().flush();
	}

	private IndividualEvaluationDTO read(String reportNumber, String studentNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.REPORT.getName(), new String[] { reportNumber });
		parameters.put(EnumParameter.STUDENT.getName(), new String[] { studentNumber });

		return read(parameters).get(0);
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) {
		IndividualEvaluationDTO dto = read(individualEvaluation.getReport().getNumber(), individualEvaluation.getStudent().getNumber());

		currentSession().remove(dto);
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IndividualEvaluationDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from individualevaluations ie");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<IndividualEvaluationDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return "ie.report.code";
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return "ie.student.code";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	protected void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
