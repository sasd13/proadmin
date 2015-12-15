/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db.mysql;

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
public class MySQLReportDAO extends MySQLTableDAO<Report> implements ReportDAO {

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
        
        try {
            String query = "INSERT INTO " 
                    + REPORT_TABLE_NAME + "("
                        + REPORT_DATEMEETING + ", "
                        + REPORT_WEEKNUMBER + ", "
                        + REPORT_TEAMCOMMENT + ", "
                        + TEAMS_TEAM_ID + ")"
                    + " VALUES (?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, report);
            
            long affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {	        	
	        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	        	
	        if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
	        }
            }
            
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return id;
    }

    @Override
    public void update(Report report) {
        try {
            String query = "UPDATE " 
                    + REPORT_TABLE_NAME
                    + " SET "
                        + REPORT_DATEMEETING + " = ?, "
                        + REPORT_WEEKNUMBER + " = ?, "
                        + REPORT_TEAMCOMMENT + " = ?, "
                        + TEAMS_TEAM_ID + " = ?, "
                    + " WHERE "
			+ REPORT_ID + " = ?";
            
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
        try {
            String query = "DELTE FROM " 
                    + REPORT_TABLE_NAME
                    + " WHERE "
			+ REPORT_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Report select(long id) {
        Report report = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + REPORT_TABLE_NAME
                    + " WHERE "
                        + REPORT_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
		report = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return report;
    }

    @Override
    public List<Report> selectByTeam(long teamId) {
        List<Report> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + REPORT_TABLE_NAME
                    + " WHERE "
                        + TEAMS_TEAM_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, teamId);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
		list.add(getResultSetValues(resultSet));
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<Report> selectAll() {
        List<Report> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + REPORT_TABLE_NAME;
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
		list.add(getResultSetValues(resultSet));
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
