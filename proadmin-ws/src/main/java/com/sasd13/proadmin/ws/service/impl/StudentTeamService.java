package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.StudentTeam;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IStudentTeamDAO;
import com.sasd13.proadmin.ws.dao.jdbc.StudentTeamDeepReader;
import com.sasd13.proadmin.ws.service.IStudentTeamService;

public class StudentTeamService implements IStudentTeamService {

	private IStudentTeamDAO studentTeamDAO;
	private StudentTeamDeepReader studentTeamDeepReader;

	public StudentTeamService(DAO dao) {
		studentTeamDAO = (IStudentTeamDAO) dao.getSession(IStudentTeamDAO.class);
		studentTeamDeepReader = (StudentTeamDeepReader) dao.getDeepReader(StudentTeamDeepReader.class);
	}

	@Override
	public void create(List<StudentTeam> studentTeams) {
		studentTeamDAO.create(studentTeams);
	}

	@Override
	public void delete(List<StudentTeam> studentTeams) {
		studentTeamDAO.delete(studentTeams);
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		return studentTeamDeepReader.read(parameters);
	}
}
