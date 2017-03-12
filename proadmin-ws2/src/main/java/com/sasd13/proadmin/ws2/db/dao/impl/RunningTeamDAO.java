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
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws2.db.dto.RunningTeamDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RunningTeamDAO extends HibernateSession<RunningTeam> implements IRunningTeamDAO {

	public RunningTeamDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public long insert(RunningTeam runningTeam) {
		RunningTeamDTO dto = new RunningTeamDTO(runningTeam);

		HibernateUtils.insert(this, dto);

		return dto.getId();
	}

	@Override
	public void update(IUpdateWrapper<RunningTeam> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_RUNNING + " = ?");
		builder.append(", " + COLUMN_TEAM + " = ?");
		builder.append(", " + COLUMN_ACADEMICLEVEL + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_RUNNING + " = ?");
		builder.append(" AND " + COLUMN_TEAM + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_RUNNING + " = ?");
		builder.append(" AND " + COLUMN_TEAM + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL + " = ?");

		HibernateUtils.delete(this, builder.toString(), runningTeam);
	}

	@Override
	public Serializable select(long id) {
		return null;
	}

	@Override
	public List<Serializable> select(Map<String, String[]> parameters) {
		return (List<Serializable>) HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Serializable> selectAll() {
		return (List<Serializable>) HibernateUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(RunningTeam runningTeam) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<RunningTeam> updateWrapper) {
		query.setParameter(1, updateWrapper.getWrapped().getRunning().getYear());
		query.setParameter(2, updateWrapper.getWrapped().getRunning().getProject().getCode());
		query.setParameter(3, updateWrapper.getWrapped().getRunning().getTeacher().getNumber());
		query.setParameter(4, updateWrapper.getWrapped().getTeam().getNumber());
		query.setParameter(5, updateWrapper.getWrapped().getAcademicLevel().getCode());
		query.setParameter(6, ((IRunningTeamUpdateWrapper) updateWrapper).getRunningYear());
		query.setParameter(7, ((IRunningTeamUpdateWrapper) updateWrapper).getProjectCode());
		query.setParameter(8, ((IRunningTeamUpdateWrapper) updateWrapper).getTeacherNumber());
		query.setParameter(9, ((IRunningTeamUpdateWrapper) updateWrapper).getTeamNumber());
		query.setParameter(10, ((IRunningTeamUpdateWrapper) updateWrapper).getAcademicLevelCode());
	}

	@Override
	public void editQueryForDelete(Query query, RunningTeam runningTeam) {
		query.setParameter(1, runningTeam.getRunning().getYear());
		query.setParameter(2, runningTeam.getRunning().getProject().getCode());
		query.setParameter(3, runningTeam.getRunning().getTeacher().getNumber());
		query.setParameter(4, runningTeam.getTeam().getNumber());
		query.setParameter(5, runningTeam.getAcademicLevel().getCode());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.RUNNING.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_RUNNING;
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_TEAM;
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_ACADEMICLEVEL;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			try {
				query.setParameter(index, Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
