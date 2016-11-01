package com.sasd13.proadmin.dao.running;

import java.util.List;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ITransaction;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;

public class ReportTransation implements ITransaction<Report> {

	private ILeadEvaluationDAO leadEvaluationDAO;
	private IIndividualEvaluationDAO individualEvaluationDAO;

	public ReportTransation(IReportDAO reportDAO) {
		this.leadEvaluationDAO = reportDAO.getLeadEvaluationDAO();
		this.individualEvaluationDAO = reportDAO.getIndividualEvaluationDAO();
	}

	@Override
	public void insert(Report report) throws DAOException {
		leadEvaluationDAO.insert(report.getLeadEvaluation());

		List<IndividualEvaluation> individualEvaluations = report.getIndividualEvaluations();
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationDAO.insert(individualEvaluation);
		}
	}

	@Override
	public void update(Report report) throws DAOException {
		leadEvaluationDAO.update(report.getLeadEvaluation());

		List<IndividualEvaluation> individualEvaluations = report.getIndividualEvaluations();
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationDAO.update(individualEvaluation);
		}
	}

	@Override
	public void delete(Report report) throws DAOException {
		leadEvaluationDAO.delete(report.getLeadEvaluation());

		List<IndividualEvaluation> individualEvaluations = report.getIndividualEvaluations();
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationDAO.delete(individualEvaluation);
		}
	}
}
