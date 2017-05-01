package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.LeadEvaluation;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws.dao.IStudentDAO;

public class LeadEvaluationDeepReader extends DeepReader<LeadEvaluation> {

	private ReportDeepReader reportDeepReader;
	private IStudentDAO studentDAO;
	private Map<String, String[]> criterias;

	public LeadEvaluationDeepReader(ILeadEvaluationDAO leadEvaluationDAO, ReportDeepReader reportDeepReader, IStudentDAO studentDAO) {
		super(leadEvaluationDAO);

		this.reportDeepReader = reportDeepReader;
		this.studentDAO = studentDAO;
		criterias = new HashMap<>();
	}

	@Override
	protected void retrieve(LeadEvaluation leadEvaluation) {
		retrieveDataReport(leadEvaluation);
		retrieveDataStudent(leadEvaluation);
	}

	private void retrieveDataReport(LeadEvaluation leadEvaluation) {
		criterias.clear();
		criterias.put(EnumCriteria.NUMBER.getCode(), new String[] { leadEvaluation.getReport().getNumber() });

		Report report = reportDeepReader.read(criterias).get(0);
		leadEvaluation.setReport(report);
	}

	private void retrieveDataStudent(LeadEvaluation leadEvaluation) {
		criterias.clear();
		criterias.put(EnumCriteria.INTERMEDIARY.getCode(), new String[] { leadEvaluation.getStudent().getIntermediary() });

		Student student = studentDAO.read(criterias).get(0);
		leadEvaluation.setStudent(student);
	}
}
