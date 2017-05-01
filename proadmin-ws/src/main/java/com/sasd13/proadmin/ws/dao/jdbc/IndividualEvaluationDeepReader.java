package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.ws.dao.IStudentDAO;

public class IndividualEvaluationDeepReader extends DeepReader<IndividualEvaluation> {

	private ReportDeepReader reportDeepReader;
	private IStudentDAO studentDAO;
	private Map<String, String[]> criterias;

	public IndividualEvaluationDeepReader(IIndividualEvaluationDAO individualEvaluationDAO, ReportDeepReader reportDeepReader, IStudentDAO studentDAO) {
		super(individualEvaluationDAO);

		this.reportDeepReader = reportDeepReader;
		this.studentDAO = studentDAO;
		criterias = new HashMap<>();
	}

	@Override
	protected void retrieve(IndividualEvaluation individualEvaluation) {
		retrieveDataReport(individualEvaluation);
		retrieveDataStudent(individualEvaluation);
	}

	private void retrieveDataReport(IndividualEvaluation individualEvaluation) {
		criterias.clear();
		criterias.put(EnumCriteria.NUMBER.getCode(), new String[] { individualEvaluation.getReport().getNumber() });

		Report report = reportDeepReader.read(criterias).get(0);
		individualEvaluation.setReport(report);
	}

	private void retrieveDataStudent(IndividualEvaluation individualEvaluation) {
		criterias.clear();
		criterias.put(EnumCriteria.INTERMEDIARY.getCode(), new String[] { individualEvaluation.getStudent().getIntermediary() });

		Student student = studentDAO.read(criterias).get(0);
		individualEvaluation.setStudent(student);
	}
}
