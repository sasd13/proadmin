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
import com.sasd13.javaex.util.order.OrderException;
import com.sasd13.proadmin.backend.dao.IRunningTeamDAO;
import com.sasd13.proadmin.backend.model.RunningTeam;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RunningTeamDAO extends AbstractDAO implements IRunningTeamDAO, IConditionnal {

	public RunningTeamDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public RunningTeam create(RunningTeam runningTeam) {
		currentSession().save(runningTeam);
		currentSession().flush();

		return runningTeam;
	}

	@Override
	public void update(RunningTeam runningTeam) {
		currentSession().update(runningTeam);
		currentSession().flush();
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		currentSession().remove(runningTeam);
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RunningTeam> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from RunningTeam rntm");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<RunningTeam>) query.getResultList();
	}

	@Override
	public String getCondition(String key) {
		if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			return "rntm.running.year = ?";
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			return "rntm.running.project.code = ?";
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			return "rntm.running.teacher.number = ?";
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			return "rntm.team.number = ?";
		} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(key)) {
			return "rntm.academicLevel.code = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			return "rntm.running.year";
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			return "rntm.running.project.code";
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			return "rntm.running.teacher.number";
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			return "rntm.team.number";
		} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(key)) {
			return "rntm.academicLevel.code";
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
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
