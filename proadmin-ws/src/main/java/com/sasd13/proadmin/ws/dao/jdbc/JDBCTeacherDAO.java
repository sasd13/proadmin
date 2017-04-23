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
import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.bean.update.TeacherUpdate;
import com.sasd13.proadmin.ws.dao.ITeacherDAO;

/**
 *
 * @author Samir
 */
public class JDBCTeacherDAO extends JDBCSession<ITeacher> implements ITeacherDAO {

	@Override
	public long create(ITeacher iTeacher) {
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
			return JDBCUtils.insert(this, builder.toString(), iTeacher);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(TeacherUpdate updateWrapper) {
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
			JDBCUtils.update(this, builder.toString(), updateWrapper);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(ITeacher iTeacher) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), iTeacher);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ITeacher> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ITeacher> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, ITeacher iTeacher) throws SQLException {
		preparedStatement.setString(1, iTeacher.getIntermediary());
		preparedStatement.setString(2, iTeacher.getFirstName());
		preparedStatement.setString(3, iTeacher.getLastName());
		preparedStatement.setString(4, iTeacher.getEmail());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<ITeacher> updateWrapper) throws SQLException {
		ITeacher iTeacher = updateWrapper.getWrapped();

		preparedStatement.setString(1, iTeacher.getFirstName());
		preparedStatement.setString(2, iTeacher.getLastName());
		preparedStatement.setString(3, iTeacher.getEmail());
		preparedStatement.setString(4, ((TeacherUpdate) updateWrapper).getIntermediary());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, ITeacher iTeacher) throws SQLException {
		preparedStatement.setString(1, iTeacher.getIntermediary());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.INTERMEDIARY.getName().equalsIgnoreCase(key)) {
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
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.INTERMEDIARY.getName().equalsIgnoreCase(key)) {
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
	public ITeacher getResultSetValues(ResultSet resultSet) throws SQLException {
		ITeacher iTeacher = new ITeacher();

		iTeacher.setIntermediary(resultSet.getString(COLUMN_CODE));
		iTeacher.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		iTeacher.setLastName(resultSet.getString(COLUMN_LASTNAME));
		iTeacher.setEmail(resultSet.getString(COLUMN_EMAIL));

		return iTeacher;
	}
}
