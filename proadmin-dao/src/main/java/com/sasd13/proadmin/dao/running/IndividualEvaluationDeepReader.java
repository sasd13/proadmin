package com.sasd13.proadmin.dao.running;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.member.IStudentDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class IndividualEvaluationDeepReader extends DeepReader<IndividualEvaluation> {

	private IStudentDAO studentDAO;

	public IndividualEvaluationDeepReader(IIndividualEvaluationDAO individualEvaluationDAO, IStudentDAO studentDAO) {
		super(individualEvaluationDAO);

		this.studentDAO = studentDAO;
	}

	@Override
	protected void retrieveData(IndividualEvaluation individualEvaluation) throws DAOException {
		Map<String, String[]> parameters = new HashMap<>();

		retrieveDataStudent(individualEvaluation, parameters);
	}

	private void retrieveDataStudent(IndividualEvaluation individualEvaluation, Map<String, String[]> parameters) throws DAOException {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { individualEvaluation.getStudent().getNumber() });

		Student student = studentDAO.select(parameters).get(0);
		individualEvaluation.setStudent(student);
	}
}
