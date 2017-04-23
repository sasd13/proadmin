package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IRunning;

public class RunningUpdateWrapper implements IUpdateWrapper<IRunning> {

	private IRunning iRunning;
	private int year;
	private String projectCode, teacherIntermediary;

	@Override
	public IRunning getWrapped() {
		return iRunning;
	}

	@Override
	public void setWrapped(IRunning iRunning) {
		this.iRunning = iRunning;
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
