package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.member.Team;

public interface ITeamDAO extends ISession<Team> {

	String TABLE = "teams";
	String COLUMN_CODE = "_code";
	String COLUMN_NAME = "_name";
}
