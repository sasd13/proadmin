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
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCEntityDAO<StudentTeam> implements IStudentTeamDAO {

	private static final Logger LOG = Logger.getLogger(JDBCStudentTeamDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCStudentTeamDAO() {
		expressionBuilder = new StudentTeamExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, StudentTeam studentTeam) throws SQLException {
		preparedStatement.setString(1, studentTeam.getStudent().getNumber());
		preparedStatement.setString(2, studentTeam.getTeam().getNumber());
	}

	@Override
	protected StudentTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		student.setNumber(resultSet.getString(COLUMN_STUDENT_CODE));

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_TEAM_CODE));

		StudentTeam studentTeam = new StudentTeam(student, team);

		return studentTeam;
	}

	@Override
	public long insert(StudentTeam studentTeam) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_STUDENT_CODE);
		builder.append(", " + COLUMN_TEAM_CODE);
		builder.append(") VALUES (?, ?)");

		return QueryUtils.insert(this, builder.toString(), studentTeam);
	}

	@Override
	public void update(StudentTeam studentTeam) throws DAOException {
		LOG.error("update unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public void delete(StudentTeam studentTeam) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_STUDENT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEAM_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = getConnection().prepareStatement(builder.toString());
			preparedStatement.setString(1, studentTeam.getStudent().getNumber());
			preparedStatement.setString(2, studentTeam.getTeam().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DAOUtils.doCatchWithThrow(e, "StudentTeam not deleted");
		} finally {
			DAOUtils.doFinally(preparedStatement);
		}
	}

	@Override
	public StudentTeam select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<StudentTeam> select(Map<String, String[]> parameters) throws DAOException {
		return QueryUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<StudentTeam> selectAll() throws DAOException {
		return QueryUtils.selectAll(this, TABLE);
	}
}
