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
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.bean.update.StudentUpdate;
import com.sasd13.proadmin.ws.dao.IStudentDAO;

/**
 *
 * @author Samir
 */
public class JDBCStudentDAO extends JDBCSession<Student> implements IStudentDAO {

	@Override
	public long create(Student student) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_FIRSTNAME);
		builder.append(", " + COLUMN_LASTNAME);
		builder.append(", " + COLUMN_EMAIL);
		builder.append(") VALUES (?, ?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), student);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(StudentUpdate studentUpdate) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_FIRSTNAME + " = ?");
		builder.append(", " + COLUMN_LASTNAME + " = ?");
		builder.append(", " + COLUMN_EMAIL + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.update(this, builder.toString(), studentUpdate);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Student student) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), student);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Student> read(Map<String, String[]> criterias) {
		try {
			return JDBCUtils.select(this, TABLE, criterias);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Student> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Student student) throws SQLException {
		preparedStatement.setString(1, student.getIntermediary());
		preparedStatement.setString(2, student.getFirstName());
		preparedStatement.setString(3, student.getLastName());
		preparedStatement.setString(4, student.getEmail());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Student> updateWrapper) throws SQLException {
		Student student = updateWrapper.getWrapped();

		preparedStatement.setString(1, student.getFirstName());
		preparedStatement.setString(2, student.getLastName());
		preparedStatement.setString(3, student.getEmail());
		preparedStatement.setString(4, ((StudentUpdate) updateWrapper).getIntermediary());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Student student) throws SQLException {
		preparedStatement.setString(1, student.getIntermediary());
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			return IStudentDAO.COLUMN_CODE + " = ?";
		} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(key)) {
			return IStudentDAO.COLUMN_FIRSTNAME + " = ?";
		} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(key)) {
			return IStudentDAO.COLUMN_LASTNAME + " = ?";
		} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(key)) {
			return IStudentDAO.COLUMN_EMAIL + " = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public Student getResultSetValues(ResultSet resultSet) throws SQLException {
		Student student = new Student();

		student.setIntermediary(resultSet.getString(COLUMN_CODE));
		student.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		student.setLastName(resultSet.getString(COLUMN_LASTNAME));
		student.setEmail(resultSet.getString(COLUMN_EMAIL));

		return student;
	}
}
