package com.sasd13.proadmin.core.bean.running;

import java.util.Calendar;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;

public class Running {
	
	private long id;
	private int year;
	private Teacher teacher;
	private Project project;
	
	public Running() {
		year = Calendar.getInstance().get(Calendar.YEAR);
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
