/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
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
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IReportDAO;
import com.sasd13.proadmin.ws2.db.dto.ReportDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ReportDAO extends HibernateSession<Report> implements IReportDAO {

	public ReportDAO(SessionFactory connectionFactory) {
		super(connectionFactory);
	}

	@Override
	public long insert(Report report) {
		ReportDTO dto = new ReportDTO(report);

		HibernateUtils.insert(this, dto);

		return dto.getId();
	}

	@Override
	public void update(IUpdateWrapper<Report> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DATEMEETING + " = ?");
		builder.append(", " + COLUMN_SESSION + " = ?");
		builder.append(", " + COLUMN_COMMENT + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		transaction.editTransaction(builder.toString(), (IReportUpdateWrapper) updateWrapper);

		HibernateUtils.updateInTransaction(this, transaction);
	}

	@Override
	public void delete(Report report) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		transaction.editTransaction(builder.toString(), report);

		HibernateUtils.deleteInTransaction(this, transaction);
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
	public boolean contains(Report report) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<Report> updateWrapper) {
		query.setParameter(1, new Timestamp(updateWrapper.getWrapped().getDateMeeting().getTime()));
		query.setParameter(2, updateWrapper.getWrapped().getSession());
		query.setParameter(3, updateWrapper.getWrapped().getComment());
		query.setParameter(4, updateWrapper.getWrapped().getRunningTeam().getRunning().getYear());
		query.setParameter(5, updateWrapper.getWrapped().getRunningTeam().getRunning().getProject().getCode());
		query.setParameter(6, updateWrapper.getWrapped().getRunningTeam().getRunning().getTeacher().getNumber());
		query.setParameter(7, updateWrapper.getWrapped().getRunningTeam().getTeam().getNumber());
		query.setParameter(8, updateWrapper.getWrapped().getRunningTeam().getAcademicLevel().getCode());
		query.setParameter(9, ((IReportUpdateWrapper) updateWrapper).getNumber());
	}

	@Override
	public void editQueryForDelete(Query query, Report report) {
		query.setParameter(1, report.getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_CODE;
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_SESSION;
		} else if (EnumParameter.RUNNINGTEAM.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			try {
				query.setParameter(index, Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
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
