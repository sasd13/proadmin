/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.running;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.builder.running.LeadEvaluationBaseBuilder;

/**
 *
 * @author Samir
 */
public class JDBCLeadEvaluationDAO extends JDBCSession<LeadEvaluation> implements ILeadEvaluationDAO {

	private IExpressionBuilder expressionBuilder;

	public JDBCLeadEvaluationDAO() {
		expressionBuilder = new LeadEvaluationExpressionBuilder();
	}

	@Override
	public long insert(LeadEvaluation leadEvaluation) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_PLANNINGMARK);
		builder.append(", " + COLUMN_PLANNINGCOMMENT);
		builder.append(", " + COLUMN_COMMUNICATIONMARK);
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT);
		builder.append(", " + COLUMN_REPORT_CODE);
		builder.append(", " + COLUMN_STUDENT_CODE);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?)");

		return JDBCUtils.insert(this, builder.toString(), leadEvaluation);
	}

	@Override
	public void update(LeadEvaluation leadEvaluation) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_PLANNINGMARK + " = ?");
		builder.append(", " + COLUMN_PLANNINGCOMMENT + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONMARK + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT + " = ?");
		builder.append(", " + COLUMN_STUDENT_CODE + " = ?");
		builder.append(", " + COLUMN_REPORT_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		JDBCUtils.update(this, builder.toString(), leadEvaluation);
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), leadEvaluation);
	}

	@Override
	public LeadEvaluation select(long id) throws DAOException {
		return null;
	}

	@Override
	public List<LeadEvaluation> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<LeadEvaluation> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(LeadEvaluation leadEvaluation) throws DAOException {
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
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, LeadEvaluation leadEvaluation) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, leadEvaluation);

		preparedStatement.setString(7, leadEvaluation.getReport().getNumber());
		preparedStatement.setString(8, leadEvaluation.getStudent().getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, LeadEvaluation leadEvaluation) throws SQLException {
		preparedStatement.setString(1, leadEvaluation.getReport().getNumber());
		preparedStatement.setString(2, leadEvaluation.getStudent().getNumber());
	}

	@Override
	public LeadEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		LeadEvaluation leadEvaluation = new LeadEvaluationBaseBuilder(
				resultSet.getString(COLUMN_REPORT_CODE), 
				resultSet.getString(COLUMN_STUDENT_CODE)).build();

		leadEvaluation.setPlanningMark(resultSet.getFloat(COLUMN_PLANNINGMARK));
		leadEvaluation.setPlanningComment(resultSet.getString(COLUMN_PLANNINGCOMMENT));
		leadEvaluation.setCommunicationMark(resultSet.getFloat(COLUMN_COMMUNICATIONMARK));
		leadEvaluation.setCommunicationComment(resultSet.getString(COLUMN_COMMUNICATIONCOMMENT));

		// TODO : dependency binder

		return leadEvaluation;
	}
}
