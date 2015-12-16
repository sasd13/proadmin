/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db;

import com.sasd13.proadmin.core.db.StudentTeamDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO implements StudentTeamDAO {
    
    private Connection connection;
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    protected PreparedStatement getPreparedStatement(String query, long studentId, long teamId) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        
        preparedStatement.setLong(1, studentId);
        preparedStatement.setLong(2, teamId);
        
        return preparedStatement;
    }

    @Override
    public long insertStudentInTeam(long studentId, long teamId) {
        long id = 0;
        
        try {
            String query = "INSERT INTO " 
                    + STUDENTTEAM_TABLE_NAME + "("
                        + STUDENTS_STUDENT_ID + ", "
                        + TEAMS_TEAM_ID + ")"
                    + " VALUES (?, ?)";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, studentId, teamId);
            
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
    public void deleteStudentFromTeam(long studentId, long teamId) {
        try {
            String query = "DELTE FROM " 
                    + STUDENTTEAM_TABLE_NAME
                    + " WHERE "
                    	+ STUDENTS_STUDENT_ID + " = ? AND "
                        + TEAMS_TEAM_ID + " = ?";
            
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, teamId);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Long> selectByTeam(long teamId) {
        List<Long> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + STUDENTTEAM_TABLE_NAME
                    + " WHERE "
                        + TEAMS_TEAM_ID + " = ?";
            
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setLong(1, teamId);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	list.add(resultSet.getLong(STUDENTS_STUDENT_ID));
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
