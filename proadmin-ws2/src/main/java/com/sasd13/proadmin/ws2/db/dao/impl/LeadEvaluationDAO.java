/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dao.IStudentDAO;
import com.sasd13.proadmin.ws2.db.dto.LeadEvaluationDTO;
import com.sasd13.proadmin.ws2.db.dto.StudentDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class LeadEvaluationDAO extends AbstractDAO implements ILeadEvaluationDAO, IConditionnal {

	@Autowired
	private IStudentDAO studentDAO;

	public LeadEvaluationDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public LeadEvaluationDTO create(LeadEvaluation leadEvaluation) {
		LeadEvaluationDTO dto = new LeadEvaluationDTO(leadEvaluation);

		dto.setStudent(readStudent(leadEvaluation.getStudent().getNumber()));
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
	public void update(List<LeadEvaluationUpdateWrapper> updateWrappers) {
		LeadEvaluationUpdateWrapper updateWrapper;
		LeadEvaluationDTO dto;

		for (int i = 0; i < updateWrappers.size(); i++) {
			updateWrapper = updateWrappers.get(i);
			dto = read(updateWrapper.getReportNumber());

			dto.setPlanningMark(updateWrapper.getWrapped().getPlanningMark());
			dto.setPlanningComment(updateWrapper.getWrapped().getPlanningComment());
			dto.setCommunicationMark(updateWrapper.getWrapped().getCommunicationMark());
			dto.setCommunicationComment(updateWrapper.getWrapped().getCommunicationComment());
			dto.setStudent(readStudent(updateWrapper.getWrapped().getStudent().getNumber()));
			currentSession().update(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	private LeadEvaluationDTO read(String reportNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.REPORT.getName(), new String[] { reportNumber });

		return read(parameters).get(0);
	}

	@Override
	public void delete(List<LeadEvaluation> leadEvaluations) {
		LeadEvaluation leadEvaluation;
		LeadEvaluationDTO dto;

		for (int i = 0; i < leadEvaluations.size(); i++) {
			leadEvaluation = leadEvaluations.get(i);
			dto = read(leadEvaluation.getReport().getNumber());

			currentSession().remove(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LeadEvaluationDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from leadevaluations le");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<LeadEvaluationDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return "le.report.code";
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return "le.student.code";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
