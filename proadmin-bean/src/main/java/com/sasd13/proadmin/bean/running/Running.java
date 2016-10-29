package com.sasd13.proadmin.bean.running;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;

public class Running {

	private Project project;
	private int year;
	private Teacher teacher;

	public Running(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Running [");
		builder.append("year=" + getYear());
		builder.append("]");

		return builder.toString();
	}
}
