package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.IStudentTeamDAO;
import com.sasd13.proadmin.dao.StudentTeamDeepReader;
import com.sasd13.proadmin.service.IStudentTeamService;

public class StudentTeamService implements IStudentTeamService {

	private IStudentTeamDAO session;
	private StudentTeamDeepReader deepReader;

	public StudentTeamService(DAO dao) {
		session = (IStudentTeamDAO) dao.getSession(IStudentTeamDAO.class);
		deepReader = (StudentTeamDeepReader) dao.getDeepReader(StudentTeamDeepReader.class);
	}

	@Override
	public long create(StudentTeam studentTeam) {
		return session.create(studentTeam);
	}

	@Override
	public void delete(StudentTeam studentTeam) {
		session.delete(studentTeam);
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		return deepReader.read(parameters);
	}

	@Override
	public List<StudentTeam> readAll() {
		return deepReader.readAll();
	}
}
