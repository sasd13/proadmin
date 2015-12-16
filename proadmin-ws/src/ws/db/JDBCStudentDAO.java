/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.db.StudentDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCStudentDAO extends JDBCTableDAO<Student> implements StudentDAO {

    @Override
    protected PreparedStatement getPreparedStatement(String query, Student student) throws SQLException {
        PreparedStatement preparedStatement = super.getPreparedStatement(query, student);
        
        preparedStatement.setString(1, student.getNumber());
        preparedStatement.setString(2, String.valueOf(student.getAcademicLevel()));
        preparedStatement.setString(3, student.getFirstName());
        preparedStatement.setString(4, student.getLastName());
        preparedStatement.setString(5, student.getEmail());
        
        return preparedStatement;
    }

    @Override
    protected Student getResultSetValues(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        
        student.setId(resultSet.getLong(STUDENT_ID));
        student.setNumber(resultSet.getString(STUDENT_NUMBER));
        student.setAcademicLevel(AcademicLevel.valueOf(resultSet.getString(STUDENT_ACADEMICLEVEL)));
        student.setFirstName(resultSet.getString(STUDENT_FIRSTNAME));
        student.setLastName(resultSet.getString(STUDENT_LASTNAME));
        student.setEmail(resultSet.getString(STUDENT_EMAIL));
        
        return student;
    }

    @Override
    public long insert(Student student) {
        long id = 0;
        
        try {
            String query = "INSERT INTO " 
                    + STUDENT_TABLE_NAME + "("
                        + STUDENT_NUMBER + ", "
                        + STUDENT_ACADEMICLEVEL + ", "
                        + STUDENT_FIRSTNAME + ", "
                        + STUDENT_LASTNAME + ", "
                        + STUDENT_EMAIL + ")"
                    + " VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, student);
            
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
    public void update(Student student) {
        try {
            String query = "UPDATE " 
                    + STUDENT_TABLE_NAME
                    + " SET "
                        + STUDENT_NUMBER + " = ?, "
                        + STUDENT_ACADEMICLEVEL + " = ?, "
                        + STUDENT_FIRSTNAME + " = ?, "
                        + STUDENT_LASTNAME + " = ?, "
                        + STUDENT_EMAIL + " = ?, "
                    + " WHERE "
                    	+ STUDENT_ID + " = ?";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, student);
            preparedStatement.setLong(6, student.getId());
            
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
                    + STUDENT_TABLE_NAME
                    + " WHERE "
                        + STUDENT_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
			
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student select(long id) {
        Student student = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + STUDENT_TABLE_NAME
                    + " WHERE "
                        + STUDENT_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	student = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return student;
    }

    @Override
    public Student selectByNumber(String number) {
        Student student = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + STUDENT_TABLE_NAME
                    + " WHERE "
                        + STUDENT_NUMBER + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, number);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	student = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return student;
    }
    
    @Override
	public Student selectByEmail(String email) {
    	Student student = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + STUDENT_TABLE_NAME
                    + " WHERE "
                        + STUDENT_EMAIL + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	student = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return student;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + STUDENT_TABLE_NAME;
            
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
