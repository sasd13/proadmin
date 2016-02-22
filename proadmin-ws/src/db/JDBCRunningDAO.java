/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.db.RunningDAO;
import com.sasd13.proadmin.core.db.util.WhereClauseParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCEntityDAO<Running> implements RunningDAO {
	
	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setLong(2, running.getTeacher().getId());
		preparedStatement.setLong(3, running.getProject().getId());
	}
	
	@Override
	protected Running getResultSetValues(ResultSet resultSet) throws SQLException {
		Running running = new Running();
		
		running.setId(resultSet.getLong(COLUMN_ID));
		running.setYear(resultSet.getInt(COLUMN_YEAR));
		
		Teacher teacher = new Teacher();
		teacher.setId(resultSet.getLong(COLUMN_TEACHER_ID));
		running.setTeacher(teacher);
		
		Project project = new Project();
		project.setId(resultSet.getLong(COLUMN_PROJECT_ID));
		running.setProject(project);
		
		return running;
	}
	
	@Override
	public long insert(Running running) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "("
					+ COLUMN_YEAR + ", "
					+ COLUMN_TEACHER_ID + ", "
					+ COLUMN_PROJECT_ID 
				+ ") VALUES (?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, running);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
			}
			
			running.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	@Override
	public void update(Running running) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_YEAR + " = ?, " 
					+ COLUMN_TEACHER_ID + " = ?, " 
					+ COLUMN_PROJECT_ID + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, running);
			
			preparedStatement.setLong(4, running.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
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
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Running select(long id) {
		Running running = null;
		
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
					running = getResultSetValues(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return running;
	}
	
	public List<Running> select(Map<String, String[]> parameters) {
		List<Running> list = new ArrayList<>();
		
		String query = null;
		Statement statement = null;
		
		try {
			query = "SELECT * FROM " + TABLE
					+ " WHERE " + WhereClauseParser.parse(RunningDAO.class, parameters) + ";";
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	private void fillListWithResultSet(List<Running> list, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			if (!resultSet.getBoolean(COLUMN_DELETED)) {
				list.add(getResultSetValues(resultSet));
			}
		}
	}
	
	@Override
	public List<Running> selectAll() {
		List<Running> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE;
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				if (!resultSet.getBoolean(COLUMN_DELETED)) {
					list.add(getResultSetValues(resultSet));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
