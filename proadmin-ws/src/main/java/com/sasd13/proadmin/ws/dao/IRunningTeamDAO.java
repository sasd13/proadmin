package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.IRunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

public interface IRunningTeamDAO extends IReader<IRunningTeam> {

	String TABLE = "runningteams";
	String COLUMN_YEAR = "_year";
	String COLUMN_PROJECT = "_project";
	String COLUMN_TEACHER = "_teacher";
	String COLUMN_TEAM = "_team";
	String COLUMN_ACADEMICLEVEL = "_academiclevel";

	long create(IRunningTeam iRunningTeam);

	void update(RunningTeamUpdateWrapper updateWrapper);

	void delete(IRunningTeam iRunningTeam);
}
