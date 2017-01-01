package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface IStudentTeamDAO extends ISession<StudentTeam> {

	String TABLE = "studentteams";
	String COLUMN_STUDENT = "_student";
	String COLUMN_TEAM = "_team";
}
