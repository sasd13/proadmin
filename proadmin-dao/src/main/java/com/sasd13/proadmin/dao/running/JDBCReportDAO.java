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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.condition.ConditionBuilder;
import com.sasd13.javaex.net.http.URLQueryEncoder;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.JDBCEntityDAO;

/**
 *
 * @author Samir
 */
public class JDBCReportDAO extends JDBCEntityDAO<Report> implements IReportDAO {

	private static final Logger LOG = Logger.getLogger(JDBCReportDAO.class);

	private JDBCLeadEvaluationDAO leadEvaluationDAO;
	private JDBCIndividualEvaluationDAO individualEvaluationDAO;

	public JDBCReportDAO() {
		leadEvaluationDAO = new JDBCLeadEvaluationDAO();
		individualEvaluationDAO = new JDBCIndividualEvaluationDAO();
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
	protected void editPreparedStatement(PreparedStatement preparedStatement, Report report) throws SQLException {
		preparedStatement.setString(1, report.getNumber());
		preparedStatement.setString(2, String.valueOf(report.getMeetingDate()));
		preparedStatement.setInt(3, report.getSession());
		preparedStatement.setString(4, report.getComment());
		preparedStatement.setLong(5, report.getRunningTeam().getId());
	}

	@Override
	protected Report getResultSetValues(ResultSet resultSet) throws SQLException {
		RunningTeam runningTeam = new RunningTeam();
		runningTeam.setId(resultSet.getLong(COLUMN_RUNNINGTEAM));

		Report report = new Report(runningTeam);
		report.setId(resultSet.getLong(COLUMN_ID));
		report.setNumber(resultSet.getString(COLUMN_NUMBER));
		report.setMeetingDate(Timestamp.valueOf(resultSet.getString(COLUMN_MEETINGDATE)));
		report.setSession(resultSet.getInt(COLUMN_SESSION));
		report.setComment(resultSet.getString(COLUMN_COMMENT));

		return report;
	}

	@Override
	public long insert(Report report) throws DAOException {
		LOG.info("JDBCReportDAO --> insert : number=" + report.getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_NUMBER);
		builder.append(", " + COLUMN_MEETINGDATE);
		builder.append(", " + COLUMN_SESSION);
		builder.append(", " + COLUMN_COMMENT);
		builder.append(", " + COLUMN_RUNNINGTEAM);
		builder.append(") VALUES (?, ?, ?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, report);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
				report.setId(id);

				leadEvaluationDAO.insert(report.getLeadEvaluation());

				for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
					individualEvaluationDAO.insert(individualEvaluation);
				}

				connection.commit();
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException | DAOException e) {
			doCatchWithRollback(e, LOG, "JDBCReportDAO --> insert failed. Rollback...", "Report not inserted", "JDBCReportDAO --> rollback failed");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(Report report) throws DAOException {
		LOG.info("JDBCReportDAO --> update : number=" + report.getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_NUMBER + " = ?");
		builder.append(", " + COLUMN_MEETINGDATE + " = ?");
		builder.append(", " + COLUMN_SESSION + " = ?");
		builder.append(", " + COLUMN_COMMENT + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, report);
			preparedStatement.setLong(6, report.getId());

			preparedStatement.executeUpdate();

			leadEvaluationDAO.update(report.getLeadEvaluation());

			for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
				individualEvaluationDAO.update(individualEvaluation);
			}

			connection.commit();
		} catch (SQLException | DAOException e) {
			doCatchWithRollback(e, LOG, "JDBCReportDAO --> update failed. Rollback...", "Report not updated", "JDBCReportDAO --> rollback failed");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(Report report) throws DAOException {
		LOG.info("JDBCReportDAO --> delete : number=" + report.getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DELETED + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			connection.setAutoCommit(false);

			leadEvaluationDAO.delete(report.getLeadEvaluation());

			for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
				individualEvaluationDAO.delete(individualEvaluation);
			}

			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, report.getId());

			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException | DAOException e) {
			doCatchWithRollback(e, LOG, "JDBCReportDAO --> delete failed. Rollback...", "Report not deleted", "JDBCReportDAO --> rollback failed");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public Report select(long id) throws DAOException {
		LOG.info("JDBCReportDAO --> select : id=" + id);

		Report report = null;

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");
		builder.append(" AND ");
		builder.append(COLUMN_DELETED + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setLong(1, id);
			preparedStatement.setBoolean(2, false);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				report = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCReportDAO --> select failed", "Report not selected");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return report;
	}

	public List<Report> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCReportDAO --> select : parameters=" + URLQueryEncoder.toString(parameters));

		List<Report> reports = new ArrayList<>();

		Statement statement = null;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM ");
			builder.append(TABLE);
			builder.append(" WHERE ");
			builder.append(ConditionBuilder.parse(parameters, new ReportConditionExpression()));
			builder.append(" AND ");
			builder.append(COLUMN_DELETED + " = false");

			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				reports.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCReportDAO --> select failed", "Reports not selected");
		} finally {
			doFinally(statement, LOG);
		}

		return reports;
	}

	@Override
	public List<Report> selectAll() throws DAOException {
		LOG.info("JDBCReportDAO --> selectAll");

		List<Report> reports = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_DELETED + " = false");

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				reports.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCReportDAO --> selectAll failed", "Reports not selected");
		} finally {
			doFinally(statement, LOG);
		}

		return reports;
	}
}
