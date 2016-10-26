package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.member.IStudentDAO;
import com.sasd13.proadmin.util.Binder;

public class LeadEvaluationDeepReader extends DeepReader<LeadEvaluation> {

	private IStudentDAO studentDAO;

	public LeadEvaluationDeepReader(IEntityDAO<LeadEvaluation> entityDAO, IStudentDAO studentDAO) {
		super(entityDAO);

		this.studentDAO = studentDAO;
	}

	@Override
	protected void retrieveData(LeadEvaluation leadEvaluation) throws DAOException {
		Student student = studentDAO.select(leadEvaluation.getStudent().getId());
		Binder.bind(leadEvaluation.getStudent(), student);
	}
}
