package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.bean.update.TeamUpdate;

public interface ITeamDAO extends IReader<Team> {

	String TABLE = "teams";
	String COLUMN_CODE = "_code";
	String COLUMN_NAME = "_name";

	long create(Team team);

	void update(TeamUpdate teamUpdate);

	void delete(Team team);
}
