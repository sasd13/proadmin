package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.ws.dao.IStudentDAO;

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
	protected void retrieve(IndividualEvaluation individualEvaluation) {
		retrieveDataReport(individualEvaluation);
		retrieveDataStudent(individualEvaluation);
	}

	private void retrieveDataReport(IndividualEvaluation individualEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { individualEvaluation.getReport().getNumber() });

		Report report = reportDeepReader.read(parameters).get(0);
		individualEvaluation.setReport(report);
	}

	private void retrieveDataStudent(IndividualEvaluation individualEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[] { individualEvaluation.getStudent().getIntermediary() });

		Student student = studentDAO.read(parameters).get(0);
		individualEvaluation.setStudent(student);
	}
}
