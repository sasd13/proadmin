/*
 * To change this license header, choose License Headers in individualEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.dao.hibernate.HibernateSession;
import com.sasd13.javaex.dao.hibernate.HibernateUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IIndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class IndividualEvaluationDAO extends HibernateSession<IndividualEvaluation> implements IIndividualEvaluationDAO {

	public IndividualEvaluationDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public long insert(IndividualEvaluation individualEvaluation) {
		TeacherDTO teacherDTO = new TeacherDTO(teacher);

		HibernateUtils.insert(this, teacherDTO);

		return teacherDTO.getId();
	}

	@Override
	public void update(IUpdateWrapper<IndividualEvaluation> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_MARK + " = ?");
		builder.append(", " + COLUMN_STUDENT_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		HibernateUtils.delete(this, builder.toString(), individualEvaluation);
	}

	@Override
	public IndividualEvaluation select(long id) {
		return null;
	}

	@Override
	public List<IndividualEvaluation> select(Map<String, String[]> parameters) {
		return HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<IndividualEvaluation> selectAll() {
		return HibernateUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(IndividualEvaluation individualEvaluation) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<IndividualEvaluation> updateWrapper) {
		query.setParameter(1, updateWrapper.getWrapped().getMark());
		query.setParameter(2, updateWrapper.getWrapped().getStudent().getNumber());
		query.setParameter(3, ((IIndividualEvaluationUpdateWrapper) updateWrapper).getReportNumber());
		query.setParameter(4, ((IIndividualEvaluationUpdateWrapper) updateWrapper).getStudentNumber());
	}

	@Override
	public void editQueryForDelete(Query query, IndividualEvaluation individualEvaluation) {
		query.setParameter(1, individualEvaluation.getReport().getNumber());
		query.setParameter(2, individualEvaluation.getStudent().getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_REPORT_CODE;
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_STUDENT_CODE;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(javax.persistence.Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public IndividualEvaluation getResultValues(Serializable serializable) {
		IndividualEvaluation individualEvaluation = new IndividualEvaluation(resultSet.getString(COLUMN_REPORT_CODE), resultSet.getString(COLUMN_STUDENT_CODE));

		individualEvaluation.setMark(resultSet.getFloat(COLUMN_MARK));

		return individualEvaluation;
	}
}
