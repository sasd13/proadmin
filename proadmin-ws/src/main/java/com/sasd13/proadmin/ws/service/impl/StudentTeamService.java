package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.StudentTeam;
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
	public long create(StudentTeam studentTeam) {
		return studentTeamDAO.create(studentTeam);
	}

	@Override
	public void delete(StudentTeam studentTeam) {
		studentTeamDAO.delete(studentTeam);
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		return studentTeamDeepReader.read(parameters);
	}

	@Override
	public List<StudentTeam> readAll() {
		return studentTeamDeepReader.readAll();
	}
}
