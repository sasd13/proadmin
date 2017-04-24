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
import com.sasd13.proadmin.backend.bean.Team;
import com.sasd13.proadmin.backend.dao.ITeamDAO;
import com.sasd13.proadmin.backend.dao.dto.TeamDTO;
import com.sasd13.proadmin.backend.util.adapter.bean2dto.TeamAdapterB2D;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TeamDAO extends AbstractDAO implements ITeamDAO, IConditionnal {

	public TeamDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public TeamDTO create(Team team) {
		TeamDTO dto = new TeamAdapterB2D().adapt(team);

		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	@Override
	public void update(Team team) {
		currentSession().update(new TeamAdapterB2D().adapt(team));
		currentSession().flush();
	}

	@Override
	public void delete(Team team) {
		currentSession().remove(new TeamAdapterB2D().adapt(team));
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeamDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from teams tm");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<TeamDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return "tm.number = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
