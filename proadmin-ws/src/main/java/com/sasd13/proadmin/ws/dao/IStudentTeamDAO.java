package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface IStudentTeamDAO extends IReader<StudentTeam> {

	String TABLE = "studentteams";
	String COLUMN_STUDENT = "_student";
	String COLUMN_TEAM = "_team";

	long create(StudentTeam studentTeam);

	void delete(StudentTeam studentTeam);
}
