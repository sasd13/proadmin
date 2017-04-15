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
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.AcademicLevelUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCAcademicLevelDAO extends JDBCSession<AcademicLevel> implements IAcademicLevelDAO {

	@Override
	public long insert(AcademicLevel academicLevel) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(") VALUES (?)");

		return JDBCUtils.insert(this, builder.toString(), academicLevel);
	}

	@Override
	public void update(IUpdateWrapper<AcademicLevel> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(AcademicLevel academicLevel) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), academicLevel);
	}

	@Override
	public AcademicLevel select(long id) {
		return null;
	}

	@Override
	public List<AcademicLevel> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<AcademicLevel> selectAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(AcademicLevel academicLevel) {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, AcademicLevel academicLevel) throws SQLException {
		preparedStatement.setString(1, academicLevel.getCode());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<AcademicLevel> updateWrapper) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, updateWrapper.getWrapped());

		preparedStatement.setString(2, ((AcademicLevelUpdateWrapper) updateWrapper).getCode());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, AcademicLevel academicLevel) throws SQLException {
		preparedStatement.setString(1, academicLevel.getCode());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IAcademicLevelDAO.COLUMN_CODE + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public AcademicLevel getResultSetValues(ResultSet resultSet) throws SQLException {
		AcademicLevel academicLevel = new AcademicLevel();

		academicLevel.setCode(resultSet.getString(COLUMN_CODE));

		return academicLevel;
	}
}
