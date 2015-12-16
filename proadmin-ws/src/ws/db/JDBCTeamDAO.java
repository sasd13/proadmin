/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db;

import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.TeamDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCTeamDAO extends JDBCTableDAO<Team> implements TeamDAO {

    @Override
    protected PreparedStatement getPreparedStatement(String query, Team team) throws SQLException {
        PreparedStatement preparedStatement = super.getPreparedStatement(query, team);
        
        preparedStatement.setString(1, team.getCode());
        preparedStatement.setLong(2, team.getRunning().getId());
        
        return preparedStatement;
    }

    @Override
    protected Team getResultSetValues(ResultSet resultSet) throws SQLException {
        Team team = new Team();
        
        team.setId(resultSet.getLong(TEAM_ID));
        team.setCode(resultSet.getString(TEAM_CODE));
        
        Running running = new Running();
        running.setId(resultSet.getLong(RUNNINGS_RUNNING_ID));
        team.setRunning(running);
        
        return team;
    }

    @Override
    public long insert(Team team) {
        long id = 0;
        
        try {
            String query = "INSERT INTO " 
                    + TEAM_TABLE_NAME + "("
                        + TEAM_CODE + ", "
                        + RUNNINGS_RUNNING_ID + ")"
                    + " VALUES (?, ?)";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, team);
            
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
    public void update(Team team) {
        try {
            String query = "UPDATE " 
                    + TEAM_TABLE_NAME
                    + " SET "
                        + TEAM_CODE + " = ?, "
                        + RUNNINGS_RUNNING_ID + " = ?, "
                    + " WHERE "
                    	+ TEAM_ID + " = ?";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, team);
            preparedStatement.setLong(3, team.getId());
            
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
                    + TEAM_TABLE_NAME
                    + " WHERE "
                    	+ TEAM_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Team select(long id) {
        Team team = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + TEAM_TABLE_NAME
                    + " WHERE "
                        + TEAM_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	team = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return team;
    }

    @Override
    public List<Team> selectByRunning(long runningId) {
        List<Team> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + TEAM_TABLE_NAME
                    + " WHERE "
                        + RUNNINGS_RUNNING_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, runningId);
        
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
    public List<Team> selectAll() {
        List<Team> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + TEAM_TABLE_NAME;
            
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
