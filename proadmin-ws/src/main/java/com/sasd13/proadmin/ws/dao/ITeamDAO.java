package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

public interface ITeamDAO extends IReader<ITeam> {

	String TABLE = "teams";
	String COLUMN_CODE = "_code";
	String COLUMN_NAME = "_name";

	long create(ITeam iTeam);

	void update(TeamUpdateWrapper updateWrapper);

	void delete(ITeam iTeam);
}
