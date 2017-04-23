package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.member.IStudentTeam;

public interface IStudentTeamDAO extends IReader<IStudentTeam> {

	String TABLE = "studentteams";
	String COLUMN_STUDENT = "_student";
	String COLUMN_TEAM = "_team";

	long create(IStudentTeam iStudentTeam);

	void delete(IStudentTeam iStudentTeam);
}
