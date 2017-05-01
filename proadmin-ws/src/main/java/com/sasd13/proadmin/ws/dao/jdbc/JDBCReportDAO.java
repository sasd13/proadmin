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
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.AcademicLevel;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.RunningTeam;
import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.bean.update.ReportUpdate;
import com.sasd13.proadmin.ws.dao.IReportDAO;

/**
 *
 * @author Samir
 */
public class JDBCReportDAO extends JDBCSession<Report> implements IReportDAO {

	@Override
	public long create(Report report) {
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
			return JDBCUtils.insert(this, builder.toString(), report);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(ReportUpdate updateWrapper) {
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
	public void delete(Report report) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), report);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Report> read(Map<String, String[]> criterias) {
		try {
			return JDBCUtils.select(this, TABLE, criterias);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Report> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Report report) throws SQLException {
		preparedStatement.setTimestamp(1, new Timestamp(report.getDateMeeting().getTime()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		preparedStatement.setInt(2, report.getSession());
		preparedStatement.setString(3, report.getComment());
		preparedStatement.setInt(4, report.getRunningTeam().getRunning().getYear());
		preparedStatement.setString(5, report.getRunningTeam().getRunning().getProject().getCode());
		preparedStatement.setString(6, report.getRunningTeam().getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(7, report.getRunningTeam().getTeam().getNumber());
		preparedStatement.setString(8, report.getRunningTeam().getAcademicLevel().getCode());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Report> updateWrapper) throws SQLException {
		Report report = updateWrapper.getWrapped();

		preparedStatement.setTimestamp(1, new Timestamp(report.getDateMeeting().getTime()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		preparedStatement.setInt(2, report.getSession());
		preparedStatement.setString(3, report.getComment());
		preparedStatement.setInt(4, report.getRunningTeam().getRunning().getYear());
		preparedStatement.setString(5, report.getRunningTeam().getRunning().getProject().getCode());
		preparedStatement.setString(6, report.getRunningTeam().getRunning().getTeacher().getIntermediary());
		preparedStatement.setString(7, report.getRunningTeam().getTeam().getNumber());
		preparedStatement.setString(8, report.getRunningTeam().getAcademicLevel().getCode());
		preparedStatement.setString(9, ((ReportUpdate) updateWrapper).getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Report report) throws SQLException {
		preparedStatement.setString(1, report.getNumber());
	}

	@Override
	public String getCondition(String key) {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			return COLUMN_CODE + " = ?";
		} else if (EnumCriteria.DATE.getCode().equalsIgnoreCase(key)) {
			return COLUMN_DATEMEETING + " = ?";
		} else if (EnumCriteria.SESSION.getCode().equalsIgnoreCase(key)) {
			return COLUMN_SESSION + " = ?";
		} else if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			return COLUMN_YEAR + " = ?";
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			return COLUMN_PROJECT + " = ?";
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			return COLUMN_TEACHER + " = ?";
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			return COLUMN_TEAM + " = ?";
		} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(key)) {
			return COLUMN_ACADEMICLEVEL + " = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.DATE.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setTimestamp(index, new Timestamp(new DateTime(value).getMillis()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		} else if (EnumCriteria.SESSION.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setInt(index, Integer.parseInt(value));
		} else if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setInt(index, Integer.parseInt(value));
		} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public Report getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();

		report.setNumber(resultSet.getString(COLUMN_CODE));
		report.setDateMeeting(new Date(resultSet.getTimestamp(COLUMN_DATEMEETING).getTime()));
		report.setSession(resultSet.getInt(COLUMN_SESSION));
		report.setComment(resultSet.getString(COLUMN_COMMENT));

		RunningTeam runningTeam = new RunningTeam();
		report.setRunningTeam(runningTeam);

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

		return report;
	}
}
