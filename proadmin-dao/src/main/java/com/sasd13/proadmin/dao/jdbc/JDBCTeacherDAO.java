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
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.ITeacherDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCTeacherDAO extends JDBCSession<Teacher> implements ITeacherDAO {

	@Override
	public long insert(Teacher teacher) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_FIRSTNAME);
		builder.append(", " + COLUMN_LASTNAME);
		builder.append(", " + COLUMN_EMAIL);
		builder.append(") VALUES (?, ?, ?, ?)");

		return JDBCUtils.insert(this, builder.toString(), teacher);
	}

	@Override
	public void update(IUpdateWrapper<Teacher> updateWrapper) {
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

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Teacher teacher) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), teacher);
	}

	@Override
	public Teacher select(long id) {
		return null;
	}

	@Override
	public List<Teacher> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Teacher> selectAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Teacher teacher) {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Teacher teacher) throws SQLException {
		preparedStatement.setString(1, teacher.getNumber());
		preparedStatement.setString(2, teacher.getFirstName());
		preparedStatement.setString(3, teacher.getLastName());
		preparedStatement.setString(4, teacher.getEmail());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Teacher> updateWrapper) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, updateWrapper.getWrapped());

		preparedStatement.setString(5, ((TeacherUpdateWrapper) updateWrapper).getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Teacher teacher) throws SQLException {
		preparedStatement.setString(1, teacher.getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_CODE + " = ?";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_FIRSTNAME + " = ?";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_LASTNAME + " = ?";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_EMAIL + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public Teacher getResultSetValues(ResultSet resultSet) throws SQLException {
		Teacher teacher = new Teacher();

		teacher.setNumber(resultSet.getString(COLUMN_CODE));
		teacher.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		teacher.setLastName(resultSet.getString(COLUMN_LASTNAME));
		teacher.setEmail(resultSet.getString(COLUMN_EMAIL));

		return teacher;
	}
}
