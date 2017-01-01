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
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.ITeamDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.ITeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.TeamDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.TeamDTOAdapter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TeamDAO extends HibernateSession<Team> implements ITeamDAO {

	private TeamDTOAdapter adapter;

	public TeamDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);

		adapter = new TeamDTOAdapter();
	}

	@Override
	public long insert(Team team) {
		TeamDTO dto = new TeamDTO(team);

		HibernateUtils.insert(this, dto);

		return dto.getId();
	}

	@Override
	public void update(IUpdateWrapper<Team> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Team team) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		HibernateUtils.delete(this, builder.toString(), team);
	}

	@Override
	public Team select(long id) {
		return null;
	}

	@Override
	public List<Team> select(Map<String, String[]> parameters) {
		return HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Team> selectAll() {
		return HibernateUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Team team) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<Team> updateWrapper) {
		query.setParameter(1, updateWrapper.getWrapped().getNumber());
		query.setParameter(2, ((ITeamUpdateWrapper) updateWrapper).getNumber());
	}

	@Override
	public void editQueryForDelete(Query query, Team team) {
		query.setParameter(1, team.getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return ITeamDAO.COLUMN_CODE;
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

	@Override
	public Team getResultValues(Serializable serializable) {
		Team team = new Team();

		adapter.adapt((TeamDTO) serializable, team);

		return team;
	}
}
