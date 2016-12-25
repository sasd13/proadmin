package com.sasd13.proadmin.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.IReportDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportDeepReader extends DeepReader<Report> {

	private LeadEvaluationDeepReader leadEvaluationDeepReader;
	private IndividualEvaluationDeepReader individualEvaluationDeepReader;

	public ReportDeepReader(IReportDAO reportDAO, LeadEvaluationDeepReader leadEvaluationDeepReader, IndividualEvaluationDeepReader individualEvaluationDeepReader) {
		super(reportDAO);

		this.leadEvaluationDeepReader = leadEvaluationDeepReader;
		this.individualEvaluationDeepReader = individualEvaluationDeepReader;
	}

	@Override
	protected void retrieveData(Report report) {
		Map<String, String[]> parameters = new HashMap<String, String[]>();

		retrieveDataLeadEvaluation(report, parameters);
		retrieveDataIndividualEvaluations(report, parameters);
	}

	private void retrieveDataLeadEvaluation(Report report, Map<String, String[]> parameters) {
		parameters.clear();
		parameters.put(EnumParameter.REPORT.getName(), new String[] { report.getNumber() });

		LeadEvaluation leadEvaluation = leadEvaluationDeepReader.select(parameters).get(0);

		report.setLeadEvaluation(leadEvaluation);
		leadEvaluation.setReport(report);
	}

	private void retrieveDataIndividualEvaluations(Report report, Map<String, String[]> parameters) {
		parameters.clear();
		parameters.put(EnumParameter.REPORT.getName(), new String[] { report.getNumber() });

		List<IndividualEvaluation> individualEvaluations = individualEvaluationDeepReader.select(parameters);
		report.getIndividualEvaluations().clear();

		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			report.getIndividualEvaluations().add(individualEvaluation);
			individualEvaluation.setReport(report);
		}
	}
}
