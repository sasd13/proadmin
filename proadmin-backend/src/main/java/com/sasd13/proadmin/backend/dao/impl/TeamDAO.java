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
import com.sasd13.proadmin.backend.dao.ITeamDAO;
import com.sasd13.proadmin.backend.model.Team;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
public class TeamDAO extends AbstractDAO implements ITeamDAO, IConditionnal {

	public TeamDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Team create(Team team) {
		currentSession().save(team);
		currentSession().flush();

		return team;
	}

	@Override
	public void update(Team team) {
		currentSession().update(team);
		currentSession().flush();
	}

	@Override
	public void delete(Team team) {
		currentSession().remove(team);
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Team> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Team tm");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<Team>) query.getResultList();
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			return "tm.number = ?" + index;
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			return "tm.number";
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
