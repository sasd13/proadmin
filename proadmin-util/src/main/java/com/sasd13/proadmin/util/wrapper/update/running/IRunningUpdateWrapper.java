package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Running;

public interface IRunningUpdateWrapper extends IUpdateWrapper<Running> {

	int getYear();

	void setYear(int year);

	String getProjectCode();

	void setProjectCode(String projectCode);

	String getTeacherNumber();

	void setTeacherNumber(String teacherNumber);
}
