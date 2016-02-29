package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.StudentTeam;
import com.sasd13.proadmin.core.bean.running.handler.StudentTeamHandler;

public class StudentTeamDeepReader extends DeepReader<StudentTeam> {
	
	private StudentDAO studentDAO;
	
	public StudentTeamDeepReader(IEntityDAO<StudentTeam> entityDAO, StudentDAO studentDAO) {
		super(entityDAO);
		
		this.studentDAO = studentDAO;
	}
	
	@Override
	protected void retrieveData(StudentTeam studentTeam) {
		Student student = studentDAO.select(studentTeam.getStudent().getId());
		StudentTeamHandler.setStudenToStudentTeam(student, studentTeam);
	}
}
