package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.dao.IStudentTeamDAO;
import com.sasd13.proadmin.backend.dao.dto.StudentTeamDTO;
import com.sasd13.proadmin.backend.service.IStudentTeamService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.StudentTeamDTOAdapter;
import com.sasd13.proadmin.bean.member.StudentTeam;

@Service
public class StudentTeamService implements IStudentTeamService {

	@Autowired
	private IStudentTeamDAO studentTeamDAO;

	@Override
	public void create(StudentTeam studentTeam) {
		studentTeamDAO.create(studentTeam);
	}

	@Override
	public void delete(List<StudentTeam> studentTeams) {
		studentTeamDAO.delete(studentTeams);
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		List<StudentTeam> studentTeams = new ArrayList<>();

		List<StudentTeamDTO> dtos = studentTeamDAO.read(parameters);
		StudentTeamDTOAdapter adapter = new StudentTeamDTOAdapter();

		for (StudentTeamDTO dto : dtos) {
			studentTeams.add(adapter.adapt(dto));
		}

		return studentTeams;
	}
}
