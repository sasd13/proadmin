package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Team;

public interface TeamDAO extends IEntityDAO<Team> {
	
	String TABLE = "teams";
	
	String COLUMN_ID = "id";
	String COLUMN_CODE = "code";
}
