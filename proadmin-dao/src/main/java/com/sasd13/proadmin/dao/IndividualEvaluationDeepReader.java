package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public class IndividualEvaluationDeepReader extends DeepReader<IndividualEvaluation> {
	
	private StudentDAO studentDAO;

	public IndividualEvaluationDeepReader(IEntityDAO<IndividualEvaluation> entityDAO, StudentDAO studentDAO) {
		super(entityDAO);
		
		this.studentDAO = studentDAO;
	}

	@Override
	protected void retrieveData(IndividualEvaluation individualEvaluation) {
		Student student = studentDAO.select(individualEvaluation.getStudent().getId());
		individualEvaluation.setStudent(student);
	}
}
