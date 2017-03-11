package com.sasd13.proadmin.ws2.db.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang.NotImplementedException;

import com.sasd13.javaex.dao.jdbc.IJDBCTransaction;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.create.IReportCreateWrapper;

public class ReportTransaction implements IJDBCTransaction {

	private ReportDAO reportDAO;
	private LeadEvaluationDAO leadEvaluationDAO;
	private IndividualEvaluationDAO individualEvaluationDAO;
	private IReportCreateWrapper createWrapper;

	public ReportTransaction(ReportDAO reportDAO, LeadEvaluationDAO leadEvaluationDAO, IndividualEvaluationDAO individualEvaluationDAO) {
		this.reportDAO = reportDAO;
		this.leadEvaluationDAO = leadEvaluationDAO;
		this.individualEvaluationDAO = individualEvaluationDAO;
	}

	public void editTransaction(IReportCreateWrapper createWrapper) {
		this.createWrapper = createWrapper;
	}

	@Override
	public long insert(Connection connection) throws SQLException {
		long id = 0;

		reportDAO.insert(createWrapper.getReport());

		createWrapper.getLeadEvaluation().setReport(createWrapper.getReport());
		leadEvaluationDAO.insert(createWrapper.getLeadEvaluation());

		for (IndividualEvaluation individualEvaluation : createWrapper.getIndividualEvaluations()) {
			individualEvaluation.setReport(createWrapper.getReport());
			individualEvaluationDAO.insert(individualEvaluation);
		}

		return id;
	}

	@Override
	public void update(Connection connection) throws SQLException {
		throw new NotImplementedException("update not implemented");
	}

	@Override
	public void delete(Connection connection) throws SQLException {
		throw new NotImplementedException("delete not implemented");
	}
}
