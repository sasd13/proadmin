package com.sasd13.proadmin.ws2.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.ws2.dao.dto.StudentTeamDTO;

public class StudentTeamDTOAdapter implements IAdapter<StudentTeamDTO, StudentTeam> {
	
	private StudentDTOAdapter studentDTOAdapter;
	private TeamDTOAdapter teamDTOAdapter;
	
	public StudentTeamDTOAdapter() {
		studentDTOAdapter = new StudentDTOAdapter();
		teamDTOAdapter = new TeamDTOAdapter();
	}

	@Override
	public StudentTeam adapt(StudentTeamDTO source) {
		StudentTeam target = new StudentTeam();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(StudentTeamDTO source, StudentTeam target) {
		target.setStudent(studentDTOAdapter.adapt(source.getStudent()));
		target.setTeam(teamDTOAdapter.adapt(source.getTeam()));
	}
}
