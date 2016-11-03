package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.running.RunningTeam;

public interface IRunningTeamDAO extends IEntityDAO<RunningTeam> {

	String TABLE = "runningteams";
	String COLUMN_RUNNING_YEAR = "running_year";
	String COLUMN_RUNNING_PROJECT_CODE = "running_project_code";
	String COLUMN_RUNNING_TEACHER_CODE = "running_teacher_code";
	String COLUMN_TEAM_CODE = "team_code";
	String COLUMN_ACADEMICLEVEL_CODE = "academiclevel_code";
}
