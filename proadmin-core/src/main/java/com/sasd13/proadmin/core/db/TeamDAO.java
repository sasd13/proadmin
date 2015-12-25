package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.Team;

public interface TeamDAO extends IEntityDAO<Team> {
	
	String TABLE = "teams";
	
	String COLUMN_ID = "team_id";
	String COLUMN_CODE = "team_code";
	String COLUMN_RUNNING_ID = "team_running_id";
}
