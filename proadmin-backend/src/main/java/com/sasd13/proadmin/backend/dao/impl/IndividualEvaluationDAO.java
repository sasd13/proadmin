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
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.backend.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.backend.model.IndividualEvaluation;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class IndividualEvaluationDAO extends AbstractDAO implements IIndividualEvaluationDAO, IConditionnal {

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
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from IndividualEvaluation ie");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<IndividualEvaluation>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return "ie.report.number = ?";
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return "ie.student.intermediary = ?";
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
