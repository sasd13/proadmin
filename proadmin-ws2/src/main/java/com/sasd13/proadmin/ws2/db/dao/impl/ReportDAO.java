/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.dao.IReportDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ReportDAO extends JDBCSession<Report> implements IReportDAO {

	private LeadEvaluationDAO leadEvaluationDAO;
	private IndividualEvaluationDAO individualEvaluationDAO;
	private ReportTransaction transaction;

	public ReportDAO() {
		leadEvaluationDAO = new LeadEvaluationDAO(null);
		individualEvaluationDAO = new IndividualEvaluationDAO(null);
		transaction = new ReportTransaction(this);
	}

	@Override
	public ILeadEvaluationDAO getLeadEvaluationDAO() {
		return leadEvaluationDAO;
	}

	@Override
	public IIndividualEvaluationDAO getIndividualEvaluationDAO() {
		return individualEvaluationDAO;
	}

	@Override
	public void setConnection(Connection connection) {
		super.setConnection(connection);

		// leadEvaluationDAO.setConnection(connection);
		// individualEvaluationDAO.setConnection(connection);
	}

	@Override
	public long insert(Report report) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_DATEMEETING);
		builder.append(", " + COLUMN_SESSION);
		builder.append(", " + COLUMN_COMMENT);
		builder.append(", " + COLUMN_YEAR);
		builder.append(", " + COLUMN_PROJECT);
		builder.append(", " + COLUMN_TEACHER);
		builder.append(", " + COLUMN_TEAM);
		builder.append(", " + COLUMN_ACADEMICLEVEL);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		transaction.editTransaction(builder.toString(), report);

		return JDBCUtils.insertInTransaction(this, transaction);
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
		builder.append(", " + COLUMN_YEAR + " = ?");
		builder.append(", " + COLUMN_PROJECT + " = ?");
		builder.append(", " + COLUMN_TEACHER + " = ?");
		builder.append(", " + COLUMN_TEAM + " = ?");
		builder.append(", " + COLUMN_ACADEMICLEVEL + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		transaction.editTransaction(builder.toString(), (IReportUpdateWrapper) updateWrapper);

		JDBCUtils.updateInTransaction(this, transaction);
	}

	@Override
	public void delete(Report report) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		transaction.editTransaction(builder.toString(), report);

		JDBCUtils.deleteInTransaction(this, transaction);
	}

	@Override
	public Report select(long id) {
		return null;
	}

	@Override
	public List<Report> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Report> selectAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Report report) {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Report report) throws SQLException {
		preparedStatement.setString(1, report.getNumber());
		preparedStatement.setString(2, String.valueOf(report.getDateMeeting()));
		preparedStatement.setInt(3, report.getSession());
		preparedStatement.setString(4, report.getComment());
		preparedStatement.setInt(5, report.getRunningTeam().getRunning().getYear());
		preparedStatement.setString(6, report.getRunningTeam().getRunning().getProject().getCode());
		preparedStatement.setString(7, report.getRunningTeam().getRunning().getTeacher().getNumber());
		preparedStatement.setString(8, report.getRunningTeam().getTeam().getNumber());
		preparedStatement.setString(9, report.getRunningTeam().getAcademicLevel().getCode());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Report> updateWrapper) throws SQLException {
		Report report = updateWrapper.getWrapped();

		preparedStatement.setString(1, String.valueOf(report.getDateMeeting()));
		preparedStatement.setInt(2, report.getSession());
		preparedStatement.setString(3, report.getComment());
		preparedStatement.setInt(4, report.getRunningTeam().getRunning().getYear());
		preparedStatement.setString(5, report.getRunningTeam().getRunning().getProject().getCode());
		preparedStatement.setString(6, report.getRunningTeam().getRunning().getTeacher().getNumber());
		preparedStatement.setString(7, report.getRunningTeam().getTeam().getNumber());
		preparedStatement.setString(8, report.getRunningTeam().getAcademicLevel().getCode());
		preparedStatement.setString(9, ((IReportUpdateWrapper) updateWrapper).getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Report report) throws SQLException {
		preparedStatement.setString(1, report.getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_CODE;
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_SESSION;
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_YEAR;
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_PROJECT;
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_TEACHER;
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_TEAM;
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_ACADEMICLEVEL;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			try {
				preparedStatement.setInt(index, Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			try {
				preparedStatement.setInt(index, Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public Report getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();

		return report;
	}
}
