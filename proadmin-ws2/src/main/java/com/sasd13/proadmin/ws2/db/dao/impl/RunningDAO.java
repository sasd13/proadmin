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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.dao.hibernate.HibernateSession;
import com.sasd13.javaex.dao.hibernate.HibernateUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningDAO;
import com.sasd13.proadmin.ws2.db.dto.RunningDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RunningDAO extends HibernateSession<Running> implements IRunningDAO {

	public RunningDAO(SessionFactory connectionFactory) {
		super(connectionFactory);
	}

	@Override
	public long insert(Running running) {
		RunningDTO dto = new RunningDTO(running);

		HibernateUtils.insert(this, dto);

		return dto.getId();
	}

	@Override
	public void update(IUpdateWrapper<Running> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(", " + COLUMN_PROJECT + " = ?");
		builder.append(", " + COLUMN_TEACHER + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT + " = ?");
		builder.append(" AND " + COLUMN_TEACHER + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Running running) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT + " = ?");
		builder.append(" AND " + COLUMN_TEACHER + " = ?");

		HibernateUtils.delete(this, builder.toString(), running);
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
	public boolean contains(Running running) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<Running> updateWrapper) {
		query.setParameter(1, updateWrapper.getWrapped().getYear());
		query.setParameter(2, updateWrapper.getWrapped().getProject().getCode());
		query.setParameter(3, updateWrapper.getWrapped().getTeacher().getNumber());
		query.setParameter(4, ((IRunningUpdateWrapper) updateWrapper).getYear());
		query.setParameter(5, ((IRunningUpdateWrapper) updateWrapper).getProjectCode());
		query.setParameter(6, ((IRunningUpdateWrapper) updateWrapper).getTeacherNumber());
	}

	@Override
	public void editQueryForDelete(Query query, Running running) {
		query.setParameter(1, running.getYear());
		query.setParameter(2, running.getProject().getCode());
		query.setParameter(3, running.getTeacher().getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_YEAR;
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_PROJECT;
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_TEACHER;
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
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
