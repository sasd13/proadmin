package com.sasd13.proadmin.core.bean.running;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;

public class Running {
	
	private long id;
	private int year;
	private Teacher teacher;
	private Project project;
	private List<Team> teams;
	
	public Running() {
		year = Calendar.getInstance().get(Calendar.YEAR);
		teams = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public void addTeam(Team team) {
		teams.add(team);
	}
	
	public void removeTeam(Team team) {
		teams.remove(team);
	}
	
	public Team[] getTeams() {
		return teams.toArray(new Team[teams.size()]);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Running [");
		builder.append("id=" + getId());
		builder.append(", year=" + getYear());
		builder.append("]");
		
		return builder.toString().trim();
	}
}
