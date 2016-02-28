package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.StudentTeam;

public class StudentTeamDeepReader extends DeepReader<StudentTeam> {
	
	private StudentDAO studentDAO;
	
	public StudentTeamDeepReader(IEntityDAO<StudentTeam> entityDAO, StudentDAO studentDAO) {
		super(entityDAO);
		
		this.studentDAO = studentDAO;
	}
	
	@Override
	protected void retrieveData(StudentTeam studentTeam) {
		studentTeam.setStudent(studentDAO.select(studentTeam.getStudent().getId()));
	}
}
