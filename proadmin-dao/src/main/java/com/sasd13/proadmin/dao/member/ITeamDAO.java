package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.member.Team;

public interface ITeamDAO extends ISession<Team> {

	String TABLE = "teams";
	String COLUMN_CODE = "code";
}
