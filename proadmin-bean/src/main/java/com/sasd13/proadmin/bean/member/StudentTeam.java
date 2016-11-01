package com.sasd13.proadmin.bean.member;

public class StudentTeam {

	private Student student;
	private Team team;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("StudentTeam [");
		builder.append("]");

		return builder.toString();
	}
}
