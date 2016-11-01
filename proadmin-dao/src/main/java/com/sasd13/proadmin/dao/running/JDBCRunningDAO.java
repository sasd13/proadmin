/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.running;

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
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCEntityDAO<Running> implements IRunningDAO {

	private static final Logger LOG = Logger.getLogger(JDBCRunningDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCRunningDAO() {
		expressionBuilder = new RunningExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setString(2, running.getProject().getCode());
		preparedStatement.setString(3, running.getTeacher().getNumber());
	}

	@Override
	protected Running getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_PROJECT_CODE));

		Teacher teacher = new Teacher();
		teacher.setNumber(resultSet.getString(COLUMN_TEACHER_CODE));

		Running running = new Running(project);
		running.setYear(resultSet.getInt(COLUMN_YEAR));
		running.setTeacher(teacher);

		return running;
	}

	@Override
	public long insert(Running running) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_YEAR);
		builder.append(", " + COLUMN_PROJECT_CODE);
		builder.append(", " + COLUMN_TEACHER_CODE);
		builder.append(") VALUES (?, ?, ?)");

		return QueryUtils.insert(this, builder.toString(), running);
	}

	@Override
	public void update(Running running) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(", " + COLUMN_PROJECT_CODE + " = ?");
		builder.append(", " + COLUMN_TEACHER_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEACHER_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = getConnection().prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, running);
			preparedStatement.setString(4, running.getProject().getCode());
			preparedStatement.setString(5, running.getTeacher().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DAOUtils.doCatchWithThrow(e, "Running not updated");
		} finally {
			DAOUtils.doFinally(preparedStatement);
		}
	}

	@Override
	public void delete(Running running) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEACHER_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = getConnection().prepareStatement(builder.toString());
			preparedStatement.setString(1, running.getProject().getCode());
			preparedStatement.setString(2, running.getTeacher().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DAOUtils.doCatchWithThrow(e, "Running not deleted");
		} finally {
			DAOUtils.doFinally(preparedStatement);
		}
	}

	@Override
	public Running select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<Running> select(Map<String, String[]> parameters) throws DAOException {
		return QueryUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<Running> selectAll() throws DAOException {
		return QueryUtils.selectAll(this, TABLE);
	}
}
