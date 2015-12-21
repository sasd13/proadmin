/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.ReportDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCReportDAO extends JDBCTableDAO<Report> implements ReportDAO {
	
	@Override
	protected PreparedStatement getPreparedStatement(String query, Report report) throws SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(query, report);
		
		preparedStatement.setString(1, String.valueOf(report.getDateMeeting()));
		preparedStatement.setInt(2, report.getWeekNumber());
		preparedStatement.setString(3, report.getTeamComment());
		preparedStatement.setLong(4, report.getTeam().getId());
		
		return preparedStatement;
	}
	
	@Override
	protected Report getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();
		
		report.setId(resultSet.getLong(REPORT_ID));
		report.setDateMeeting(Timestamp.valueOf(resultSet.getString(REPORT_DATEMEETING)));
		report.setWeekNumber(resultSet.getInt(REPORT_WEEKNUMBER));
		report.setTeamComment(resultSet.getString(REPORT_TEAMCOMMENT));
		
		Team team = new Team();
		team.setId(resultSet.getLong(TEAMS_TEAM_ID));
		report.setTeam(team);
		
		return report;
	}
	
	@Override
	public long insert(Report report) {
		long id = 0;
		
		String query = "INSERT INTO " + REPORT_TABLE_NAME 
				+ "(" 
					+ REPORT_DATEMEETING + ", " 
					+ REPORT_WEEKNUMBER + ", " 
					+ REPORT_TEAMCOMMENT + ", " 
					+ TEAMS_TEAM_ID 
				+ ") VALUES (?, ?, ?, ?)";
		
		try {
			id = executeInsert(query, report);
			report.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(Report report) {
		String query = "UPDATE " + REPORT_TABLE_NAME 
				+ " SET " 
					+ REPORT_DATEMEETING + " = ?, " 
					+ REPORT_WEEKNUMBER + " = ?, " 
					+ REPORT_TEAMCOMMENT + " = ?, " 
					+ TEAMS_TEAM_ID + " = ?, " 
				+ " WHERE " 
					+ REPORT_ID + " = ?";
		
		try {			
			PreparedStatement preparedStatement = getPreparedStatement(query, report);
			preparedStatement.setLong(5, report.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(long id) {
		String query = "UPDATE " + REPORT_TABLE_NAME
				+ " SET " 
					+ DELETED + " = ?" 
				+ " WHERE " 
					+ REPORT_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Report select(long id) {
		Report report = null;
		
		String query = "SELECT * FROM " + REPORT_TABLE_NAME 
				+ " WHERE " 
					+ REPORT_ID + " = ?";
		
		try {
			report = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return report;
	}
	
	@Override
	public List<Report> selectAll() {
		List<Report> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + REPORT_TABLE_NAME;
		
		try {			
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<Report> selectByTeam(long teamId) {
		List<Report> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + REPORT_TABLE_NAME 
				+ " WHERE " 
					+ TEAMS_TEAM_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, teamId);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
