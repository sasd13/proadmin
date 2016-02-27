/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.db.StudentDAO;
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
public class JDBCStudentDAO extends JDBCEntityDAO<Student> implements StudentDAO {
	
	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Student student) throws SQLException {
		preparedStatement.setString(1, student.getNumber());
		preparedStatement.setString(2, String.valueOf(student.getAcademicLevel()));
		preparedStatement.setString(3, student.getFirstName());
		preparedStatement.setString(4, student.getLastName());
		preparedStatement.setString(5, student.getEmail());
	}
	
	@Override
	protected Student getResultSetValues(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		
		student.setId(resultSet.getLong(COLUMN_ID));
		student.setNumber(resultSet.getString(COLUMN_NUMBER));
		student.setAcademicLevel(AcademicLevel.valueOf(resultSet.getString(COLUMN_ACADEMICLEVEL)));
		student.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		student.setLastName(resultSet.getString(COLUMN_LASTNAME));
		student.setEmail(resultSet.getString(COLUMN_EMAIL));
		
		return student;
	}
	
	@Override
	public long insert(Student student) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_NUMBER + ", " 
					+ COLUMN_ACADEMICLEVEL + ", " 
					+ COLUMN_FIRSTNAME + ", " 
					+ COLUMN_LASTNAME + ", " 
					+ COLUMN_EMAIL 
				+ ") VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, student);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
			}
			
			student.setId(id);
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
	public void update(Student student) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_NUMBER + " = ?, " 
					+ COLUMN_ACADEMICLEVEL + " = ?, " 
					+ COLUMN_FIRSTNAME + " = ?, " 
					+ COLUMN_LASTNAME + " = ?, " 
					+ COLUMN_EMAIL + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, student);
			
			preparedStatement.setLong(6, student.getId());
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
	public Student select(long id) {
		Student student = null;
		
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
					student = getResultSetValues(resultSet);
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
		
		return student;
	}
	
	public List<Student> select(Map<String, String[]> parameters) {
		List<Student> list = new ArrayList<>();
		
		String query = null;
		Statement statement = null;
		
		try {
			query = "SELECT * FROM " + TABLE
					+ " WHERE " + WhereClauseParser.parse(StudentDAO.class, parameters) + ";";
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
	
	private void fillListWithResultSet(List<Student> list, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			if (!resultSet.getBoolean(COLUMN_DELETED)) {
				list.add(getResultSetValues(resultSet));
			}
		}
	}
	
	@Override
	public List<Student> selectAll() {
		List<Student> list = new ArrayList<>();
		
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
