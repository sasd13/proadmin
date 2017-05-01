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
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.AcademicLevel;
import com.sasd13.proadmin.ws.dao.IAcademicLevelDAO;

/**
 *
 * @author Samir
 */
public class JDBCAcademicLevelDAO extends JDBCSession<AcademicLevel> implements IAcademicLevelDAO {

	@Override
	public List<AcademicLevel> read(Map<String, String[]> criterias) {
		try {
			return JDBCUtils.select(this, TABLE, criterias);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getCondition(String key) {
		if (EnumCriteria.CODE.getCode().equalsIgnoreCase(key)) {
			return COLUMN_CODE + " = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumCriteria.CODE.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public AcademicLevel getResultSetValues(ResultSet resultSet) throws SQLException {
		AcademicLevel academicLevel = new AcademicLevel();

		academicLevel.setCode(resultSet.getString(COLUMN_CODE));

		return academicLevel;
	}

	@Override
	public List<AcademicLevel> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
