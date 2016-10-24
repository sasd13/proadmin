package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.running.RunningTeam;

public interface IRunningTeamDAO extends IEntityDAO<RunningTeam> {

	String TABLE = "runningteams";
	String COLUMN_ID = "id";
	String COLUMN_RUNNING_ID = "running_id";
	String COLUMN_TEAM_ID = "team_id";
}
