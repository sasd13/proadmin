package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface StudentTeamDAO extends IEntityDAO<StudentTeam> {
	
	String TABLE = "studentteams";
	
	String COLUMN_ID = "studentteam_id";
	String COLUMN_STUDENT_ID = "studentteam_student_id";
	String COLUMN_TEAM_ID = "studentteam_team_id";
}
