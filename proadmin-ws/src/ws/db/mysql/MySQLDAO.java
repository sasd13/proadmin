/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.sasd13.proadmin.core.db.DAO;

/**
 *
 * @author Samir
 */
public class MySQLDAO extends DAO {
    
    private static MySQLDAO instance = null;
    
    private static final String HOSTNAME = "localhost:3306";
    private static final String DBNAME ="proadmin";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + "/" + DBNAME;
    private static final String USERNAME ="root"; 
    private static final String PASSWORD ="";
	
    private Connection connection;
    
    private MySQLDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            teacherDAO = new MySQLTeacherDAO();
            projectDAO = new MySQLProjectDAO();
            runningDAO = new MySQLRunningDAO();
            teamDAO = new MySQLTeamDAO();
            studentDAO = new MySQLStudentDAO();
            studentTeamDAO = new MySQLStudentTeamDAO();
            reportDAO = new MySQLReportDAO();
            leadEvaluationDAO = new MySQLLeadEvaluationDAO();
            individualEvaluationDAO = new MySQLIndividualEvaluationDAO();
	
            open();
            createTablesIfNotExist();
            close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static MySQLDAO getInstance() {
        if (instance == null) {
            instance = new MySQLDAO();
        }
        
        return instance;
    }

    @Override
    public void open() {
        try {
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            ((MySQLTeacherDAO) teacherDAO).setConnection(this.connection);
            ((MySQLProjectDAO) projectDAO).setConnection(this.connection);
            ((MySQLRunningDAO) runningDAO).setConnection(this.connection);
            ((MySQLTeamDAO) teamDAO).setConnection(this.connection);
            ((MySQLStudentDAO) studentDAO).setConnection(this.connection);
            ((MySQLStudentTeamDAO) studentTeamDAO).setConnection(this.connection);
            ((MySQLReportDAO) reportDAO).setConnection(this.connection);
            ((MySQLLeadEvaluationDAO) leadEvaluationDAO).setConnection(this.connection);
            ((MySQLIndividualEvaluationDAO) individualEvaluationDAO).setConnection(this.connection);
        } catch (SQLException e) {
            e.printStackTrace();
	}
    }

    @Override
    public void close() {
        try {
            this.connection.close();
	} catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void createTablesIfNotExist() {
	try {
            Statement statement = this.connection.createStatement();
		
            statement.executeUpdate(MySQLDBHandler.TEACHER_TABLE_CREATE);
            statement.executeUpdate(MySQLDBHandler.PROJECT_TABLE_CREATE);
            statement.executeUpdate(MySQLDBHandler.RUNNING_TABLE_CREATE);
            statement.executeUpdate(MySQLDBHandler.TEAM_TABLE_CREATE);
            statement.executeUpdate(MySQLDBHandler.STUDENT_TABLE_CREATE);
            statement.executeUpdate(MySQLDBHandler.STUDENTTEAM_TABLE_CREATE);
            statement.executeUpdate(MySQLDBHandler.REPORT_TABLE_CREATE);
            statement.executeUpdate(MySQLDBHandler.LEADEVALUATION_TABLE_CREATE);
            statement.executeUpdate(MySQLDBHandler.INDIVIDUALEVALUATION_TABLE_CREATE);
			
            statement.close();
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }
}
