/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.jdbc;

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
import com.sasd13.proadmin.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCLeadEvaluationDAO extends JDBCSession<LeadEvaluation> implements ILeadEvaluationDAO {

	@Override
	public long insert(LeadEvaluation leadEvaluation) {
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

		return JDBCUtils.insert(this, builder.toString(), leadEvaluation);
	}

	@Override
	public void update(IUpdateWrapper<LeadEvaluation> updateWrapper) {
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

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		JDBCUtils.delete(this, builder.toString(), leadEvaluation);
	}

	@Override
	public LeadEvaluation select(long id) {
		return null;
	}

	@Override
	public List<LeadEvaluation> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<LeadEvaluation> selectAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(LeadEvaluation leadEvaluation) {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, LeadEvaluation leadEvaluation) throws SQLException {
		preparedStatement.setFloat(1, leadEvaluation.getPlanningMark());
		preparedStatement.setString(2, leadEvaluation.getPlanningComment());
		preparedStatement.setFloat(3, leadEvaluation.getCommunicationMark());
		preparedStatement.setString(4, leadEvaluation.getCommunicationComment());
		preparedStatement.setString(5, leadEvaluation.getReport().getNumber());
		preparedStatement.setString(6, leadEvaluation.getStudent().getNumber());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<LeadEvaluation> updateWrapper) throws SQLException {
		LeadEvaluation leadEvaluation = updateWrapper.getWrapped();

		preparedStatement.setFloat(1, leadEvaluation.getPlanningMark());
		preparedStatement.setString(2, leadEvaluation.getPlanningComment());
		preparedStatement.setFloat(3, leadEvaluation.getCommunicationMark());
		preparedStatement.setString(4, leadEvaluation.getCommunicationComment());
		preparedStatement.setString(5, leadEvaluation.getStudent().getNumber());
		preparedStatement.setString(6, ((LeadEvaluationUpdateWrapper) updateWrapper).getReportNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, LeadEvaluation leadEvaluation) throws SQLException {
		preparedStatement.setString(1, leadEvaluation.getReport().getNumber());
		preparedStatement.setString(2, leadEvaluation.getStudent().getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return ILeadEvaluationDAO.COLUMN_REPORT + " = ?";
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return ILeadEvaluationDAO.COLUMN_STUDENT + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
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
		student.setNumber(resultSet.getString(COLUMN_STUDENT));

		leadEvaluation.setStudent(student);

		return leadEvaluation;
	}
}
