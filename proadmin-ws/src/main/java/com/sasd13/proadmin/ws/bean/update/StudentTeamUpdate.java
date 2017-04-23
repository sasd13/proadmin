package com.sasd13.proadmin.ws.bean.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.ws.bean.StudentTeam;

public class StudentTeamUpdate implements IUpdateWrapper<StudentTeam> {

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
