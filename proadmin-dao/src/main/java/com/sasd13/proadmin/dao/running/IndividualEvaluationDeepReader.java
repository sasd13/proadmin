package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.member.IStudentDAO;
import com.sasd13.proadmin.util.Binder;

public class IndividualEvaluationDeepReader extends DeepReader<IndividualEvaluation> {

	private IStudentDAO studentDAO;

	public IndividualEvaluationDeepReader(IIndividualEvaluationDAO individualEvaluationDAO, IStudentDAO studentDAO) {
		super(individualEvaluationDAO);

		this.studentDAO = studentDAO;
	}

	@Override
	protected void retrieveData(IndividualEvaluation individualEvaluation) throws DAOException {
		Student student = studentDAO.select(individualEvaluation.getStudent().getId());
		Binder.bind(individualEvaluation.getStudent(), student);
	}
}
