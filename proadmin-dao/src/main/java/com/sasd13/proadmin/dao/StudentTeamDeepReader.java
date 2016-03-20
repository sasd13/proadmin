package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

public class StudentTeamDeepReader extends DeepReader<StudentTeam> {
	
	private StudentDAO studentDAO;
	private TeamDAO teamDAO;
	
	public StudentTeamDeepReader(IEntityDAO<StudentTeam> entityDAO, StudentDAO studentDAO, TeamDAO teamDAO) {
		super(entityDAO);
		
		this.studentDAO = studentDAO;
		this.teamDAO = teamDAO;
	}
	
	@Override
	protected void retrieveData(StudentTeam studentTeam) {
		Student student = studentDAO.select(studentTeam.getStudent().getId());
		studentTeam.getStudent().setAcademicLevel(student.getAcademicLevel());
		studentTeam.getStudent().setNumber(student.getNumber());
		studentTeam.getStudent().setFirstName(student.getFirstName());
		studentTeam.getStudent().setLastName(student.getLastName());
		studentTeam.getStudent().setEmail(student.getEmail());
		
		Team team = teamDAO.select(studentTeam.getTeam().getId());
		studentTeam.getTeam().setCode(team.getCode());
	}
}
