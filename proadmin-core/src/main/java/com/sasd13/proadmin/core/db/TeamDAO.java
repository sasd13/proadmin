package com.sasd13.proadmin.core.db;

import java.util.List;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.Team;

public interface TeamDAO extends IEntityDAO<Team> {
	
	String TEAM_TABLE_NAME = "teams";
	
	String TEAM_ID = "team_id";
	String TEAM_CODE = "team_code";
	String RUNNINGS_RUNNING_ID = "runnings_running_id";
	
	Team selectByCode(String code);
	
	List<Team> selectByRunning(long runningId);
}
