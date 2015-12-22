/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

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
public class JDBCIndividualEvaluationDAO extends JDBCEntityDAO<IndividualEvaluation> implements IndividualEvaluationDAO {
	
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
		
		String query = "INSERT INTO " + INDIVIDUALEVALUATION_TABLE_NAME 
				+ "(" 
					+ INDIVIDUALEVALUATION_MARK + ", " 
					+ STUDENTS_STUDENT_ID + ", " 
					+ REPORTS_REPORT_ID 
				+ ") VALUES (?, ?, ?)";
		
		try {
			id = executeInsert(query, individualEvaluation);
			individualEvaluation.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(IndividualEvaluation individualEvaluation) {
		String query = "UPDATE " + INDIVIDUALEVALUATION_TABLE_NAME 
				+ " SET " 
					+ INDIVIDUALEVALUATION_MARK + " = ?, " 
					+ STUDENTS_STUDENT_ID + " = ?, " 
					+ REPORTS_REPORT_ID + " = ?, " 
				+ " WHERE " 
					+ INDIVIDUALEVALUATION_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = getPreparedStatement(query, individualEvaluation);
			preparedStatement.setLong(4, individualEvaluation.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(long id) {
		String query = "UPDATE " + INDIVIDUALEVALUATION_TABLE_NAME 
				+ " SET " 
					+ DELETED + " = ?" 
				+ " WHERE " 
					+ INDIVIDUALEVALUATION_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public IndividualEvaluation select(long id) {
		IndividualEvaluation individualEvaluation = null;
		
		String query = "SELECT * FROM " + INDIVIDUALEVALUATION_TABLE_NAME 
				+ " WHERE " 
					+ INDIVIDUALEVALUATION_ID + " = ?";
		
		try {
			individualEvaluation = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return individualEvaluation;
	}
	
	@Override
	public List<IndividualEvaluation> selectAll() {
		List<IndividualEvaluation> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + INDIVIDUALEVALUATION_TABLE_NAME;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<IndividualEvaluation> selectByStudent(long studentId) {
		List<IndividualEvaluation> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + INDIVIDUALEVALUATION_TABLE_NAME 
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
	public List<IndividualEvaluation> selectByReport(long reportId) {
		List<IndividualEvaluation> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + INDIVIDUALEVALUATION_TABLE_NAME 
				+ " WHERE " 
					+ REPORTS_REPORT_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, reportId);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
