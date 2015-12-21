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
		
		String query = "INSERT INTO " + PROJECT_TABLE_NAME 
				+ "(" 
					+ PROJECT_CODE + ", " 
					+ PROJECT_ACADEMICLEVEL + ", " 
					+ PROJECT_TITLE + ", "
					+ PROJECT_DESCRIPTION 
				+ ") VALUES (?, ?, ?, ?)";
		
		try {
			id = executeInsert(query, project);
			project.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(Project project) {
		String query = "UPDATE " + PROJECT_TABLE_NAME 
				+ " SET " 
					+ PROJECT_CODE + " = ?, " 
					+ PROJECT_ACADEMICLEVEL + " = ?, " 
					+ PROJECT_TITLE + " = ?, " 
					+ PROJECT_DESCRIPTION + " = ?, " 
				+ " WHERE " 
					+ PROJECT_ID + " = ?";
		
		try {			
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
		String query = "UPDATE " + PROJECT_TABLE_NAME 
				+ " SET " 
					+ DELETED + " = ?" 
				+ " WHERE " 
					+ PROJECT_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Project select(long id) {
		Project project = null;
		
		String query = "SELECT * FROM " + PROJECT_TABLE_NAME 
				+ " WHERE " 
					+ PROJECT_ID + " = ?";
		
		try {
			project = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return project;
	}
	
	@Override
	public List<Project> selectAll() {
		List<Project> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + PROJECT_TABLE_NAME;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public Project selectByCode(String code) {
		Project project = null;
		
		String query = "SELECT * FROM " + PROJECT_TABLE_NAME 
				+ " WHERE " 
					+ PROJECT_CODE + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, code);
			
			project = executeSelectSingleResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return project;
	}
	
	@Override
	public List<Project> selectByAcademicLevel(AcademicLevel academicLevel) {
		List<Project> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + PROJECT_TABLE_NAME 
				+ " WHERE " 
					+ PROJECT_ACADEMICLEVEL + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, String.valueOf(academicLevel));
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
