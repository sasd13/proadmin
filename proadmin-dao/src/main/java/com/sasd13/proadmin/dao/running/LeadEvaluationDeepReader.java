package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.member.IStudentDAO;

public class LeadEvaluationDeepReader extends DeepReader<LeadEvaluation> {

	private IStudentDAO iStudentDAO;

	public LeadEvaluationDeepReader(IEntityDAO<LeadEvaluation> entityDAO, IStudentDAO iStudentDAO) {
		super(entityDAO);

		this.iStudentDAO = iStudentDAO;
	}

	@Override
	protected void retrieveData(LeadEvaluation leadEvaluation) throws DAOException {
		Student student = iStudentDAO.select(leadEvaluation.getStudent().getId());
		leadEvaluation.setStudent(student);
	}
}
