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
import com.sasd13.javaex.dao.DAOUtils;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.QueryUtils;
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
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_FIRSTNAME);
		builder.append(", " + COLUMN_LASTNAME);
		builder.append(", " + COLUMN_EMAIL);
		builder.append(") VALUES (?, ?, ?, ?)");

		return QueryUtils.insert(this, builder.toString(), teacher);
	}

	@Override
	public void update(Teacher teacher) throws DAOException {
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
			preparedStatement = getConnection().prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, teacher);
			preparedStatement.setString(5, teacher.getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DAOUtils.doCatchWithThrow(e, "Teacher not updated");
		} finally {
			DAOUtils.doFinally(preparedStatement);
		}
	}

	@Override
	public void delete(Teacher teacher) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = getConnection().prepareStatement(builder.toString());
			preparedStatement.setString(1, teacher.getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DAOUtils.doCatchWithThrow(e, "Teacher not deleted");
		} finally {
			DAOUtils.doFinally(preparedStatement);
		}
	}

	@Override
	public Teacher select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<Teacher> select(Map<String, String[]> parameters) throws DAOException {
		return QueryUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<Teacher> selectAll() throws DAOException {
		return QueryUtils.selectAll(this, TABLE);
	}
}
