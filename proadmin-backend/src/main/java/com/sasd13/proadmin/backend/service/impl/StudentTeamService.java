package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.dao.IStudentTeamDAO;
import com.sasd13.proadmin.backend.model.StudentTeam;
import com.sasd13.proadmin.backend.service.IStudentTeamService;

@Service
public class StudentTeamService implements IStudentTeamService {

	@Autowired
	private IStudentTeamDAO studentTeamDAO;

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
		return studentTeamDAO.read(parameters);
	}
}
