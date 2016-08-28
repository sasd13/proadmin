package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public class LeadEvaluationDeepReader extends DeepReader<LeadEvaluation> {

	private StudentDAO studentDAO;

	public LeadEvaluationDeepReader(IEntityDAO<LeadEvaluation> entityDAO, StudentDAO studentDAO) {
		super(entityDAO);

		this.studentDAO = studentDAO;
	}

	@Override
	protected void retrieveData(LeadEvaluation leadEvaluation) throws DAOException {
		Student student = studentDAO.select(leadEvaluation.getStudent().getId());
		leadEvaluation.setStudent(student);
	}
}
