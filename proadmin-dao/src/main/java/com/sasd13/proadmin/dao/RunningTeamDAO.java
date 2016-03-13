package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.running.RunningTeam;

public interface RunningTeamDAO extends IEntityDAO<RunningTeam> {
	
	String TABLE = "runningteams";
	
	String COLUMN_ID = "runningteam_id";
	String COLUMN_RUNNING_ID = "runningteam_running_id";
	String COLUMN_TEAM_ID = "runningteam_team_id";
}
