package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.javaex.dao.hibernate.ISession;
import com.sasd13.proadmin.bean.member.Team;

public interface ITeamDAO extends ISession<Team> {

	String TABLE = "teams";
	String COLUMN_CODE = "_code";
}
