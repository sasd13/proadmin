package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface StudentTeamDAO extends IEntityDAO<StudentTeam> {

	String TABLE = "studentteams";

	String COLUMN_ID = "id";
	String COLUMN_STUDENT_ID = "student_id";
	String COLUMN_TEAM_ID = "team_id";
}
