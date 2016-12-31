package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.RunningTeam;

public interface IRunningTeamUpdateWrapper extends IUpdateWrapper<RunningTeam> {

	int getRunningYear();

	void setRunningYear(int runningYear);

	String getProjectCode();

	void setProjectCode(String projectCode);

	String getTeacherNumber();

	void setTeacherNumber(String teacherNumber);

	String getTeamNumber();

	void setTeamNumber(String teamNumber);

	String getAcademicLevelCode();

	void setAcademicLevelCode(String academicLevelCode);
}
