package com.sasd13.proadmin.core.bean.running;

import com.sasd13.proadmin.core.bean.member.Student;

public class StudentTeam {
	
	private long id;
	private Student student;
	private Team team;
	
	public StudentTeam() {}
	
	public StudentTeam(Student student, Team team) {
		this.student = student;
		this.team = team;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
		builder.append("id=" + getId());
		builder.append("]");
		
		return builder.toString().trim();
	}
}
