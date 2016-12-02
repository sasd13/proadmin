package com.sasd13.proadmin.dao.running;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.dao.jdbc.IJDBCTransaction;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;

public class ReportTransaction implements IJDBCTransaction {

	private JDBCReportDAO reportDAO;
	private String query;
	private Report report;
	private IReportUpdateWrapper updateWrapper;

	public ReportTransaction(JDBCReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	public void editTransaction(String query, Report report) {
		this.query = query;
		this.report = report;
	}

	public void editTransaction(String query, IReportUpdateWrapper updateWrapper) {
		this.query = query;
		this.updateWrapper = updateWrapper;
	}

	@Override
	public long insert(Connection connection) throws SQLException, DAOException {
		long id = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.NO_GENERATED_KEYS);

		reportDAO.editPreparedStatementForInsert(preparedStatement, report);
		preparedStatement.executeUpdate();

		reportDAO.getLeadEvaluationDAO().insert(report.getLeadEvaluation());

		for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
			reportDAO.getIndividualEvaluationDAO().insert(individualEvaluation);
		}

		return id;
	}

	@Override
	public void update(Connection connection) throws SQLException, DAOException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		reportDAO.editPreparedStatementForUpdate(preparedStatement, updateWrapper);
		preparedStatement.executeUpdate();

		if (updateWrapper.getLeadEvaluationUpdateWrapper() != null) {
			reportDAO.getLeadEvaluationDAO().update(updateWrapper.getLeadEvaluationUpdateWrapper());
		}

		if (updateWrapper.getIndividualEvaluationUpdateWrappers() != null) {
			for (IUpdateWrapper<IndividualEvaluation> wrapper : updateWrapper.getIndividualEvaluationUpdateWrappers()) {
				reportDAO.getIndividualEvaluationDAO().update(wrapper);
			}
		}
	}

	@Override
	public void delete(Connection connection) throws SQLException, DAOException {
		reportDAO.getLeadEvaluationDAO().delete(report.getLeadEvaluation());

		for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
			reportDAO.getIndividualEvaluationDAO().delete(individualEvaluation);
		}

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		reportDAO.editPreparedStatementForDelete(preparedStatement, report);
		preparedStatement.executeUpdate();
	}
}
