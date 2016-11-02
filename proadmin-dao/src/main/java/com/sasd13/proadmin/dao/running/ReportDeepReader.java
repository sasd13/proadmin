package com.sasd13.proadmin.dao.running;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportDeepReader extends DeepReader<Report> {

	private ILeadEvaluationDAO leadEvaluationDAO;
	private IIndividualEvaluationDAO individualEvaluationDAO;

	public ReportDeepReader(IReportDAO reportDAO) {
		super(reportDAO);

		leadEvaluationDAO = reportDAO.getLeadEvaluationDAO();
		individualEvaluationDAO = reportDAO.getIndividualEvaluationDAO();
	}

	@Override
	protected void retrieveData(Report report) throws DAOException {
		Map<String, String[]> parameters = new HashMap<String, String[]>();

		retrieveDataLeadEvaluation(report, parameters);
		retrieveDataIndividualEvaluations(report, parameters);
	}

	private void retrieveDataLeadEvaluation(Report report, Map<String, String[]> parameters) throws DAOException {
		parameters.clear();
		parameters.put(EnumParameter.REPORT.getName(), new String[] { report.getNumber() });

		LeadEvaluation leadEvaluation = leadEvaluationDAO.select(parameters).get(0);

		report.setLeadEvaluation(leadEvaluation);
		Binder.bind(report.getLeadEvaluation(), leadEvaluation);
		// TODO : dependency binder
	}

	private void retrieveDataIndividualEvaluations(Report report, Map<String, String[]> parameters) throws DAOException {
		report.getIndividualEvaluations().clear();
		parameters.clear();
		parameters.put(EnumParameter.REPORT.getName(), new String[] { report.getNumber() });

		List<IndividualEvaluation> individualEvaluations = individualEvaluationDAO.select(parameters);

		IndividualEvaluation individualEvaluationToAdd;
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			individualEvaluationToAdd = new IndividualEvaluation();

			report.getIndividualEvaluations().add(individualEvaluation);
			Binder.bind(individualEvaluationToAdd, individualEvaluation);
			// TODO : dependency binder
		}
	}
}
