package com.sasd13.proadmin.bean.member;

public class StudentTeam {

	private Student student;
	private Team team;

	public StudentTeam(Student student, Team team) {
		this.student = student;
		this.team = team;
	}

	public Student getStudent() {
		return student;
	}

	public Team getTeam() {
		return team;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("StudentTeam [");
		builder.append("]");

		return builder.toString();
	}
}
