package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.ILeadEvaluation;
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws.dao.IStudentDAO;

public class LeadEvaluationDeepReader extends DeepReader<ILeadEvaluation> {

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
	protected void retrieve(ILeadEvaluation iLeadEvaluation) {
		retrieveDataReport(iLeadEvaluation);
		retrieveDataStudent(iLeadEvaluation);
	}

	private void retrieveDataReport(ILeadEvaluation iLeadEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { iLeadEvaluation.getReport().getNumber() });

		IReport iReport = reportDeepReader.read(parameters).get(0);
		iLeadEvaluation.setReport(iReport);
	}

	private void retrieveDataStudent(ILeadEvaluation iLeadEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[] { iLeadEvaluation.getStudent().getIntermediary() });

		Student student = studentDAO.read(parameters).get(0);
		iLeadEvaluation.setStudent(student);
	}
}
