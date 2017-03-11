package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;

public class LeadEvaluationDeepReader extends DeepReader<LeadEvaluation> {

	private ReportDeepReader reportDeepReader;
	private IStudentDAO studentDAO;
	private Map<String, String[]> parameters;

	public LeadEvaluationDeepReader(ILeadEvaluationDAO leadEvaluationDAO, ReportDeepReader reportDeepReader, IStudentDAO studentDAO) {
		super(leadEvaluationDAO);

		this.reportDeepReader = reportDeepReader;
		this.studentDAO = studentDAO;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieveData(LeadEvaluation leadEvaluation) {
		retrieveDataReport(leadEvaluation);
		retrieveDataStudent(leadEvaluation);
	}

	private void retrieveDataReport(LeadEvaluation leadEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { leadEvaluation.getReport().getNumber() });

		Report report = reportDeepReader.select(parameters).get(0);
		leadEvaluation.setReport(report);
	}

	private void retrieveDataStudent(LeadEvaluation leadEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { leadEvaluation.getStudent().getNumber() });

		Student student = studentDAO.select(parameters).get(0);
		leadEvaluation.setStudent(student);
	}
}
