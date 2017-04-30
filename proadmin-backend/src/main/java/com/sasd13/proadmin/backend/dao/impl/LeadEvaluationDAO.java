/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.backend.dao.impl;

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
import com.sasd13.proadmin.backend.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.backend.model.LeadEvaluation;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class LeadEvaluationDAO extends AbstractDAO implements ILeadEvaluationDAO, IConditionnal {

	public LeadEvaluationDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public LeadEvaluation create(LeadEvaluation leadEvaluation) {
		currentSession().save(leadEvaluation);
		currentSession().flush();

		return leadEvaluation;
	}

	@Override
	public void update(LeadEvaluation leadEvaluation) {
		currentSession().update(leadEvaluation);
		currentSession().flush();
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		currentSession().remove(leadEvaluation);
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from LeadEvaluation le");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<LeadEvaluation>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return "le.report.number = ?";
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return "le.student.intermediary = ?";
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
