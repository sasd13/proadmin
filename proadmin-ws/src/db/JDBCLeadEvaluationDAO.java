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
public class JDBCLeadEvaluationDAO extends JDBCEntityDAO<LeadEvaluation> implements LeadEvaluationDAO {
	
	@Override
	protected PreparedStatement getPreparedStatement(String query, LeadEvaluation leadEvaluation) throws SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(query, leadEvaluation);
		
		preparedStatement.setFloat(1, leadEvaluation.getPlanningMark());
		preparedStatement.setString(2, leadEvaluation.getPlanningComment());
		preparedStatement.setFloat(3, leadEvaluation.getCommunicationMark());
		preparedStatement.setString(4, leadEvaluation.getCommunicationComment());
		preparedStatement.setLong(5, leadEvaluation.getReport().getId());
		preparedStatement.setLong(6, leadEvaluation.getStudent().getId());
		
		return preparedStatement;
	}
	
	@Override
	protected LeadEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		LeadEvaluation leadEvaluation = new LeadEvaluation();
		
		leadEvaluation.setId(resultSet.getLong(COLUMN_ID));
		leadEvaluation.setPlanningMark(resultSet.getFloat(COLUMN_PLANNINGMARK));
		leadEvaluation.setPlanningComment(resultSet.getString(COLUMN_PLANNINGCOMMENT));
		leadEvaluation.setCommunicationMark(resultSet.getFloat(COLUMN_COMMUNICATIONMARK));
		leadEvaluation.setCommunicationComment(resultSet.getString(COLUMN_COMMUNICATIONCOMMENT));
		
		Report report = new Report();
		report.setId(resultSet.getLong(COLUMN_REPORT_ID));
		leadEvaluation.setReport(report);
		
		Student student = new Student();
		student.setId(resultSet.getLong(COLUMN_STUDENT_ID));
		leadEvaluation.setStudent(student);
		
		return leadEvaluation;
	}
	
	@Override
	public long insert(LeadEvaluation leadEvaluation) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_PLANNINGMARK + ", " 
					+ COLUMN_PLANNINGCOMMENT + ", " 
					+ COLUMN_COMMUNICATIONMARK + ", " 
					+ COLUMN_COMMUNICATIONCOMMENT + ", " 
					+ COLUMN_REPORT_ID + ", " 
					+ COLUMN_STUDENT_ID
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
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_PLANNINGMARK + " = ?, " 
					+ COLUMN_PLANNINGCOMMENT + " = ?, " 
					+ COLUMN_COMMUNICATIONMARK + " = ?, " 
					+ COLUMN_COMMUNICATIONCOMMENT + " = ?, " 
					+ COLUMN_REPORT_ID + " = ?, " 
					+ COLUMN_STUDENT_ID + " = ?, " 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
	public LeadEvaluation select(long id) {
		LeadEvaluation leadEvaluation = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
		
		String query = "SELECT * FROM " + TABLE;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
