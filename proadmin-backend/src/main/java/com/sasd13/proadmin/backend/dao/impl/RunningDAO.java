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
import com.sasd13.proadmin.backend.dao.IRunningDAO;
import com.sasd13.proadmin.backend.model.Running;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RunningDAO extends AbstractDAO implements IRunningDAO, IConditionnal {

	public RunningDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Running create(Running running) {
		currentSession().save(running);
		currentSession().flush();

		return running;
	}

	@Override
	public void update(Running running) {
		currentSession().update(running);
		currentSession().flush();
	}

	@Override
	public void delete(Running running) {
		currentSession().remove(running);
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from runnings rn");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<Running>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return "rn.year = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return "rn.project.code = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return "rn.teacher.intermediary = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, Integer.parseInt(value));
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
