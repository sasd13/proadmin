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

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.order.OrderException;
import com.sasd13.proadmin.backend.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.backend.entity.LeadEvaluation;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
public class LeadEvaluationDAO extends AbstractDAO implements ILeadEvaluationDAO {

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
	public List<LeadEvaluation> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from LeadEvaluation le");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<LeadEvaluation>) query.getResultList();
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.REPORT.getCode().equalsIgnoreCase(key)) {
			return "le.report.number = ?" + index;
		} else if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			return "le.student.intermediary = ?" + index;
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.REPORT.getCode().equalsIgnoreCase(key)) {
			return "le.report.number";
		} else if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			return "le.student.intermediary";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.REPORT.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}
}
