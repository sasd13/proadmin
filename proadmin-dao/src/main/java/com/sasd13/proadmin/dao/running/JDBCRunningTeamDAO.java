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
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.dao.jdbc.ConditionException;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.builder.running.RunningTeamBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCRunningTeamDAO extends JDBCSession<RunningTeam> implements IRunningTeamDAO {

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
	public void update(IUpdateWrapper<RunningTeam> updateWrapper) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_RUNNING_YEAR + " = ?");
		builder.append(", " + COLUMN_RUNNING_PROJECT_CODE + " = ?");
		builder.append(", " + COLUMN_RUNNING_TEACHER_CODE + " = ?");
		builder.append(", " + COLUMN_TEAM_CODE + " = ?");
		builder.append(", " + COLUMN_ACADEMICLEVEL_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_RUNNING_YEAR + " = ?");
		builder.append(" AND " + COLUMN_RUNNING_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_RUNNING_TEACHER_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEAM_CODE + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL_CODE + " = ?");

		JDBCUtils.update(this, builder.toString(), updateWrapper);
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
		return JDBCUtils.select(this, TABLE, parameters);
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
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<RunningTeam> updateWrapper) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, updateWrapper.getWrapped());

		preparedStatement.setInt(6, ((IRunningTeamUpdateWrapper) updateWrapper).getRunningYear());
		preparedStatement.setString(7, ((IRunningTeamUpdateWrapper) updateWrapper).getProjectCode());
		preparedStatement.setString(8, ((IRunningTeamUpdateWrapper) updateWrapper).getTeacherNumber());
		preparedStatement.setString(9, ((IRunningTeamUpdateWrapper) updateWrapper).getTeamNumber());
		preparedStatement.setString(10, ((IRunningTeamUpdateWrapper) updateWrapper).getAcademicLevelCode());
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
	public String buildCondition(String key) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_RUNNING_YEAR + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_RUNNING_PROJECT_CODE + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_RUNNING_TEACHER_CODE + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_TEAM_CODE + " = ?";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_ACADEMICLEVEL_CODE + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
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
		RunningTeam runningTeam = new RunningTeamBaseBuilder(resultSet.getInt(COLUMN_RUNNING_YEAR), resultSet.getString(COLUMN_RUNNING_PROJECT_CODE), resultSet.getString(COLUMN_RUNNING_TEACHER_CODE), resultSet.getString(COLUMN_TEAM_CODE), resultSet.getString(COLUMN_ACADEMICLEVEL_CODE)).build();

		return runningTeam;
	}
}
