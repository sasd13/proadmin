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
public class JDBCProjectDAO extends JDBCEntityDAO<Project> implements ProjectDAO {
	
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
		
		project.setId(resultSet.getLong(COLUMN_ID));
		project.setCode(resultSet.getString(COLUMN_CODE));
		project.setAcademicLevel(AcademicLevel.valueOf(resultSet.getString(COLUMN_ACADEMICLEVEL)));
		project.setTitle(resultSet.getString(COLUMN_TITLE));
		project.setDescription(resultSet.getString(COLUMN_DESCRIPTION));
		
		return project;
	}
	
	@Override
	public long insert(Project project) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_CODE + ", " 
					+ COLUMN_ACADEMICLEVEL + ", " 
					+ COLUMN_TITLE + ", " 
					+ COLUMN_DESCRIPTION 
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
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_CODE + " = ?, " 
					+ COLUMN_ACADEMICLEVEL + " = ?, " 
					+ COLUMN_TITLE + " = ?, " 
					+ COLUMN_DESCRIPTION + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
	public Project select(long id) {
		Project project = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
		
		String query = "SELECT * FROM " + TABLE;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
