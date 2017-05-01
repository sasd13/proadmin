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
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.bean.update.IndividualEvaluationUpdate;
import com.sasd13.proadmin.ws.dao.IIndividualEvaluationDAO;

/**
 *
 * @author Samir
 */
public class JDBCIndividualEvaluationDAO extends JDBCSession<IndividualEvaluation> implements IIndividualEvaluationDAO {

	@Override
	public void create(List<IndividualEvaluation> individualEvaluations) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_MARK);
		builder.append(", " + COLUMN_REPORT);
		builder.append(", " + COLUMN_STUDENT);
		builder.append(") VALUES (?, ?, ?)");

		try {
			for (IndividualEvaluation individualEvaluation : individualEvaluations) {
				JDBCUtils.insert(this, new String(builder.toString()), individualEvaluation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(List<IndividualEvaluationUpdate> individualEvaluationUpdates) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_MARK + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		try {
			for (IndividualEvaluationUpdate individualEvaluationUpdate : individualEvaluationUpdates) {
				JDBCUtils.update(this, new String(builder.toString()), individualEvaluationUpdate);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(List<IndividualEvaluation> individualEvaluations) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		try {
			for (IndividualEvaluation individualEvaluation : individualEvaluations) {
				JDBCUtils.delete(this, builder.toString(), individualEvaluation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> criterias) {
		try {
			return JDBCUtils.select(this, TABLE, criterias);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IndividualEvaluation> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		preparedStatement.setFloat(1, individualEvaluation.getMark());
		preparedStatement.setString(2, individualEvaluation.getReport().getNumber());
		preparedStatement.setString(3, individualEvaluation.getStudent().getIntermediary());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<IndividualEvaluation> updateWrapper) throws SQLException {
		IndividualEvaluation individualEvaluation = updateWrapper.getWrapped();

		preparedStatement.setFloat(1, individualEvaluation.getMark());
		preparedStatement.setString(2, ((IndividualEvaluationUpdate) updateWrapper).getReportNumber());
		preparedStatement.setString(3, ((IndividualEvaluationUpdate) updateWrapper).getStudentIntermediary());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		preparedStatement.setString(1, individualEvaluation.getReport().getNumber());
		preparedStatement.setString(2, individualEvaluation.getStudent().getIntermediary());
	}

	@Override
	public String getCondition(String key) {
		if (EnumCriteria.REPORT.getCode().equalsIgnoreCase(key)) {
			return COLUMN_REPORT + " = ?";
		} else if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			return COLUMN_STUDENT + " = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumCriteria.REPORT.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public IndividualEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		IndividualEvaluation individualEvaluation = new IndividualEvaluation();

		individualEvaluation.setMark(resultSet.getFloat(COLUMN_MARK));

		Report report = new Report();
		report.setNumber(resultSet.getString(COLUMN_REPORT));
		individualEvaluation.setReport(report);

		Student student = new Student();
		student.setIntermediary(resultSet.getString(COLUMN_STUDENT));
		individualEvaluation.setStudent(student);

		return individualEvaluation;
	}
}
