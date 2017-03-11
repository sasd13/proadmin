package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;

public class IndividualEvaluationDeepReader extends DeepReader<IndividualEvaluation> {

	private ReportDeepReader reportDeepReader;
	private IStudentDAO studentDAO;
	private Map<String, String[]> parameters;

	public IndividualEvaluationDeepReader(IIndividualEvaluationDAO individualEvaluationDAO, ReportDeepReader reportDeepReader, IStudentDAO studentDAO) {
		super(individualEvaluationDAO);

		this.reportDeepReader = reportDeepReader;
		this.studentDAO = studentDAO;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieveData(IndividualEvaluation individualEvaluation) {
		retrieveDataReport(individualEvaluation);
		retrieveDataStudent(individualEvaluation);
	}

	private void retrieveDataReport(IndividualEvaluation individualEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { individualEvaluation.getReport().getNumber() });

		Report report = reportDeepReader.select(parameters).get(0);
		individualEvaluation.setReport(report);
	}

	private void retrieveDataStudent(IndividualEvaluation individualEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { individualEvaluation.getStudent().getNumber() });

		Student student = studentDAO.select(parameters).get(0);
		individualEvaluation.setStudent(student);
	}
}
