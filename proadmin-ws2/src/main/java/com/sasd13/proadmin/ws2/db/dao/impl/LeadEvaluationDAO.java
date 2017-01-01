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
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws2.db.dto.LeadEvaluationDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class LeadEvaluationDAO extends HibernateSession<LeadEvaluation> implements ILeadEvaluationDAO {

	public LeadEvaluationDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public long insert(LeadEvaluation leadEvaluation) {
		LeadEvaluationDTO dto = new LeadEvaluationDTO(leadEvaluation);

		HibernateUtils.insert(this, dto);

		return dto.getId();
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
		builder.append(", " + COLUMN_STUDENT + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		HibernateUtils.delete(this, builder.toString(), leadEvaluation);
	}

	@Override
	public Serializable select(long id) {
		return null;
	}

	@Override
	public List<Serializable> select(Map<String, String[]> parameters) {
		return (List<Serializable>) HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Serializable> selectAll() {
		return (List<Serializable>) HibernateUtils.selectAll(this, TABLE);
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
			return ILeadEvaluationDAO.COLUMN_REPORT;
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return ILeadEvaluationDAO.COLUMN_STUDENT;
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
