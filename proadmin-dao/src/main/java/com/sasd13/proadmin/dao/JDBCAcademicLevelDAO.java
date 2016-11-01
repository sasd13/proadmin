/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao;

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
import com.sasd13.proadmin.bean.AcademicLevel;

/**
 *
 * @author Samir
 */
public class JDBCAcademicLevelDAO extends JDBCEntityDAO<AcademicLevel> implements IAcademicLevelDAO {

	private static final Logger LOG = Logger.getLogger(JDBCAcademicLevelDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCAcademicLevelDAO() {
		expressionBuilder = new AcademicLevelExpressionBuilder();
	}

	@Override
	public long insert(AcademicLevel academicLevel) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(") VALUES (?)");

		return JDBCUtils.insert(this, builder.toString(), academicLevel);
	}

	@Override
	public void update(AcademicLevel academicLevel) throws DAOException {
		LOG.error("update unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public void delete(AcademicLevel academicLevel) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), academicLevel);
	}

	@Override
	public AcademicLevel select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public List<AcademicLevel> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<AcademicLevel> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public void editPreparedStatement(PreparedStatement preparedStatement, AcademicLevel academicLevel) throws SQLException {
		preparedStatement.setString(1, academicLevel.getCode());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, AcademicLevel academicLevel) throws SQLException {
		super.editPreparedStatementForDelete(preparedStatement, academicLevel);

		preparedStatement.setString(1, academicLevel.getCode());
	}

	@Override
	public AcademicLevel getResultSetValues(ResultSet resultSet) throws SQLException {
		AcademicLevel academicLevel = new AcademicLevel(resultSet.getString(COLUMN_CODE));

		return academicLevel;
	}
}
