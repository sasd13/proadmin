package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.member.Team;

public interface ITeamDAO extends IManager<Team>, IReader<Team> {

	String TABLE = "teams";
	String COLUMN_CODE = "code";
}
