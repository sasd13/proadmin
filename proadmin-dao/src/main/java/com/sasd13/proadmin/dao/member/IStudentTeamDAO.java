package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface IStudentTeamDAO extends IEntityDAO<StudentTeam> {

	String TABLE = "studentteams";
	String COLUMN_ID = "id";
	String COLUMN_STUDENT_ID = "student_id";
	String COLUMN_TEAM_ID = "team_id";
}
