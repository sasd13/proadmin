/*
 * To change this license header, choose License Headers in individualEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.running;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;

/**
 *
 * @author Samir
 */
public class JDBCIndividualEvaluationDAO extends JDBCSession<IndividualEvaluation> implements IIndividualEvaluationDAO {

	private static final Logger LOG = Logger.getLogger(JDBCIndividualEvaluationDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCIndividualEvaluationDAO() {
		expressionBuilder = new IndividualEvaluationExpressionBuilder();
	}

	@Override
	public long insert(IndividualEvaluation individualEvaluation) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_MARK);
		builder.append(", " + COLUMN_REPORT_CODE);
		builder.append(", " + COLUMN_STUDENT_CODE);
		builder.append(") VALUES (?, ?, ?)");

		return JDBCUtils.insert(this, builder.toString(), individualEvaluation);
	}

	@Override
	public void update(IndividualEvaluation individualEvaluation) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_MARK + " = ?");
		builder.append(", " + COLUMN_REPORT_CODE + " = ?");
		builder.append(", " + COLUMN_STUDENT_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		JDBCUtils.update(this, builder.toString(), individualEvaluation);
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), individualEvaluation);
	}

	@Override
	public IndividualEvaluation select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public List<IndividualEvaluation> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<IndividualEvaluation> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public void editPreparedStatement(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		preparedStatement.setFloat(1, individualEvaluation.getMark());
		preparedStatement.setString(2, individualEvaluation.getReport().getNumber());
		preparedStatement.setString(3, individualEvaluation.getStudent().getNumber());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		super.editPreparedStatementForUpdate(preparedStatement, individualEvaluation);

		preparedStatement.setString(4, individualEvaluation.getReport().getNumber());
		preparedStatement.setString(5, individualEvaluation.getStudent().getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		super.editPreparedStatementForDelete(preparedStatement, individualEvaluation);

		preparedStatement.setString(1, individualEvaluation.getReport().getNumber());
		preparedStatement.setString(2, individualEvaluation.getStudent().getNumber());
	}

	@Override
	public IndividualEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();
		report.setNumber(resultSet.getString(COLUMN_REPORT_CODE));

		Student student = new Student();
		student.setNumber(resultSet.getString(COLUMN_STUDENT_CODE));

		IndividualEvaluation individualEvaluation = new IndividualEvaluation();
		individualEvaluation.setReport(report);
		individualEvaluation.setStudent(student);
		individualEvaluation.setMark(resultSet.getFloat(COLUMN_MARK));

		// TODO : dependency binder

		return individualEvaluation;
	}
}
