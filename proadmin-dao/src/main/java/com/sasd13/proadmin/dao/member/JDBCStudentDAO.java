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
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.JDBCUtils;
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

		return JDBCUtils.insert(this, builder.toString(), student);
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

		JDBCUtils.update(this, builder.toString(), student);
	}

	@Override
	public void delete(Student student) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), student);
	}

	@Override
	public Student select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public List<Student> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<Student> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public void editPreparedStatement(PreparedStatement preparedStatement, Student student) throws SQLException {
		preparedStatement.setString(1, student.getNumber());
		preparedStatement.setString(2, student.getFirstName());
		preparedStatement.setString(3, student.getLastName());
		preparedStatement.setString(4, student.getEmail());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, Student student) throws SQLException {
		super.editPreparedStatementForUpdate(preparedStatement, student);

		preparedStatement.setString(5, student.getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Student student) throws SQLException {
		super.editPreparedStatementForDelete(preparedStatement, student);

		preparedStatement.setString(1, student.getNumber());
	}

	@Override
	public Student getResultSetValues(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		student.setNumber(resultSet.getString(COLUMN_CODE));
		student.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		student.setLastName(resultSet.getString(COLUMN_LASTNAME));
		student.setEmail(resultSet.getString(COLUMN_EMAIL));

		return student;
	}
}
