package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.proadmin.bean.running.Running;

public class RunningUpdateWrapper implements IRunningUpdateWrapper {

	private Running running;
	private int year;
	private String projectCode, teacherNumber;

	@Override
	public Running getWrapped() {
		return running;
	}

	@Override
	public void setWrapped(Running running) {
		this.running = running;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String getProjectCode() {
		return projectCode;
	}

	@Override
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Override
	public String getTeacherNumber() {
		return teacherNumber;
	}

	@Override
	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
}
