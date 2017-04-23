package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.StudentTeam;
import com.sasd13.proadmin.backend.dao.dto.StudentDTO;
import com.sasd13.proadmin.backend.dao.dto.StudentTeamDTO;
import com.sasd13.proadmin.backend.dao.dto.TeamDTO;

public class StudentTeamAdapterB2D implements IAdapter<StudentTeam, StudentTeamDTO> {

	@Override
	public StudentTeamDTO adapt(StudentTeam s) {
		StudentTeamDTO t = new StudentTeamDTO();

		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(s.getStudent().getId());
		t.setStudent(studentDTO);

		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setId(s.getTeam().getId());
		t.setTeam(teamDTO);

		return t;
	}
}
