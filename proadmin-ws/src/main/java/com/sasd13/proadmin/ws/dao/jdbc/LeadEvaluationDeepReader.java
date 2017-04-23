package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.bean.LeadEvaluation;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws.dao.IStudentDAO;

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
	protected void retrieve(LeadEvaluation leadEvaluation) {
		retrieveDataReport(leadEvaluation);
		retrieveDataStudent(leadEvaluation);
	}

	private void retrieveDataReport(LeadEvaluation leadEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { leadEvaluation.getReport().getNumber() });

		Report report = reportDeepReader.read(parameters).get(0);
		leadEvaluation.setReport(report);
	}

	private void retrieveDataStudent(LeadEvaluation leadEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[] { leadEvaluation.getStudent().getIntermediary() });

		Student student = studentDAO.read(parameters).get(0);
		leadEvaluation.setStudent(student);
	}
}
