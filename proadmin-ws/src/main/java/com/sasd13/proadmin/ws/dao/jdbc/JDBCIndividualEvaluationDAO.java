/*
 * To change this license header, choose License Headers in individualEvaluation Properties.
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
import com.sasd13.proadmin.bean.running.IIndividualEvaluation;
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws.dao.IIndividualEvaluationDAO;

/**
 *
 * @author Samir
 */
public class JDBCIndividualEvaluationDAO extends JDBCSession<IIndividualEvaluation> implements IIndividualEvaluationDAO {

	@Override
	public long create(IIndividualEvaluation iIndividualEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_MARK);
		builder.append(", " + COLUMN_REPORT);
		builder.append(", " + COLUMN_STUDENT);
		builder.append(") VALUES (?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), iIndividualEvaluation);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(IndividualEvaluationUpdate updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_MARK + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		try {
			JDBCUtils.update(this, builder.toString(), updateWrapper);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(IIndividualEvaluation iIndividualEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), iIndividualEvaluation);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IIndividualEvaluation> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IIndividualEvaluation> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, IIndividualEvaluation iIndividualEvaluation) throws SQLException {
		preparedStatement.setFloat(1, iIndividualEvaluation.getMark());
		preparedStatement.setString(2, iIndividualEvaluation.getReport().getNumber());
		preparedStatement.setString(3, iIndividualEvaluation.getStudent().getIntermediary());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<IIndividualEvaluation> updateWrapper) throws SQLException {
		IIndividualEvaluation iIndividualEvaluation = updateWrapper.getWrapped();

		preparedStatement.setFloat(1, iIndividualEvaluation.getMark());
		preparedStatement.setString(2, ((IndividualEvaluationUpdate) updateWrapper).getReportNumber());
		preparedStatement.setString(3, ((IndividualEvaluationUpdate) updateWrapper).getStudentIntermediary());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, IIndividualEvaluation iIndividualEvaluation) throws SQLException {
		preparedStatement.setString(1, iIndividualEvaluation.getReport().getNumber());
		preparedStatement.setString(2, iIndividualEvaluation.getStudent().getIntermediary());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_REPORT + " = ?";
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_STUDENT + " = ?";
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
	public IIndividualEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		IIndividualEvaluation iIndividualEvaluation = new IIndividualEvaluation();

		iIndividualEvaluation.setMark(resultSet.getFloat(COLUMN_MARK));

		IReport iReport = new IReport();
		iReport.setNumber(resultSet.getString(COLUMN_REPORT));

		iIndividualEvaluation.setReport(iReport);

		Student student = new Student();
		student.setIntermediary(resultSet.getString(COLUMN_STUDENT));

		iIndividualEvaluation.setStudent(student);

		return iIndividualEvaluation;
	}
}
