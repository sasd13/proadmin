package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.LeadEvaluation;

public class LeadEvaluationDeepReader extends DeepReader<LeadEvaluation> {
	
	private StudentDAO studentDAO;

	public LeadEvaluationDeepReader(IEntityDAO<LeadEvaluation> entityDAO, StudentDAO studentDAO) {
		super(entityDAO);
		
		this.studentDAO = studentDAO;
	}

	@Override
	protected void retrieveData(LeadEvaluation leadEvaluation) {
		leadEvaluation.setStudent(studentDAO.select(leadEvaluation.getStudent().getId()));
	}
}
