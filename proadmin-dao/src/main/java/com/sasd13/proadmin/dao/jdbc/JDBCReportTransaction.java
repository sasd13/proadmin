package com.sasd13.proadmin.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import com.sasd13.javaex.dao.jdbc.IJDBCTransaction;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;

public class JDBCReportTransaction implements IJDBCTransaction {

	private JDBCReportDAO reportDAO;
	private JDBCLeadEvaluationDAO leadEvaluationDAO;
	private JDBCIndividualEvaluationDAO individualEvaluationDAO;
	private String query;
	private Report report;
	private LeadEvaluation leadEvaluation;
	private List<IndividualEvaluation> individualEvaluations;

	public JDBCReportTransaction(JDBCReportDAO reportDAO, JDBCLeadEvaluationDAO leadEvaluationDAO, JDBCIndividualEvaluationDAO individualEvaluationDAO) {
		this.reportDAO = reportDAO;
		this.leadEvaluationDAO = leadEvaluationDAO;
		this.individualEvaluationDAO = individualEvaluationDAO;
	}

	public void editTransaction(String query, Report report, LeadEvaluation leadEvaluation, List<IndividualEvaluation> individualEvaluations) {
		this.query = query;
		this.report = report;
		this.leadEvaluation = leadEvaluation;
		this.individualEvaluations = individualEvaluations;
	}

	@Override
	public long insert(Connection connection) throws SQLException {
		long id = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.NO_GENERATED_KEYS);

		reportDAO.editPreparedStatementForInsert(preparedStatement, report);
		preparedStatement.executeUpdate();

		leadEvaluationDAO.insert(leadEvaluation);

		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationDAO.insert(individualEvaluation);
		}

		return id;
	}

	@Override
	public void update(Connection connection) throws SQLException {
		throw new NotImplementedException("Update not implemented");
	}

	@Override
	public void delete(Connection connection) throws SQLException {
		leadEvaluationDAO.delete(leadEvaluation);

		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationDAO.delete(individualEvaluation);
		}

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		reportDAO.editPreparedStatementForDelete(preparedStatement, report);
		preparedStatement.executeUpdate();
	}
}
