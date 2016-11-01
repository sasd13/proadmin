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

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.JDBCUtils;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 *
 * @author Samir
 */
public class JDBCReportDAO extends JDBCEntityDAO<Report> implements IReportDAO {

	private static final Logger LOG = Logger.getLogger(JDBCReportDAO.class);

	private JDBCLeadEvaluationDAO leadEvaluationDAO;
	private JDBCIndividualEvaluationDAO individualEvaluationDAO;
	private IExpressionBuilder expressionBuilder;
	private ReportTransaction transaction;

	public JDBCReportDAO() {
		leadEvaluationDAO = new JDBCLeadEvaluationDAO();
		individualEvaluationDAO = new JDBCIndividualEvaluationDAO();
		expressionBuilder = new ReportExpressionBuilder();
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
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_TEAM_CODE);
		builder.append(", " + COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		transaction.editTransaction(builder.toString(), report);

		return JDBCUtils.insertInTransaction(this, transaction);
	}

	@Override
	public void update(Report report) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(", " + COLUMN_DATEMEETING + " = ?");
		builder.append(", " + COLUMN_SESSION + " = ?");
		builder.append(", " + COLUMN_COMMENT + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_TEAM_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		transaction.editTransaction(builder.toString(), report);

		JDBCUtils.updateInTransaction(this, transaction);
	}

	@Override
	public void delete(Report report) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		transaction.editTransaction(builder.toString(), report);

		JDBCUtils.deleteInTransaction(this, transaction);
	}

	@Override
	public Report select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public List<Report> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<Report> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public void editPreparedStatement(PreparedStatement preparedStatement, Report report) throws SQLException {
		preparedStatement.setString(1, report.getNumber());
		preparedStatement.setString(2, String.valueOf(report.getDateMeeting()));
		preparedStatement.setInt(3, report.getSession());
		preparedStatement.setString(4, report.getComment());
		preparedStatement.setString(5, report.getRunningTeam().getRunning().getProject().getCode());
		preparedStatement.setString(6, report.getRunningTeam().getRunning().getTeacher().getNumber());
		preparedStatement.setString(7, report.getRunningTeam().getTeam().getNumber());
		preparedStatement.setString(8, report.getRunningTeam().getAcademicLevel().getCode());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, Report report) throws SQLException {
		super.editPreparedStatementForUpdate(preparedStatement, report);

		preparedStatement.setString(9, report.getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Report report) throws SQLException {
		super.editPreparedStatementForDelete(preparedStatement, report);

		preparedStatement.setString(1, report.getNumber());
	}

	@Override
	public Report getResultSetValues(ResultSet resultSet) throws SQLException {
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
		report.setDateMeeting(Timestamp.valueOf(resultSet.getString(COLUMN_DATEMEETING)));
		report.setSession(resultSet.getInt(COLUMN_SESSION));
		report.setComment(resultSet.getString(COLUMN_COMMENT));

		return report;
	}
}
