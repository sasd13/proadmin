package com.sasd13.proadmin.bean.running;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;

public class Running {

	private Project project;
	private Teacher teacher;
	private int year;

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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Running other = (Running) obj;

		if (year != other.year)
			return false;

		if (project == null && other.project != null)
			return false;
		else if (!project.equals(other.project))
			return false;

		if (teacher == null && other.teacher != null)
			return false;
		else if (!teacher.equals(other.teacher))
			return false;

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Running [");
		builder.append("year=" + year);
		builder.append(", project=" + project);
		builder.append(", teacher=" + teacher);
		builder.append("]");

		return builder.toString();
	}
}
