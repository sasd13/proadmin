package com.sasd13.proadmin.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.dao.IStudentDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class LeadEvaluationDeepReader extends DeepReader<LeadEvaluation> {

	private IStudentDAO studentDAO;

	public LeadEvaluationDeepReader(ILeadEvaluationDAO leadEvaluationDAO, IStudentDAO studentDAO) {
		super(leadEvaluationDAO);

		this.studentDAO = studentDAO;
	}

	@Override
	protected void retrieveData(LeadEvaluation leadEvaluation) {
		Map<String, String[]> parameters = new HashMap<>();

		retrieveDataStudent(leadEvaluation, parameters);
	}

	private void retrieveDataStudent(LeadEvaluation leadEvaluation, Map<String, String[]> parameters) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { leadEvaluation.getStudent().getNumber() });

		Student student = studentDAO.select(parameters).get(0);
		leadEvaluation.setStudent(student);
	}
}