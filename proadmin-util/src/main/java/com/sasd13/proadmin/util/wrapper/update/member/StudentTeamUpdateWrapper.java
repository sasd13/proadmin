package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.proadmin.bean.member.StudentTeam;

public class StudentTeamUpdateWrapper implements IStudentTeamUpdateWrapper {

	private StudentTeam studentTeam;
	private String studentNumber, teamNumber;

	@Override
	public StudentTeam getWrapped() {
		return studentTeam;
	}

	@Override
	public void setWrapped(StudentTeam studentTeam) {
		this.studentTeam = studentTeam;
	}

	@Override
	public String getStudentNumber() {
		return studentNumber;
	}

	@Override
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	@Override
	public String getTeamNumber() {
		return teamNumber;
	}

	@Override
	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}
}
