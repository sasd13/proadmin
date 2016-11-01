/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DAOUtils;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.QueryUtils;
import com.sasd13.proadmin.bean.member.Student;

/**
 *
 * @author Samir
 */
public class JDBCStudentDAO extends JDBCEntityDAO<Student> implements IStudentDAO {

	private static final Logger LOG = Logger.getLogger(JDBCStudentDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCStudentDAO() {
		expressionBuilder = new StudentExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Student student) throws SQLException {
		preparedStatement.setString(1, student.getNumber());
		preparedStatement.setString(2, student.getFirstName());
		preparedStatement.setString(3, student.getLastName());
		preparedStatement.setString(4, student.getEmail());
	}

	@Override
	protected Student getResultSetValues(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		student.setNumber(resultSet.getString(COLUMN_CODE));
		student.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		student.setLastName(resultSet.getString(COLUMN_LASTNAME));
		student.setEmail(resultSet.getString(COLUMN_EMAIL));

		return student;
	}

	@Override
	public long insert(Student student) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_FIRSTNAME);
		builder.append(", " + COLUMN_LASTNAME);
		builder.append(", " + COLUMN_EMAIL);
		builder.append(") VALUES (?, ?, ?, ?)");

		return QueryUtils.insert(this, builder.toString(), student);
	}

	@Override
	public void update(Student student) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(", " + COLUMN_FIRSTNAME + " = ?");
		builder.append(", " + COLUMN_LASTNAME + " = ?");
		builder.append(", " + COLUMN_EMAIL + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = getConnection().prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, student);
			preparedStatement.setString(5, student.getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DAOUtils.doCatchWithThrow(e, "Student not updated");
		} finally {
			DAOUtils.doFinally(preparedStatement);
		}
	}

	@Override
	public void delete(Student student) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = getConnection().prepareStatement(builder.toString());
			preparedStatement.setString(1, student.getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DAOUtils.doCatchWithThrow(e, "Student not deleted");
		} finally {
			DAOUtils.doFinally(preparedStatement);
		}
	}

	@Override
	public Student select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<Student> select(Map<String, String[]> parameters) throws DAOException {
		return QueryUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<Student> selectAll() throws DAOException {
		return QueryUtils.selectAll(this, TABLE);
	}
}
