/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.IndividualEvaluationDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCIndividualEvaluationDAO extends JDBCTableDAO<IndividualEvaluation> implements IndividualEvaluationDAO {

    @Override
    protected PreparedStatement getPreparedStatement(String query, IndividualEvaluation individualEvaluation) throws SQLException {
        PreparedStatement preparedStatement = super.getPreparedStatement(query, individualEvaluation);
        
        preparedStatement.setFloat(1, individualEvaluation.getMark());
        preparedStatement.setLong(2, individualEvaluation.getStudent().getId());
        preparedStatement.setLong(3, individualEvaluation.getReport().getId());
        
        return preparedStatement;
    }

    @Override
    protected IndividualEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
        IndividualEvaluation individualEvaluation = new IndividualEvaluation();
        
        individualEvaluation.setId(resultSet.getLong(INDIVIDUALEVALUATION_ID));
        individualEvaluation.setMark(resultSet.getFloat(INDIVIDUALEVALUATION_MARK));
        
        Student student = new Student();
        student.setId(resultSet.getLong(STUDENTS_STUDENT_ID));
        individualEvaluation.setStudent(student);
        
        Report report = new Report();
        report.setId(resultSet.getLong(REPORTS_REPORT_ID));
        individualEvaluation.setReport(report);
        
        return individualEvaluation;
    }

    @Override
    public long insert(IndividualEvaluation individualEvaluation) {
        long id = 0;
        
        try {
            String query = "INSERT INTO " 
                    + INDIVIDUALEVALUATION_TABLE_NAME + "("
                        + INDIVIDUALEVALUATION_MARK + ", "
                        + STUDENTS_STUDENT_ID + ", "
                        + REPORTS_REPORT_ID + ")"
                    + " VALUES (?, ?, ?)";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, individualEvaluation);
            
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
    public void update(IndividualEvaluation individualEvaluation) {
        try {
            String query = "UPDATE " 
                    + INDIVIDUALEVALUATION_TABLE_NAME
                    + " SET "
                        + INDIVIDUALEVALUATION_MARK + " = ?, "
                        + STUDENTS_STUDENT_ID + " = ?, "
                        + REPORTS_REPORT_ID + " = ?, "
                    + " WHERE "
                    	+ INDIVIDUALEVALUATION_ID + " = ?";
            
            PreparedStatement preparedStatement = getPreparedStatement(query, individualEvaluation);
            preparedStatement.setLong(4, individualEvaluation.getId());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByReport(long reportId) {
        try {
            String query = "DELTE FROM " 
                    + INDIVIDUALEVALUATION_TABLE_NAME
                    + " WHERE "
                    	+ REPORTS_REPORT_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, reportId);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IndividualEvaluation select(long id) {
        IndividualEvaluation individualEvaluation = null;
        
        try {			
            String query = "SELECT * FROM " 
                    + INDIVIDUALEVALUATION_TABLE_NAME
                    + " WHERE "
                        + INDIVIDUALEVALUATION_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	individualEvaluation = getResultSetValues(resultSet);
            }
			
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return individualEvaluation;
    }

    @Override
    public List<IndividualEvaluation> selectByReport(long reportId) {
        List<IndividualEvaluation> list = new ArrayList<>();
        
        try {			
            String query = "SELECT * FROM " 
                    + INDIVIDUALEVALUATION_TABLE_NAME
                    + " WHERE "
                        + REPORTS_REPORT_ID + " = ?";
            
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setLong(1, reportId);
        
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
