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
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.bean.AcademicLevel;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.RunningTeam;
import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.bean.update.RunningTeamUpdate;
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
	public void update(RunningTeamUpdate runningTeamUpdate) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT + " = ?");
		builder.append(" AND " + COLUMN_TEACHER + " = ?");
		builder.append(" AND " + COLUMN_TEAM + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT + " = ?");
		builder.append(" AND " + COLUMN_TEACHER + " = ?");
		builder.append(" AND " + COLUMN_TEAM + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL + " = ?");

		try {
			JDBCUtils.update(this, builder.toString(), runningTeamUpdate);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
		RunningTeam runningTeam = updateWrapper.getWrapped();

		preparedStatement.setInt(1, runningTeam.getRunning().getYear());
		preparedStatement.setString(2, runningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(3, runningTeam.getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(4, runningTeam.getTeam().getNumber());
		preparedStatement.setString(5, runningTeam.getAcademicLevel().getCode());
		preparedStatement.setInt(6, ((RunningTeamUpdate) updateWrapper).getRunningYear());
		preparedStatement.setString(7, ((RunningTeamUpdate) updateWrapper).getProjectCode());
		preparedStatement.setString(8, ((RunningTeamUpdate) updateWrapper).getTeacherIntermediary());
		preparedStatement.setString(9, ((RunningTeamUpdate) updateWrapper).getTeamNumber());
		preparedStatement.setString(10, ((RunningTeamUpdate) updateWrapper).getAcademicLevelCode());
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
			return COLUMN_YEAR + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return COLUMN_PROJECT + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return COLUMN_TEACHER + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return COLUMN_TEAM + " = ?";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return COLUMN_ACADEMICLEVEL + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			preparedStatement.setInt(index, Integer.parseInt(value));
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

		Running running = new Running();
		running.setYear(resultSet.getInt(COLUMN_YEAR));
		runningTeam.setRunning(running);

		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_PROJECT));
		running.setProject(project);

		Teacher teacher = new Teacher();
		teacher.setIntermediary(resultSet.getString(COLUMN_TEACHER));
		running.setTeacher(teacher);

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_TEAM));
		runningTeam.setTeam(team);

		AcademicLevel academicLevel = new AcademicLevel();
		academicLevel.setCode(resultSet.getString(COLUMN_ACADEMICLEVEL));
		runningTeam.setAcademicLevel(academicLevel);

		return runningTeam;
	}
}
