/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCLeadEvaluationDAO extends JDBCTableDAO<LeadEvaluation> implements LeadEvaluationDAO {
	
	@Override
	protected PreparedStatement getPreparedStatement(String query, LeadEvaluation leadEvaluation) throws SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(query, leadEvaluation);
		
		preparedStatement.setFloat(1, leadEvaluation.getPlanningMark());
		preparedStatement.setString(2, leadEvaluation.getPlanningComment());
		preparedStatement.setFloat(3, leadEvaluation.getCommunicationMark());
		preparedStatement.setString(4, leadEvaluation.getCommunicationComment());
		preparedStatement.setLong(5, leadEvaluation.getStudent().getId());
		preparedStatement.setLong(6, leadEvaluation.getReport().getId());
		
		return preparedStatement;
	}
	
	@Override
	protected LeadEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		LeadEvaluation leadEvaluation = new LeadEvaluation();
		
		leadEvaluation.setId(resultSet.getLong(LEADEVALUATION_ID));
		leadEvaluation.setPlanningMark(resultSet.getFloat(LEADEVALUATION_PLANNINGMARK));
		leadEvaluation.setPlanningComment(resultSet.getString(LEADEVALUATION_PLANNINGCOMMENT));
		leadEvaluation.setCommunicationMark(resultSet.getFloat(LEADEVALUATION_COMMUNICATIONMARK));
		leadEvaluation.setCommunicationComment(resultSet.getString(LEADEVALUATION_COMMUNICATIONCOMMENT));
		
		Student student = new Student();
		student.setId(resultSet.getLong(STUDENTS_STUDENT_ID));
		leadEvaluation.setStudent(student);
		
		Report report = new Report();
		report.setId(resultSet.getLong(REPORTS_REPORT_ID));
		leadEvaluation.setReport(report);
		
		return leadEvaluation;
	}
	
	@Override
	public long insert(LeadEvaluation leadEvaluation) {
		long id = 0;
		
		String query = "INSERT INTO " + LEADEVALUATION_TABLE_NAME 
				+ "(" 
					+ LEADEVALUATION_PLANNINGMARK + ", " 
					+ LEADEVALUATION_PLANNINGCOMMENT + ", " 
					+ LEADEVALUATION_COMMUNICATIONMARK + ", " 
					+ LEADEVALUATION_COMMUNICATIONCOMMENT + ", " 
					+ STUDENTS_STUDENT_ID + ", " 
					+ REPORTS_REPORT_ID 
				+ ") VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			id = executeInsert(query, leadEvaluation);
			leadEvaluation.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(LeadEvaluation leadEvaluation) {
		String query = "UPDATE " + LEADEVALUATION_TABLE_NAME 
				+ " SET " 
					+ LEADEVALUATION_PLANNINGMARK + " = ?, " 
					+ LEADEVALUATION_PLANNINGCOMMENT + " = ?, " 
					+ LEADEVALUATION_COMMUNICATIONMARK + " = ?, " 
					+ LEADEVALUATION_COMMUNICATIONCOMMENT + " = ?, " 
					+ STUDENTS_STUDENT_ID + " = ?, " 
					+ REPORTS_REPORT_ID + " = ?, " 
				+ " WHERE " 
					+ LEADEVALUATION_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = getPreparedStatement(query, leadEvaluation);
			preparedStatement.setLong(7, leadEvaluation.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(long id) {
		String query = "UPDATE " + LEADEVALUATION_TABLE_NAME 
				+ " SET " 
					+ DELETED + " = ?" 
				+ " WHERE " 
					+ LEADEVALUATION_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public LeadEvaluation select(long id) {
		LeadEvaluation leadEvaluation = null;
		
		String query = "SELECT * FROM " + LEADEVALUATION_TABLE_NAME 
				+ " WHERE " 
					+ LEADEVALUATION_ID + " = ?";
		
		try {
			leadEvaluation = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return leadEvaluation;
	}
	
	@Override
	public List<LeadEvaluation> selectAll() {
		List<LeadEvaluation> list = new ArrayList<LeadEvaluation>();
		
		String query = "SELECT * FROM " + LEADEVALUATION_TABLE_NAME;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<LeadEvaluation> selectByStudent(long studentId) {
		List<LeadEvaluation> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + LEADEVALUATION_TABLE_NAME 
				+ " WHERE " 
					+ STUDENTS_STUDENT_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, studentId);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public LeadEvaluation selectByReport(long reportId) {
		LeadEvaluation leadEvaluation = null;
		
		String query = "SELECT * FROM " + LEADEVALUATION_TABLE_NAME 
				+ " WHERE " 
					+ REPORTS_REPORT_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, reportId);
			
			leadEvaluation = executeSelectSingleResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return leadEvaluation;
	}
}
