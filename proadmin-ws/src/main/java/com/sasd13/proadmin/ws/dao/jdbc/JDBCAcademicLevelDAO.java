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
import com.sasd13.proadmin.bean.level.IAcademicLevel;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.IAcademicLevelDAO;

/**
 *
 * @author Samir
 */
public class JDBCAcademicLevelDAO extends JDBCSession<IAcademicLevel> implements IAcademicLevelDAO {

	@Override
	public List<IAcademicLevel> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IAcademicLevelDAO.COLUMN_CODE + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public IAcademicLevel getResultSetValues(ResultSet resultSet) throws SQLException {
		IAcademicLevel iAcademicLevel = new IAcademicLevel();

		iAcademicLevel.setCode(resultSet.getString(COLUMN_CODE));

		return iAcademicLevel;
	}

	@Override
	public List<IAcademicLevel> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
