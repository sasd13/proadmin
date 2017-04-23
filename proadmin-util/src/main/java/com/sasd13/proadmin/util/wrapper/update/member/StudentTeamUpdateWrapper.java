package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.IStudentTeam;

public class StudentTeamUpdateWrapper implements IUpdateWrapper<IStudentTeam> {

	private IStudentTeam iStudentTeam;
	private String studentIntermediary, teamNumber;

	@Override
	public IStudentTeam getWrapped() {
		return iStudentTeam;
	}

	@Override
	public void setWrapped(IStudentTeam iStudentTeam) {
		this.iStudentTeam = iStudentTeam;
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
