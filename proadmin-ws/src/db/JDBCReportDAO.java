/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.ReportDAO;
import com.sasd13.proadmin.core.db.util.WhereClauseParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Samir
 */
public class JDBCReportDAO extends JDBCEntityDAO<Report> implements ReportDAO {
	
	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Report report) throws SQLException {
		preparedStatement.setString(1, String.valueOf(report.getDateMeeting()));
		preparedStatement.setInt(2, report.getWeek());
		preparedStatement.setString(3, report.getTeamComment());
		preparedStatement.setLong(4, report.getTeam().getId());
	}
	
	@Override
	protected Report getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();
		
		report.setId(resultSet.getLong(COLUMN_ID));
		report.setDateMeeting(Timestamp.valueOf(resultSet.getString(COLUMN_DATEMEETING)));
		report.setWeek(resultSet.getInt(COLUMN_WEEK));
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
					+ COLUMN_WEEK + ", " 
					+ COLUMN_TEAMCOMMENT + ", " 
					+ COLUMN_TEAM_ID 
				+ ") VALUES (?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, report);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
			}
			
			report.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	@Override
	public void update(Report report) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DATEMEETING + " = ?, " 
					+ COLUMN_WEEK + " = ?, " 
					+ COLUMN_TEAMCOMMENT + " = ?, " 
					+ COLUMN_TEAM_ID + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, report);
			
			preparedStatement.setLong(5, report.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void delete(long id) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Report select(long id) {
		Report report = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (!resultSet.getBoolean(COLUMN_DELETED)) {
					report = getResultSetValues(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return report;
	}
	
	public List<Report> select(Map<String, String[]> parameters) {
		List<Report> list = new ArrayList<>();
		
		String query = null;
		Statement statement = null;
		
		try {
			query = "SELECT * FROM " + TABLE
					+ " WHERE " + WhereClauseParser.parse(ReportDAO.class, parameters) + ";";
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	private void fillListWithResultSet(List<Report> list, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			if (!resultSet.getBoolean(COLUMN_DELETED)) {
				list.add(getResultSetValues(resultSet));
			}
		}
	}
	
	@Override
	public List<Report> selectAll() {
		List<Report> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE;
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
