package com.sasd13.proadmin.dao.running;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ITransaction;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;

public class ReportTransation implements ITransaction {

	private JDBCReportDAO reportDAO;
	private String query;
	private Report report;

	public ReportTransation(JDBCReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	public void editTransaction(String query, Report report) {
		this.query = query;
		this.report = report;
	}

	@Override
	public long insert(Connection connection) throws SQLException, DAOException {
		long id = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		reportDAO.editPreparedStatement(preparedStatement, report);
		preparedStatement.executeUpdate();

		ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		if (generatedKeys.next()) {
			id = generatedKeys.getLong(1);

			reportDAO.getLeadEvaluationDAO().insert(report.getLeadEvaluation());

			List<IndividualEvaluation> individualEvaluations = report.getIndividualEvaluations();
			for (IndividualEvaluation individualEvaluation : individualEvaluations) {
				reportDAO.getIndividualEvaluationDAO().insert(individualEvaluation);
			}
		} else {
			throw new SQLException("Insert failed. No ID obtained");
		}

		return id;
	}

	@Override
	public void update(Connection connection) throws SQLException, DAOException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		reportDAO.editPreparedStatementForUpdate(preparedStatement, report);
		preparedStatement.executeUpdate();

		reportDAO.getLeadEvaluationDAO().update(report.getLeadEvaluation());

		List<IndividualEvaluation> individualEvaluations = report.getIndividualEvaluations();
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			reportDAO.getIndividualEvaluationDAO().update(individualEvaluation);
		}
	}

	@Override
	public void delete(Connection connection) throws SQLException, DAOException {
		reportDAO.getLeadEvaluationDAO().delete(report.getLeadEvaluation());

		List<IndividualEvaluation> individualEvaluations = report.getIndividualEvaluations();
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			reportDAO.getIndividualEvaluationDAO().delete(individualEvaluation);
		}

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		reportDAO.editPreparedStatementForDelete(preparedStatement, report);
		preparedStatement.executeUpdate();
	}
}
