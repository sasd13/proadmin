/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.IAcademicLevelDAO;

/**
 *
 * @author Samir
 */
public class JDBCAcademicLevelDAO extends JDBCSession<AcademicLevel> implements IAcademicLevelDAO {

	@Override
	public List<AcademicLevel> read(Map<String, String[]> parameters) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public List<AcademicLevel> readAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public AcademicLevel getResultSetValues(ResultSet resultSet) throws SQLException {
		AcademicLevel academicLevel = new AcademicLevel();

		academicLevel.setCode(resultSet.getString(COLUMN_CODE));

		return academicLevel;
	}
}
