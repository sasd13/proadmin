package com.sasd13.proadmin.util.builder.member;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.StudentTeam;

public class StudentTeamBaseBuilder implements IBuilder<StudentTeam> {

	private String studentNumber, teamNumber;

	public StudentTeamBaseBuilder(String studentNumber, String teamNumber) {
		this.studentNumber = studentNumber;
		this.teamNumber = teamNumber;
	}

	@Override
	public StudentTeam build() {
		StudentTeam studentTeam = new StudentTeam();
		studentTeam.setStudent(new StudentBaseBuilder(studentNumber).build());
		studentTeam.setTeam(new TeamBaseBuilder(teamNumber).build());

		return studentTeam;
	}
}
