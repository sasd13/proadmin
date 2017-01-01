/*
 * To change this license header, choose License Headers in Project Properties.
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
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class LeadEvaluationDAO extends HibernateSession<LeadEvaluation> implements ILeadEvaluationDAO {

	public LeadEvaluationDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public long insert(LeadEvaluation leadEvaluation) {
		TeacherDTO teacherDTO = new TeacherDTO(teacher);

		HibernateUtils.insert(this, teacherDTO);

		return teacherDTO.getId();
	}

	@Override
	public void update(IUpdateWrapper<LeadEvaluation> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_PLANNINGMARK + " = ?");
		builder.append(", " + COLUMN_PLANNINGCOMMENT + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONMARK + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT + " = ?");
		builder.append(", " + COLUMN_STUDENT_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		HibernateUtils.delete(this, builder.toString(), leadEvaluation);
	}

	@Override
	public LeadEvaluation select(long id) {
		return null;
	}

	@Override
	public List<LeadEvaluation> select(Map<String, String[]> parameters) {
		return HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<LeadEvaluation> selectAll() {
		return HibernateUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(LeadEvaluation leadEvaluation) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<LeadEvaluation> updateWrapper) {
		query.setParameter(1, updateWrapper.getWrapped().getPlanningMark());
		query.setParameter(2, updateWrapper.getWrapped().getPlanningComment());
		query.setParameter(3, updateWrapper.getWrapped().getCommunicationMark());
		query.setParameter(4, updateWrapper.getWrapped().getCommunicationComment());
		query.setParameter(5, updateWrapper.getWrapped().getStudent().getNumber());
		query.setParameter(6, ((ILeadEvaluationUpdateWrapper) updateWrapper).getReportNumber());
		query.setParameter(7, ((ILeadEvaluationUpdateWrapper) updateWrapper).getStudentNumber());
	}

	@Override
	public void editQueryForDelete(Query query, LeadEvaluation leadEvaluation) {
		query.setParameter(1, leadEvaluation.getReport().getNumber());
		query.setParameter(2, leadEvaluation.getStudent().getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return ILeadEvaluationDAO.COLUMN_REPORT_CODE;
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return ILeadEvaluationDAO.COLUMN_STUDENT_CODE;
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

	@Override
	public LeadEvaluation getResultValues(Serializable serializable) {
		LeadEvaluation leadEvaluation = new LeadEvaluation(resultSet.getString(COLUMN_REPORT_CODE), resultSet.getString(COLUMN_STUDENT_CODE));

		leadEvaluation.setPlanningMark(resultSet.getFloat(COLUMN_PLANNINGMARK));
		leadEvaluation.setPlanningComment(resultSet.getString(COLUMN_PLANNINGCOMMENT));
		leadEvaluation.setCommunicationMark(resultSet.getFloat(COLUMN_COMMUNICATIONMARK));
		leadEvaluation.setCommunicationComment(resultSet.getString(COLUMN_COMMUNICATIONCOMMENT));

		return leadEvaluation;
	}
}
