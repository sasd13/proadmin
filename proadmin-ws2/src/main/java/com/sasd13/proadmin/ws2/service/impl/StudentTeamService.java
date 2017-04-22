package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.ws2.dao.IStudentTeamDAO;
import com.sasd13.proadmin.ws2.dao.dto.StudentTeamDTO;
import com.sasd13.proadmin.ws2.service.IStudentTeamService;
import com.sasd13.proadmin.ws2.util.adapter.dto2bean.StudentTeamDTOAdapter;

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
