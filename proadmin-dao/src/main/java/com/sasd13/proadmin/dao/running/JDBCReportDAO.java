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
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.condition.ConditionBuilder;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.dao.validator.ValidatorUtils;

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
		preparedStatement.setString(5, report.getRunningTeam().getRunning().getProject().getCode());
		preparedStatement.setString(6, report.getRunningTeam().getRunning().getTeacher().getNumber());
		preparedStatement.setString(7, report.getRunningTeam().getTeam().getNumber());
		preparedStatement.setString(8, report.getRunningTeam().getAcademicLevel().getCode());
	}

	@Override
	protected Report getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE));

		Teacher teacher = new Teacher();
		teacher.setNumber(resultSet.getString(COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE));

		Running running = new Running(project);
		running.setTeacher(teacher);

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_RUNNINGTEAM_TEAM_CODE));

		RunningTeam runningTeam = new RunningTeam(running, team);
		runningTeam.setAcademicLevel(new AcademicLevel(resultSet.getString(COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE)));

		Report report = new Report(runningTeam);
		report.setNumber(resultSet.getString(COLUMN_CODE));
		report.setMeetingDate(Timestamp.valueOf(resultSet.getString(COLUMN_MEETINGDATE)));
		report.setSession(resultSet.getInt(COLUMN_SESSION));
		report.setComment(resultSet.getString(COLUMN_COMMENT));

		return report;
	}

	@Override
	public long insert(Report report) throws DAOException {
		ValidatorUtils.validate(report);

		LOG.info("JDBCReportDAO --> insert : number=" + report.getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_MEETINGDATE);
		builder.append(", " + COLUMN_SESSION);
		builder.append(", " + COLUMN_COMMENT);
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_TEAM_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, report);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);

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
		ValidatorUtils.validate(report);

		LOG.info("JDBCReportDAO --> update : number=" + report.getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(", " + COLUMN_MEETINGDATE + " = ?");
		builder.append(", " + COLUMN_SESSION + " = ?");
		builder.append(", " + COLUMN_COMMENT + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_TEAM_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, report);
			preparedStatement.setString(9, report.getNumber());

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
		ValidatorUtils.validate(report);

		LOG.info("JDBCReportDAO --> delete : number=" + report.getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DELETED + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			connection.setAutoCommit(false);

			leadEvaluationDAO.delete(report.getLeadEvaluation());

			for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
				individualEvaluationDAO.delete(individualEvaluation);
			}

			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, report.getNumber());

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
			doCatch(e, LOG, "JDBCReportDAO --> select failed", "Report not readed");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return report;
	}

	public List<Report> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCReportDAO --> select : parameters=" + URLQueryUtils.toString(parameters));

		List<Report> reports = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(ConditionBuilder.parse(parameters, new ReportExpressionBuilder()));
		builder.append(" AND ");
		builder.append(COLUMN_DELETED + " = false");

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				reports.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCReportDAO --> select failed", "Reports not readed");
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
			doCatch(e, LOG, "JDBCReportDAO --> selectAll failed", "Reports not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return reports;
	}
}
