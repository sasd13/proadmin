package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.StudentTeam;
import com.sasd13.proadmin.backend.dao.IStudentTeamDAO;
import com.sasd13.proadmin.backend.dao.dto.StudentTeamDTO;
import com.sasd13.proadmin.backend.service.IStudentTeamService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.StudentTeamAdapterD2B;

@Service
public class StudentTeamService implements IStudentTeamService {

	@Autowired
	private IStudentTeamDAO studentTeamDAO;

	@Override
	public void create(StudentTeam studentTeam) {
		studentTeamDAO.create(studentTeam);
	}

	@Override
	public void delete(StudentTeam studentTeam) {
		studentTeamDAO.delete(studentTeam);
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		List<StudentTeam> studentTeams = new ArrayList<>();

		List<StudentTeamDTO> dtos = studentTeamDAO.read(parameters);
		StudentTeamAdapterD2B adapter = new StudentTeamAdapterD2B();

		for (StudentTeamDTO dto : dtos) {
			studentTeams.add(adapter.adapt(dto));
		}

		return studentTeams;
	}
}
