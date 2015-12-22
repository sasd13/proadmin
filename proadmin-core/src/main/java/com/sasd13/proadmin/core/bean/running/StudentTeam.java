package com.sasd13.proadmin.core.bean.running;

import com.sasd13.proadmin.core.bean.member.Student;

public class StudentTeam {
	
	private long id;
	private Student student;
	private Team team;
	
	public Student getStudent() {
		return student;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
		builder.append(", id=" + getId());
		builder.append(", student=" + getStudent());
		builder.append(", team=" + getTeam());
		builder.append("]");
		
		return builder.toString();
	}
}
