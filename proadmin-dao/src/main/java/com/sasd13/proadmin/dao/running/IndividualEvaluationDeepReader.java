package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.member.IStudentDAO;

public class IndividualEvaluationDeepReader extends DeepReader<IndividualEvaluation> {

	private IStudentDAO iStudentDAO;

	public IndividualEvaluationDeepReader(IEntityDAO<IndividualEvaluation> entityDAO, IStudentDAO iStudentDAO) {
		super(entityDAO);

		this.iStudentDAO = iStudentDAO;
	}

	@Override
	protected void retrieveData(IndividualEvaluation individualEvaluation) throws DAOException {
		Student student = iStudentDAO.select(individualEvaluation.getStudent().getId());
		individualEvaluation.setStudent(student);
	}
}
