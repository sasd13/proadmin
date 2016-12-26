package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.EnumParameter;

public class IndividualEvaluationDeepReader extends DeepReader<IndividualEvaluation> {

	private IStudentDAO studentDAO;
	private Map<String, String[]> parameters;

	public IndividualEvaluationDeepReader(IIndividualEvaluationDAO individualEvaluationDAO, IStudentDAO studentDAO) {
		super(individualEvaluationDAO);

		this.studentDAO = studentDAO;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieveData(IndividualEvaluation individualEvaluation) {
		retrieveDataStudent(individualEvaluation);
	}

	private void retrieveDataStudent(IndividualEvaluation individualEvaluation) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { individualEvaluation.getStudent().getNumber() });

		Student student = studentDAO.select(parameters).get(0);
		individualEvaluation.setStudent(student);
	}
}
