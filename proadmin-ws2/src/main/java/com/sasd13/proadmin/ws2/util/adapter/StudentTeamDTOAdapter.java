package com.sasd13.proadmin.ws2.util.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.ws2.db.dto.StudentTeamDTO;

public class StudentTeamDTOAdapter implements IAdapter<StudentTeamDTO, StudentTeam> {

	@Override
	public StudentTeam adapt(StudentTeamDTO source) {
		StudentTeam target = new StudentTeam();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(StudentTeamDTO source, StudentTeam target) {
		target.setStudent(new StudentDTOAdapter().adapt(source.getStudent()));
		target.setTeam(new TeamDTOAdapter().adapt(source.getTeam()));
	}
}
