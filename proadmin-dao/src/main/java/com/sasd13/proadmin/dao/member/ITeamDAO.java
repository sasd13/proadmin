package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Team;

public interface ITeamDAO extends IEntityDAO<Team> {

	String TABLE = "teams";
	String COLUMN_ID = "id";
	String COLUMN_CODE = "code";
}
