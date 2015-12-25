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
public class JDBCReportDAO extends JDBCEntityDAO<Report> implements ReportDAO {
	
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
		
		report.setId(resultSet.getLong(COLUMN_ID));
		report.setDateMeeting(Timestamp.valueOf(resultSet.getString(COLUMN_DATEMEETING)));
		report.setWeekNumber(resultSet.getInt(COLUMN_WEEKNUMBER));
		report.setTeamComment(resultSet.getString(COLUMN_TEAMCOMMENT));
		
		Team team = new Team();
		team.setId(resultSet.getLong(COLUMN_TEAM_ID));
		report.setTeam(team);
		
		return report;
	}
	
	@Override
	public long insert(Report report) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_DATEMEETING + ", "
					+ COLUMN_WEEKNUMBER + ", " 
					+ COLUMN_TEAMCOMMENT + ", " 
					+ COLUMN_TEAM_ID 
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
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DATEMEETING + " = ?, " 
					+ COLUMN_WEEKNUMBER + " = ?, " 
					+ COLUMN_TEAMCOMMENT + " = ?, " 
					+ COLUMN_TEAM_ID + " = ?, " 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Report select(long id) {
		Report report = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
		
		String query = "SELECT * FROM " + TABLE;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
