package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.javaex.dao.hibernate.ISession;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface IStudentTeamDAO extends ISession<StudentTeam> {

	String TABLE = "studentteams";
	String COLUMN_STUDENT = "_student";
	String COLUMN_TEAM = "_team";
}
