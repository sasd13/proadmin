package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

public interface ITeamDAO extends IReader<Team> {

	String TABLE = "teams";
	String COLUMN_CODE = "_code";
	String COLUMN_NAME = "_name";

	long create(Team team);

	void update(TeamUpdateWrapper updateWrapper);

	void delete(Team team);
}
