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
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.javaex.util.order.OrderException;
import com.sasd13.proadmin.backend.dao.IRunningDAO;
import com.sasd13.proadmin.backend.model.Running;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
public class RunningDAO extends AbstractDAO implements IRunningDAO, IConditionnal {

	public RunningDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
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
	public List<Running> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Running rn");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<Running>) query.getResultList();
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			return "rn.year = ?" + index;
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			return "rn.project.code = ?" + index;
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			return "rn.teacher.intermediary = ?" + index;
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			return "rn.year";
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			return "rn.project.code";
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			return "rn.teacher.intermediary";
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, Integer.parseInt(value));
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
