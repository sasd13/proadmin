/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.ProjectDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCProjectDAO extends JDBCTableDAO<Project> implements ProjectDAO {

    @Override
    protected PreparedStatement getPreparedStatement(String query, Project project) throws SQLException {
        PreparedStatement preparedStatement = super.getPreparedStatement(query, project);
        
        preparedStatement.setString(1, project.getCode());
        preparedStatement.setString(2, String.valueOf(project.getAcademicLevel()));
        preparedStatement.setString(3, project.getTitle());
        preparedStatement.setString(4, project.getDescription());
        
        return preparedStatement;
    }

    @Override
    protected Project getResultSetValues(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        
        project.setId(resultSet.getLong(PROJECT_ID));
        project.setCode(resultSet.getString(PROJECT_CODE));
        project.setAcademicLevel(AcademicLevel.valueOf(resultSet.getString(PROJECT_ACADEMICLEVEL)));
        project.setTitle(resultSet.getString(PROJECT_TITLE));
        project.setDescription(resultSet.getString(PROJECT_DESCRIPTION));
        
        return project;
    }

    @Override
    public long insert(Project project) {
        long id = 0;
        
        try {
            String query = "INSERT INTO " 
                    + PROJECT_TABLE_NAME + "("
                        + PROJECT_CODE + ", "
                        + PROJECT_ACADEMICLEVEL + ", "
                        + PROJECT_TITLE + ", "
                        + PROJECT_DESCRIPTION + ")"
                    + " VALUES (?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, project);
            
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
    public void update(Project project) {
        try {
            String query = "UPDATE " 
                    + PROJECT_TABLE_NAME
                    + " SET "
                        + PROJECT_CODE + " = ?, "
                        + PROJECT_ACADEMICLEVEL + " = ?, "
                        + PROJECT_TITLE + " = ?, "
                        + PROJECT_DESCRIPTION + " = ?, "
                    + " WHERE "
                    	+ PROJECT_ID + " = ?";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, project);
            preparedStatement.setLong(5, project.getId());
            
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
                    + PROJECT_TABLE_NAME
                    + " WHERE "
                    	+ PROJECT_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project select(long id) {
        Project project = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + PROJECT_TABLE_NAME
                    + " WHERE "
                        + PROJECT_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	project = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return project;
    }
    
    @Override
	public Project selectByCode(String code) {
    	Project project = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + PROJECT_TABLE_NAME
                    + " WHERE "
                        + PROJECT_CODE + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, code);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	project = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return project;
	}

    @Override
    public List<Project> selectByAcademicLevel(AcademicLevel academicLevel) {
        List<Project> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + PROJECT_TABLE_NAME
                    + " WHERE "
                        + PROJECT_ACADEMICLEVEL + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(academicLevel));
        
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
    public List<Project> selectAll() {
        List<Project> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + PROJECT_TABLE_NAME;
            
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
