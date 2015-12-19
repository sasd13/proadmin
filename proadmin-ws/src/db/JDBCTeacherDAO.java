/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.db.TeacherDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCTeacherDAO extends JDBCTableDAO<Teacher> implements TeacherDAO {

    @Override
    protected PreparedStatement getPreparedStatement(String query, Teacher teacher) throws SQLException {
        PreparedStatement preparedStatement = super.getPreparedStatement(query, teacher);
        
        preparedStatement.setString(1, teacher.getNumber());
        preparedStatement.setString(2, teacher.getFirstName());
        preparedStatement.setString(3, teacher.getLastName());
        preparedStatement.setString(4, teacher.getEmail());
        preparedStatement.setString(5, teacher.getPassword());
        
        return preparedStatement;
    }

    @Override
    protected Teacher getResultSetValues(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        
        teacher.setId(resultSet.getLong(TEACHER_ID));
        teacher.setNumber(resultSet.getString(TEACHER_NUMBER));
        teacher.setFirstName(resultSet.getString(TEACHER_FIRSTNAME));
        teacher.setLastName(resultSet.getString(TEACHER_LASTNAME));
        teacher.setEmail(resultSet.getString(TEACHER_EMAIL));
        teacher.setPassword(resultSet.getString(TEACHER_PASSWORD));
        
        return teacher;
    }

    @Override
    public long insert(Teacher teacher) {
        long id = 0;
        
        try {
            String query = "INSERT INTO " 
                    + TEACHER_TABLE_NAME + "("
                        + TEACHER_NUMBER + ", "
                        + TEACHER_FIRSTNAME + ", "
                        + TEACHER_LASTNAME + ", "
                        + TEACHER_EMAIL + ", "
                        + TEACHER_PASSWORD + ")"
                    + " VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, teacher);
            
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
    public void update(Teacher teacher) {
        try {
            String query = "UPDATE " 
                    + TEACHER_TABLE_NAME
                    + " SET "
                        + TEACHER_NUMBER + " = ?, "
                        + TEACHER_FIRSTNAME + " = ?, "
                        + TEACHER_LASTNAME + " = ?, "
                        + TEACHER_EMAIL + " = ?, "
                        + TEACHER_PASSWORD + " = ?, "
                    + " WHERE "
                    	+ TEACHER_ID + " = ?";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, teacher);
            preparedStatement.setLong(6, teacher.getId());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {			
            String query = "DELETE FROM " 
                    + TEACHER_TABLE_NAME
                    + " WHERE "
                        + TEACHER_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
			
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher select(long id) {
        Teacher teacher = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + TEACHER_TABLE_NAME
                    + " WHERE "
                        + TEACHER_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	teacher = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return teacher;
    }

    @Override
    public Teacher selectByNumber(String number) {
        Teacher teacher = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + TEACHER_TABLE_NAME
                    + " WHERE "
                        + TEACHER_NUMBER + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, number);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	teacher = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return teacher;
    }

    @Override
    public List<Teacher> selectByEmail(String email) {
        List<Teacher> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + TEACHER_TABLE_NAME
                    + " WHERE "
                        + TEACHER_EMAIL + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
        
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
    public List<Teacher> selectAll() {
        List<Teacher> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + TEACHER_TABLE_NAME;
            
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
