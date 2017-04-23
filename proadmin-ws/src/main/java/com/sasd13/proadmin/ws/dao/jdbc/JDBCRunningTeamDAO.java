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
import com.sasd13.proadmin.bean.level.IAcademicLevel;
import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.bean.running.IRunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws.dao.IRunningTeamDAO;

/**
 *
 * @author Samir
 */
public class JDBCRunningTeamDAO extends JDBCSession<IRunningTeam> implements IRunningTeamDAO {

	@Override
	public long create(IRunningTeam iRunningTeam) {
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
			return JDBCUtils.insert(this, builder.toString(), iRunningTeam);
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
	public void delete(IRunningTeam iRunningTeam) {
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
			JDBCUtils.delete(this, builder.toString(), iRunningTeam);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IRunningTeam> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IRunningTeam> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, IRunningTeam iRunningTeam) throws SQLException {
		preparedStatement.setInt(1, iRunningTeam.getRunning().getYear());
		preparedStatement.setString(2, iRunningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(3, iRunningTeam.getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(4, iRunningTeam.getTeam().getNumber());
		preparedStatement.setString(5, iRunningTeam.getAcademicLevel().getCode());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<IRunningTeam> updateWrapper) throws SQLException {
		// RunningTeam runningTeam = updateWrapper.getWrapped();
		//
		// preparedStatement.setInt(6, ((RunningTeamUpdateWrapper) updateWrapper).getRunningYear());
		// preparedStatement.setString(7, ((RunningTeamUpdateWrapper) updateWrapper).getProjectCode());
		// preparedStatement.setString(8, ((RunningTeamUpdateWrapper) updateWrapper).getTeacherNumber());
		// preparedStatement.setString(9, ((RunningTeamUpdateWrapper) updateWrapper).getTeamNumber());
		// preparedStatement.setString(10, ((RunningTeamUpdateWrapper) updateWrapper).getAcademicLevelCode());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, IRunningTeam iRunningTeam) throws SQLException {
		preparedStatement.setInt(1, iRunningTeam.getRunning().getYear());
		preparedStatement.setString(2, iRunningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(3, iRunningTeam.getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(4, iRunningTeam.getTeam().getNumber());
		preparedStatement.setString(5, iRunningTeam.getAcademicLevel().getCode());
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
	public IRunningTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		IRunningTeam iRunningTeam = new IRunningTeam();

		IProject iProject = new IProject();
		iProject.setCode(resultSet.getString(COLUMN_PROJECT));

		ITeacher iTeacher = new ITeacher();
		iTeacher.setIntermediary(resultSet.getString(COLUMN_TEACHER));

		IRunning iRunning = new IRunning();
		iRunning.setYear(resultSet.getInt(COLUMN_YEAR));
		iRunning.setProject(iProject);
		iRunning.setTeacher(iTeacher);

		iRunningTeam.setRunning(iRunning);

		ITeam iTeam = new ITeam();
		iTeam.setNumber(resultSet.getString(COLUMN_TEAM));

		iRunningTeam.setTeam(iTeam);

		IAcademicLevel iAcademicLevel = new IAcademicLevel();
		iAcademicLevel.setCode(resultSet.getString(COLUMN_ACADEMICLEVEL));

		iRunningTeam.setAcademicLevel(iAcademicLevel);

		return iRunningTeam;
	}
}
