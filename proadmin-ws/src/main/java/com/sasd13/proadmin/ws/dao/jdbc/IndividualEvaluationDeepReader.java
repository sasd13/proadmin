package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IIndividualEvaluation;
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.ws.dao.IStudentDAO;

public class IndividualEvaluationDeepReader extends DeepReader<IIndividualEvaluation> {

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
	protected void retrieve(IIndividualEvaluation iIndividualEvaluation) {
		retrieveDataReport(iIndividualEvaluation);
		retrieveDataStudent(iIndividualEvaluation);
	}

	private void retrieveDataReport(IIndividualEvaluation iIndividualEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { iIndividualEvaluation.getReport().getNumber() });

		IReport iReport = reportDeepReader.read(parameters).get(0);
		iIndividualEvaluation.setReport(iReport);
	}

	private void retrieveDataStudent(IIndividualEvaluation iIndividualEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[] { iIndividualEvaluation.getStudent().getIntermediary() });

		Student student = studentDAO.read(parameters).get(0);
		iIndividualEvaluation.setStudent(student);
	}
}
