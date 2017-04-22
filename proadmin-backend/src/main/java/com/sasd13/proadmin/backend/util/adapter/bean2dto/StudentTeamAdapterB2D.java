package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.StudentTeam;
import com.sasd13.proadmin.backend.dao.dto.StudentTeamDTO;

public class StudentTeamAdapterB2D implements IAdapter<StudentTeam, StudentTeamDTO> {

	private StudentAdapterB2D studentAdapter;
	private TeamAdapterB2D teamAdapter;

	public StudentTeamAdapterB2D() {
		studentAdapter = new StudentAdapterB2D();
		teamAdapter = new TeamAdapterB2D();
	}

	@Override
	public StudentTeamDTO adapt(StudentTeam s) {
		StudentTeamDTO t = new StudentTeamDTO();

		t.setStudent(studentAdapter.adapt(s.getStudent()));
		t.setTeam(teamAdapter.adapt(s.getTeam()));

		return t;
	}
}
