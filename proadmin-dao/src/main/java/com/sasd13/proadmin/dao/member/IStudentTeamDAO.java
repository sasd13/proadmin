package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface IStudentTeamDAO extends ISession<StudentTeam> {

	String TABLE = "studentteams";
	String COLUMN_STUDENT_CODE = "_student_code";
	String COLUMN_TEAM_CODE = "_team_code";
}
