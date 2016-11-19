package com.sasd13.proadmin.bean.running;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;

public class Running {

	private Project project;
	private Teacher teacher;
	private int year;

	public Running() {
	}

	public Running(int year, String projectCode, String teacherNumber) {
		this.year = year;
		project = new Project(projectCode);
		teacher = new Teacher(teacherNumber);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
