/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws.dao.IRunningTeamDAO;

/**
 *
 * @author Samir
 */
public class JDBCRunningTeamDAO extends JDBCSession<RunningTeam> implements IRunningTeamDAO {

	@Override
	public long create(RunningTeam runningTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_YEAR);
		builder.append(", " + COLUMN_PROJECT);
		builder.append(", " + COLUMN_TEACHER);
		builder.append(", " + COLUMN_TEAM);
		builder.append(", " + COLUMN_ACADEMICLEVEL);
		builder.append(") VALUES (?, ?, ?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), runningTeam);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(RunningTeamUpdateWrapper updateWrapper) {
		// StringBuilder builder = new StringBuilder();
		// builder.append("UPDATE ");
		// builder.append(TABLE);
		// builder.append(" SET ");
		// builder.append(" WHERE ");
		// builder.append(COLUMN_YEAR + " = ?");
		// builder.append(" AND " + COLUMN_PROJECT + " = ?");
		// builder.append(" AND " + COLUMN_TEACHER + " = ?");
		// builder.append(" AND " + COLUMN_TEAM + " = ?");
		// builder.append(" AND " + COLUMN_ACADEMICLEVEL + " = ?");
		//
		// JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT + " = ?");
		builder.append(" AND " + COLUMN_TEACHER + " = ?");
		builder.append(" AND " + COLUMN_TEAM + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), runningTeam);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<RunningTeam> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		preparedStatement.setInt(1, runningTeam.getRunning().getYear());
		preparedStatement.setString(2, runningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(3, runningTeam.getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(4, runningTeam.getTeam().getNumber());
		preparedStatement.setString(5, runningTeam.getAcademicLevel().getCode());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<RunningTeam> updateWrapper) throws SQLException {
		// RunningTeam runningTeam = updateWrapper.getWrapped();
		//
		// preparedStatement.setInt(6, ((RunningTeamUpdateWrapper) updateWrapper).getRunningYear());
		// preparedStatement.setString(7, ((RunningTeamUpdateWrapper) updateWrapper).getProjectCode());
		// preparedStatement.setString(8, ((RunningTeamUpdateWrapper) updateWrapper).getTeacherNumber());
		// preparedStatement.setString(9, ((RunningTeamUpdateWrapper) updateWrapper).getTeamNumber());
		// preparedStatement.setString(10, ((RunningTeamUpdateWrapper) updateWrapper).getAcademicLevelCode());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		preparedStatement.setInt(1, runningTeam.getRunning().getYear());
		preparedStatement.setString(2, runningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(3, runningTeam.getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(4, runningTeam.getTeam().getNumber());
		preparedStatement.setString(5, runningTeam.getAcademicLevel().getCode());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_YEAR + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_PROJECT + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_TEACHER + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_TEAM + " = ?";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_ACADEMICLEVEL + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
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
	public RunningTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		RunningTeam runningTeam = new RunningTeam();

		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_PROJECT));

		Teacher teacher = new Teacher();
		teacher.setIntermediary(resultSet.getString(COLUMN_TEACHER));

		Running running = new Running();
		running.setYear(resultSet.getInt(COLUMN_YEAR));
		running.setProject(project);
		running.setTeacher(teacher);

		runningTeam.setRunning(running);

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_TEAM));

		runningTeam.setTeam(team);

		AcademicLevel academicLevel = new AcademicLevel();
		academicLevel.setCode(resultSet.getString(COLUMN_ACADEMICLEVEL));

		runningTeam.setAcademicLevel(academicLevel);

		return runningTeam;
	}
}
