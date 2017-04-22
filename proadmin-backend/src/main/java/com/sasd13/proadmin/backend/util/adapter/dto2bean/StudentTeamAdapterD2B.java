package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.StudentTeam;
import com.sasd13.proadmin.backend.dao.dto.StudentTeamDTO;

public class StudentTeamAdapterD2B implements IAdapter<StudentTeamDTO, StudentTeam> {

	private StudentAdapterD2B studentAdapter;
	private TeamAdapterD2B teamAdapter;

	public StudentTeamAdapterD2B() {
		studentAdapter = new StudentAdapterD2B();
		teamAdapter = new TeamAdapterD2B();
	}

	@Override
	public StudentTeam adapt(StudentTeamDTO s) {
		StudentTeam t = new StudentTeam();

		t.setStudent(studentAdapter.adapt(s.getStudent()));
		t.setTeam(teamAdapter.adapt(s.getTeam()));

		return t;
	}
}
