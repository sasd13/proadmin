package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.javaex.dao.hibernate.ISession;
import com.sasd13.proadmin.bean.running.RunningTeam;

public interface IRunningTeamDAO extends ISession<RunningTeam> {

	String TABLE = "runningteams";
	String COLUMN_RUNNING = "_running";
	String COLUMN_TEAM = "_team";
	String COLUMN_ACADEMICLEVEL = "_academiclevel";
}
