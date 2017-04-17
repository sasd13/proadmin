package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.StudentTeam;

public class StudentTeamUpdateWrapper implements IUpdateWrapper<StudentTeam> {

	private StudentTeam studentTeam;
	private String studentIntermediary, teamNumber;

	@Override
	public StudentTeam getWrapped() {
		return studentTeam;
	}

	@Override
	public void setWrapped(StudentTeam studentTeam) {
		this.studentTeam = studentTeam;
	}

	public String getStudentIntermediary() {
		return studentIntermediary;
	}

	public void setStudentIntermediary(String studentIntermediary) {
		this.studentIntermediary = studentIntermediary;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}
}
