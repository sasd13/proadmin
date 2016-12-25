package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.running.RunningTeam;

public interface IRunningTeamDAO extends ISession<RunningTeam> {

	String TABLE = "runningteams";
	String COLUMN_RUNNING_YEAR = "_running_year";
	String COLUMN_RUNNING_PROJECT_CODE = "_running_project_code";
	String COLUMN_RUNNING_TEACHER_CODE = "_running_teacher_code";
	String COLUMN_TEAM_CODE = "_team_code";
	String COLUMN_ACADEMICLEVEL_CODE = "_academiclevel_code";
}
