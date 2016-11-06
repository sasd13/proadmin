package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.builder.member.TeacherBaseBuilder;
import com.sasd13.proadmin.util.builder.project.ProjectBaseBuilder;

public class RunningBaseBuilder implements IBuilder<Running> {

	private int year;
	private String projectCode, teacherNumber;

	public RunningBaseBuilder(int year, String projectCode, String teacherNumber) {
		this.year = year;
		this.projectCode = projectCode;
		this.teacherNumber = teacherNumber;
	}

	@Override
	public Running build() {
		Running running = new Running();
		running.setYear(year);
		running.setProject(new ProjectBaseBuilder(projectCode).build());
		running.setTeacher(new TeacherBaseBuilder(teacherNumber).build());

		return running;
	}
}
