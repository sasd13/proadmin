/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.joda.time.DateTime;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.level.IAcademicLevel;
import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.bean.running.IRunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.ws.dao.IReportDAO;

/**
 *
 * @author Samir
 */
public class JDBCReportDAO extends JDBCSession<IReport> implements IReportDAO {

	@Override
	public long create(IReport iReport) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_DATEMEETING);
		builder.append(", " + COLUMN_SESSION);
		builder.append(", " + COLUMN_COMMENT);
		builder.append(", " + COLUMN_YEAR);
		builder.append(", " + COLUMN_PROJECT);
		builder.append(", " + COLUMN_TEACHER);
		builder.append(", " + COLUMN_TEAM);
		builder.append(", " + COLUMN_ACADEMICLEVEL);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), iReport);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(ReportUpdateWrapper updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DATEMEETING + " = ?");
		builder.append(", " + COLUMN_SESSION + " = ?");
		builder.append(", " + COLUMN_COMMENT + " = ?");
		builder.append(", " + COLUMN_YEAR + " = ?");
		builder.append(", " + COLUMN_PROJECT + " = ?");
		builder.append(", " + COLUMN_TEACHER + " = ?");
		builder.append(", " + COLUMN_TEAM + " = ?");
		builder.append(", " + COLUMN_ACADEMICLEVEL + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.update(this, builder.toString(), updateWrapper);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(IReport iReport) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), iReport);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IReport> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IReport> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, IReport iReport) throws SQLException {
		preparedStatement.setTimestamp(1, new Timestamp(iReport.getDateMeeting().getTime()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		preparedStatement.setInt(2, iReport.getSession());
		preparedStatement.setString(3, iReport.getComment());
		preparedStatement.setInt(4, iReport.getRunningTeam().getRunning().getYear());
		preparedStatement.setString(5, iReport.getRunningTeam().getRunning().getProject().getCode());
		preparedStatement.setString(6, iReport.getRunningTeam().getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(7, iReport.getRunningTeam().getTeam().getNumber());
		preparedStatement.setString(8, iReport.getRunningTeam().getAcademicLevel().getCode());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<IReport> updateWrapper) throws SQLException {
		IReport iReport = updateWrapper.getWrapped();

		preparedStatement.setTimestamp(1, new Timestamp(iReport.getDateMeeting().getTime()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		preparedStatement.setInt(2, iReport.getSession());
		preparedStatement.setString(3, iReport.getComment());
		preparedStatement.setInt(4, iReport.getRunningTeam().getRunning().getYear());
		preparedStatement.setString(5, iReport.getRunningTeam().getRunning().getProject().getCode());
		preparedStatement.setString(6, iReport.getRunningTeam().getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(7, iReport.getRunningTeam().getTeam().getNumber());
		preparedStatement.setString(8, iReport.getRunningTeam().getAcademicLevel().getCode());
		preparedStatement.setString(9, ((ReportUpdateWrapper) updateWrapper).getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, IReport iReport) throws SQLException {
		preparedStatement.setString(1, iReport.getNumber());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_CODE + " = ?";
		} else if (EnumParameter.START_DATE.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_DATEMEETING + " >= ?";
		} else if (EnumParameter.END_DATE.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_DATEMEETING + " <= ?";
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_SESSION + " = ?";
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_YEAR + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_PROJECT + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_TEACHER + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_TEAM + " = ?";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_ACADEMICLEVEL + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.START_DATE.getName().equalsIgnoreCase(key)) {
			preparedStatement.setTimestamp(index, new Timestamp(new DateTime(value).getMillis()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		} else if (EnumParameter.END_DATE.getName().equalsIgnoreCase(key)) {
			preparedStatement.setTimestamp(index, new Timestamp(new DateTime(value).getMillis()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			preparedStatement.setInt(index, Integer.parseInt(value));
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
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
	public IReport getResultSetValues(ResultSet resultSet) throws SQLException {
		IReport iReport = new IReport();

		iReport.setNumber(resultSet.getString(COLUMN_CODE));
		iReport.setDateMeeting(new Date(resultSet.getTimestamp(COLUMN_DATEMEETING).getTime()));
		iReport.setSession(resultSet.getInt(COLUMN_SESSION));
		iReport.setComment(resultSet.getString(COLUMN_COMMENT));

		IProject iProject = new IProject();
		iProject.setCode(resultSet.getString(COLUMN_PROJECT));

		ITeacher iTeacher = new ITeacher();
		iTeacher.setIntermediary(resultSet.getString(COLUMN_TEACHER));

		IRunning iRunning = new IRunning();
		iRunning.setYear(resultSet.getInt(COLUMN_YEAR));
		iRunning.setProject(iProject);
		iRunning.setTeacher(iTeacher);

		ITeam iTeam = new ITeam();
		iTeam.setNumber(resultSet.getString(COLUMN_TEAM));

		IAcademicLevel iAcademicLevel = new IAcademicLevel();
		iAcademicLevel.setCode(resultSet.getString(COLUMN_ACADEMICLEVEL));

		IRunningTeam iRunningTeam = new IRunningTeam();
		iRunningTeam.setRunning(iRunning);
		iRunningTeam.setTeam(iTeam);
		iRunningTeam.setAcademicLevel(iAcademicLevel);

		iReport.setRunningTeam(iRunningTeam);

		return iReport;
	}
}
