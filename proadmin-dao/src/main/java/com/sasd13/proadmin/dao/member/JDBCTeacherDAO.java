/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.ConditionBuilder;
import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.proadmin.bean.member.Teacher;

/**
 *
 * @author Samir
 */
public class JDBCTeacherDAO extends JDBCEntityDAO<Teacher> implements ITeacherDAO {

	private static final Logger LOG = Logger.getLogger(JDBCTeacherDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCTeacherDAO() {
		expressionBuilder = new TeacherExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Teacher teacher) throws SQLException {
		preparedStatement.setString(1, teacher.getNumber());
		preparedStatement.setString(2, teacher.getFirstName());
		preparedStatement.setString(3, teacher.getLastName());
		preparedStatement.setString(4, teacher.getEmail());
	}

	@Override
	protected Teacher getResultSetValues(ResultSet resultSet) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setNumber(resultSet.getString(COLUMN_CODE));
		teacher.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		teacher.setLastName(resultSet.getString(COLUMN_LASTNAME));
		teacher.setEmail(resultSet.getString(COLUMN_EMAIL));

		return teacher;
	}

	@Override
	public long insert(Teacher teacher) throws DAOException {
		LOG.info("insert : number=" + teacher.getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_FIRSTNAME);
		builder.append(", " + COLUMN_LASTNAME);
		builder.append(", " + COLUMN_EMAIL);
		builder.append(") VALUES (?, ?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, teacher);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "insert failed", "Teacher not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(Teacher teacher) throws DAOException {
		LOG.info("update : number=" + teacher.getNumber());

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
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, teacher);
			preparedStatement.setString(5, teacher.getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "update failed", "Teacher not updated");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(Teacher teacher) throws DAOException {
		LOG.info("delete : number=" + teacher.getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, teacher.getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "delete failed", "Teacher not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public Teacher select(long id) throws DAOException {
		LOG.info("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<Teacher> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("select : parameters=" + URLQueryUtils.toString(parameters));

		List<Teacher> list = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");

		try {
			builder.append(ConditionBuilder.parse(parameters, expressionBuilder));
		} catch (ConditionException e) {
			doCatchWithThrow(LOG, "select failed", e.getMessage());
		}

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				list.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "select failed", "Teachers not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return list;
	}

	@Override
	public List<Teacher> selectAll() throws DAOException {
		LOG.info("selectAll");

		List<Teacher> list = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				list.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "selectAll failed", "Teachers not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return list;
	}
}
