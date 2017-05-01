/*
 * To change this license header, choose License Headers in individualEvaluation Properties.
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
import com.sasd13.javaex.util.order.OrderException;
import com.sasd13.proadmin.backend.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.backend.model.IndividualEvaluation;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class IndividualEvaluationDAO extends AbstractDAO implements IIndividualEvaluationDAO {

	public IndividualEvaluationDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<IndividualEvaluation> create(List<IndividualEvaluation> individualEvaluations) {
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			currentSession().save(individualEvaluation);
		}

		currentSession().flush();

		return individualEvaluations;
	}

	@Override
	public void update(List<IndividualEvaluation> individualEvaluations) {
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			currentSession().update(individualEvaluation);
		}

		currentSession().flush();
	}

	@Override
	public void delete(List<IndividualEvaluation> individualEvaluations) {
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			currentSession().remove(individualEvaluation);
		}

		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IndividualEvaluation> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from IndividualEvaluation ie");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<IndividualEvaluation>) query.getResultList();
	}

	@Override
	public String getCondition(String key) {
		if (EnumCriteria.REPORT.getCode().equalsIgnoreCase(key)) {
			return "ie.report.number = ?";
		} else if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			return "ie.student.intermediary = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.REPORT.getCode().equalsIgnoreCase(key)) {
			return "ie.report.number";
		} else if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			return "ie.student.intermediary";
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}

	@Override
	protected void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.REPORT.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
