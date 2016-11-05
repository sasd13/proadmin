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

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
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
public class JDBCRunningTeamDAO extends JDBCSession<RunningTeam> implements IRunningTeamDAO {

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
		builder.append(COLUMN_RUNNING_YEAR);
		builder.append(", " + COLUMN_RUNNING_PROJECT_CODE);
		builder.append(", " + COLUMN_RUNNING_TEACHER_CODE);
		builder.append(", " + COLUMN_TEAM_CODE);
		builder.append(", " + COLUMN_ACADEMICLEVEL_CODE);
		builder.append(") VALUES (?, ?, ?, ?, ?)");

		return JDBCUtils.insert(this, builder.toString(), runningTeam);
	}

	@Override
	public void update(RunningTeam runningTeam) throws DAOException {
		// Do nothing
	}

	@Override
	public void delete(RunningTeam runningTeam) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_RUNNING_YEAR + " = ?");
		builder.append(" AND " + COLUMN_RUNNING_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_RUNNING_TEACHER_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEAM_CODE + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), runningTeam);
	}

	@Override
	public RunningTeam select(long id) throws DAOException {
		return null;
	}

	@Override
	public List<RunningTeam> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<RunningTeam> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(RunningTeam runningTeam) throws DAOException {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		preparedStatement.setInt(1, runningTeam.getRunning().getYear());
		preparedStatement.setString(2, runningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(3, runningTeam.getRunning().getTeacher().getNumber());
		preparedStatement.setString(4, runningTeam.getTeam().getNumber());
		preparedStatement.setString(5, runningTeam.getAcademicLevel().getCode());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		// Do nothing
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		preparedStatement.setInt(1, runningTeam.getRunning().getYear());
		preparedStatement.setString(2, runningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(3, runningTeam.getRunning().getTeacher().getNumber());
		preparedStatement.setString(4, runningTeam.getTeam().getNumber());
		preparedStatement.setString(5, runningTeam.getAcademicLevel().getCode());
	}

	@Override
	public RunningTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_RUNNING_PROJECT_CODE));

		Teacher teacher = new Teacher();
		teacher.setNumber(resultSet.getString(COLUMN_RUNNING_TEACHER_CODE));

		Running running = new Running();
		running.setYear(resultSet.getInt(COLUMN_RUNNING_YEAR));
		running.setProject(project);
		running.setTeacher(teacher);

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_TEAM_CODE));

		AcademicLevel academicLevel = new AcademicLevel();
		academicLevel.setCode(resultSet.getString(COLUMN_ACADEMICLEVEL_CODE));

		RunningTeam runningTeam = new RunningTeam();
		runningTeam.setRunning(running);
		runningTeam.setTeam(team);
		runningTeam.setAcademicLevel(new AcademicLevel());

		return runningTeam;
	}
}
