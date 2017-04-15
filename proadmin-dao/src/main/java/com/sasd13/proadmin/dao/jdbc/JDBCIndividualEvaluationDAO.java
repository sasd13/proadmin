/*
 * To change this license header, choose License Headers in individualEvaluation Properties.
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
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCIndividualEvaluationDAO extends JDBCSession<IndividualEvaluation> implements IIndividualEvaluationDAO {

	@Override
	public long insert(IndividualEvaluation individualEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_MARK);
		builder.append(", " + COLUMN_REPORT);
		builder.append(", " + COLUMN_STUDENT);
		builder.append(") VALUES (?, ?, ?)");

		return JDBCUtils.insert(this, builder.toString(), individualEvaluation);
	}

	@Override
	public void update(IUpdateWrapper<IndividualEvaluation> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_MARK + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT + " = ?");
		builder.append(" AND " + COLUMN_STUDENT + " = ?");

		JDBCUtils.delete(this, builder.toString(), individualEvaluation);
	}

	@Override
	public List<IndividualEvaluation> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<IndividualEvaluation> selectAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		preparedStatement.setFloat(1, individualEvaluation.getMark());
		preparedStatement.setString(2, individualEvaluation.getReport().getNumber());
		preparedStatement.setString(3, individualEvaluation.getStudent().getNumber());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<IndividualEvaluation> updateWrapper) throws SQLException {
		IndividualEvaluation individualEvaluation = updateWrapper.getWrapped();

		preparedStatement.setFloat(1, individualEvaluation.getMark());
		preparedStatement.setString(2, ((IndividualEvaluationUpdateWrapper) updateWrapper).getReportNumber());
		preparedStatement.setString(3, ((IndividualEvaluationUpdateWrapper) updateWrapper).getStudentNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		preparedStatement.setString(1, individualEvaluation.getReport().getNumber());
		preparedStatement.setString(2, individualEvaluation.getStudent().getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_REPORT + " = ?";
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_STUDENT + " = ?";
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
	public IndividualEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		IndividualEvaluation individualEvaluation = new IndividualEvaluation();

		individualEvaluation.setMark(resultSet.getFloat(COLUMN_MARK));

		Report report = new Report();
		report.setNumber(resultSet.getString(COLUMN_REPORT));

		individualEvaluation.setReport(report);

		Student student = new Student();
		student.setNumber(resultSet.getString(COLUMN_STUDENT));

		individualEvaluation.setStudent(student);

		return individualEvaluation;
	}
}
