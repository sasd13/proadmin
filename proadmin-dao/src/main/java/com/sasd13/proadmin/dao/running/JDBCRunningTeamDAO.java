/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.running;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.QueryUtils;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 *
 * @author Samir
 */
public class JDBCRunningTeamDAO extends JDBCEntityDAO<RunningTeam> implements IRunningTeamDAO {

	private static final Logger LOG = Logger.getLogger(JDBCRunningTeamDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCRunningTeamDAO() {
		expressionBuilder = new RunningTeamExpressionBuilder();
	}

	@Override
	public long insert(RunningTeam runningTeam) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_RUNNING_PROJECT_CODE);
		builder.append(", " + COLUMN_RUNNING_TEACHER_CODE);
		builder.append(", " + COLUMN_TEAM_CODE);
		builder.append(", " + COLUMN_ACADEMICLEVEL_CODE);
		builder.append(") VALUES (?, ?, ?, ?)");

		return QueryUtils.insert(this, builder.toString(), runningTeam);
	}

	@Override
	public void update(RunningTeam runningTeam) throws DAOException {
		LOG.error("update unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public void delete(RunningTeam runningTeam) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_RUNNING_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_RUNNING_TEACHER_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEAM_CODE + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL_CODE + " = ?");

		QueryUtils.delete(this, builder.toString(), runningTeam);
	}

	@Override
	public RunningTeam select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<RunningTeam> select(Map<String, String[]> parameters) throws DAOException {
		return QueryUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<RunningTeam> selectAll() throws DAOException {
		return QueryUtils.selectAll(this, TABLE);
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		preparedStatement.setString(1, runningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(2, runningTeam.getRunning().getTeacher().getNumber());
		preparedStatement.setString(3, runningTeam.getTeam().getNumber());
		preparedStatement.setString(4, runningTeam.getAcademicLevel().getCode());
	}

	@Override
	protected void editPreparedStatementForDelete(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		super.editPreparedStatementForDelete(preparedStatement, runningTeam);

		preparedStatement.setString(1, runningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(2, runningTeam.getRunning().getTeacher().getNumber());
		preparedStatement.setString(3, runningTeam.getTeam().getNumber());
		preparedStatement.setString(4, runningTeam.getAcademicLevel().getCode());
	}

	@Override
	protected RunningTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_RUNNING_PROJECT_CODE));

		Teacher teacher = new Teacher();
		teacher.setNumber(resultSet.getString(COLUMN_RUNNING_TEACHER_CODE));

		Running running = new Running(project);
		running.setTeacher(teacher);

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_TEAM_CODE));

		RunningTeam runningTeam = new RunningTeam(running, team);
		runningTeam.setAcademicLevel(new AcademicLevel(resultSet.getString(COLUMN_ACADEMICLEVEL_CODE)));

		return runningTeam;
	}
}
