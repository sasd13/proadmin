package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IStudentTeamDAO;
import com.sasd13.proadmin.backend.model.StudentTeam;
import com.sasd13.proadmin.backend.service.IStudentTeamService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
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
	public List<StudentTeam> read(Map<String, Object> criterias) {
		return studentTeamDAO.read(criterias);
	}
}
