/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws.dao.ILeadEvaluationDAO;

/**
 *
 * @author Samir
 */
public class JDBCLeadEvaluationDAO extends JDBCSession<LeadEvaluation> implements ILeadEvaluationDAO {

	@Override
	public long create(LeadEvaluation leadEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_PLANNINGMARK);
		builder.append(", " + COLUMN_PLANNINGCOMMENT);
		builder.append(", " + COLUMN_COMMUNICATIONMARK);
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT);
		builder.append(", " + COLUMN_REPORT);
		builder.append(", " + COLUMN_STUDENT);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), leadEvaluation);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(LeadEvaluationUpdateWrapper updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_PLANNINGMARK + " = ?");
		builder.append(", " + COLUMN_PLANNINGCOMMENT + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONMARK + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT + " = ?");
		builder.append(", " + COLUMN_STUDENT + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");

		try {
			JDBCUtils.update(this, builder.toString(), updateWrapper);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), leadEvaluation);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<LeadEvaluation> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, LeadEvaluation leadEvaluation) throws SQLException {
		preparedStatement.setFloat(1, leadEvaluation.getPlanningMark());
		preparedStatement.setString(2, leadEvaluation.getPlanningComment());
		preparedStatement.setFloat(3, leadEvaluation.getCommunicationMark());
		preparedStatement.setString(4, leadEvaluation.getCommunicationComment());
		preparedStatement.setString(5, leadEvaluation.getReport().getNumber());
		preparedStatement.setString(6, leadEvaluation.getStudent().getIntermediary());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<LeadEvaluation> updateWrapper) throws SQLException {
		LeadEvaluation leadEvaluation = updateWrapper.getWrapped();

		preparedStatement.setFloat(1, leadEvaluation.getPlanningMark());
		preparedStatement.setString(2, leadEvaluation.getPlanningComment());
		preparedStatement.setFloat(3, leadEvaluation.getCommunicationMark());
		preparedStatement.setString(4, leadEvaluation.getCommunicationComment());
		preparedStatement.setString(5, leadEvaluation.getStudent().getIntermediary());
		preparedStatement.setString(6, ((LeadEvaluationUpdateWrapper) updateWrapper).getReportNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, LeadEvaluation leadEvaluation) throws SQLException {
		preparedStatement.setString(1, leadEvaluation.getReport().getNumber());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return ILeadEvaluationDAO.COLUMN_REPORT + " = ?";
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return ILeadEvaluationDAO.COLUMN_STUDENT + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public LeadEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		LeadEvaluation leadEvaluation = new LeadEvaluation();

		leadEvaluation.setPlanningMark(resultSet.getFloat(COLUMN_PLANNINGMARK));
		leadEvaluation.setPlanningComment(resultSet.getString(COLUMN_PLANNINGCOMMENT));
		leadEvaluation.setCommunicationMark(resultSet.getFloat(COLUMN_COMMUNICATIONMARK));
		leadEvaluation.setCommunicationComment(resultSet.getString(COLUMN_COMMUNICATIONCOMMENT));

		Report report = new Report();
		report.setNumber(resultSet.getString(COLUMN_REPORT));

		leadEvaluation.setReport(report);

		Student student = new Student();
		student.setIntermediary(resultSet.getString(COLUMN_STUDENT));

		leadEvaluation.setStudent(student);

		return leadEvaluation;
	}
}
