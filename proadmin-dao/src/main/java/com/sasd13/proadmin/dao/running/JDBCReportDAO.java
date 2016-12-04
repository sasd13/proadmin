/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.running;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.dao.jdbc.ConditionException;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCReportDAO extends JDBCSession<Report> implements IReportDAO {

	private JDBCLeadEvaluationDAO leadEvaluationDAO;
	private JDBCIndividualEvaluationDAO individualEvaluationDAO;
	private ReportTransaction transaction;

	public JDBCReportDAO() {
		leadEvaluationDAO = new JDBCLeadEvaluationDAO();
		individualEvaluationDAO = new JDBCIndividualEvaluationDAO();
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

		leadEvaluationDAO.setConnection(connection);
		individualEvaluationDAO.setConnection(connection);
	}

	@Override
	public long insert(Report report) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_DATEMEETING);
		builder.append(", " + COLUMN_SESSION);
		builder.append(", " + COLUMN_COMMENT);
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_YEAR);
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_TEAM_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		transaction.editTransaction(builder.toString(), report);

		return JDBCUtils.insertInTransaction(getConnection(), transaction);
	}

	@Override
	public void update(IUpdateWrapper<Report> updateWrapper) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DATEMEETING + " = ?");
		builder.append(", " + COLUMN_SESSION + " = ?");
		builder.append(", " + COLUMN_COMMENT + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_YEAR + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_TEAM_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		transaction.editTransaction(builder.toString(), (IReportUpdateWrapper) updateWrapper);

		JDBCUtils.updateInTransaction(getConnection(), transaction);
	}

	@Override
	public void delete(Report report) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		transaction.editTransaction(builder.toString(), report);

		JDBCUtils.deleteInTransaction(getConnection(), transaction);
	}

	@Override
	public Report select(long id) throws DAOException {
		return null;
	}

	@Override
	public List<Report> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Report> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Report report) throws DAOException {
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
	public String buildCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_CODE;
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_SESSION;
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_RUNNING_YEAR;
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE;
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE;
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_TEAM_CODE;
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE;
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
		Report report = new Report(resultSet.getInt(COLUMN_RUNNINGTEAM_RUNNING_YEAR), resultSet.getString(COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE), resultSet.getString(COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE), resultSet.getString(COLUMN_RUNNINGTEAM_TEAM_CODE), resultSet.getString(COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE));

		report.setNumber(resultSet.getString(COLUMN_CODE));
		report.setDateMeeting(Timestamp.valueOf(resultSet.getString(COLUMN_DATEMEETING)));
		report.setSession(resultSet.getInt(COLUMN_SESSION));
		report.setComment(resultSet.getString(COLUMN_COMMENT));

		return report;
	}
}
