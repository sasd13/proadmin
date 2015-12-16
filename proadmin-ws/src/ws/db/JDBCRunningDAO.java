/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.db.RunningDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCTableDAO<Running> implements RunningDAO {

    @Override
    protected PreparedStatement getPreparedStatement(String query, Running running) throws SQLException {
        PreparedStatement preparedStatement = super.getPreparedStatement(query, running);
        
        preparedStatement.setInt(1, running.getYear());
        preparedStatement.setLong(2, running.getTeacher().getId());
        preparedStatement.setLong(3, running.getProject().getId());
        
        return preparedStatement;
    }

    @Override
    protected Running getResultSetValues(ResultSet resultSet) throws SQLException {
        Running running = new Running();
        
        running.setId(resultSet.getLong(RUNNING_ID));
        running.setYear(resultSet.getInt(RUNNING_YEAR));
        
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong(TEACHERS_TEACHER_ID));
        running.setTeacher(teacher);
        
        Project project = new Project();
        project.setId(resultSet.getLong(PROJECTS_PROJECT_ID));
        running.setProject(project);
        
        return running;
    }

    @Override
    public long insert(Running running) {
        long id = 0;
        
        try {
            String query = "INSERT INTO " 
                    + RUNNING_TABLE_NAME + "("
                        + RUNNING_YEAR + ", "
                        + TEACHERS_TEACHER_ID + ", "
                        + PROJECTS_PROJECT_ID + ")"
                    + " VALUES (?, ?, ?)";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, running);
            
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
    public void update(Running running) {
        try {
            String query = "UPDATE " 
                    + RUNNING_TABLE_NAME
                    + " SET "
                        + RUNNING_YEAR + " = ?, "
                        + TEACHERS_TEACHER_ID + " = ?, "
                        + PROJECTS_PROJECT_ID + " = ?, "
                    + " WHERE "
                    	+ RUNNING_ID + " = ?";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, running);
            preparedStatement.setLong(4, running.getId());
            
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
                    + RUNNING_TABLE_NAME
                    + " WHERE "
                    	+ RUNNING_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Running select(long id) {
        Running running = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + RUNNING_TABLE_NAME
                    + " WHERE "
                        + RUNNING_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	running = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return running;
    }

    @Override
    public List<Running> selectByTeacher(long teacherId) {
        List<Running> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + RUNNING_TABLE_NAME
                    + " WHERE "
                        + TEACHERS_TEACHER_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, teacherId);
        
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
    public List<Running> selectAll() {
        List<Running> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + RUNNING_TABLE_NAME;
            
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
