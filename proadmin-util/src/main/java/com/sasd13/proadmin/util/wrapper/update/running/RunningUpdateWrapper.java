package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Running;

public class RunningUpdateWrapper implements IUpdateWrapper<Running> {

	private Running running;
	private int year;
	private String projectCode, teacherIntermediary;

	@Override
	public Running getWrapped() {
		return running;
	}

	@Override
	public void setWrapped(Running running) {
		this.running = running;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getTeacherIntermediary() {
		return teacherIntermediary;
	}

	public void setTeacherIntermediary(String teacherIntermediary) {
		this.teacherIntermediary = teacherIntermediary;
	}
}
