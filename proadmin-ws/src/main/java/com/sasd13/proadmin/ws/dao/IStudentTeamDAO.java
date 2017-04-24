package com.sasd13.proadmin.ws.dao;

import java.util.List;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.ws.bean.StudentTeam;

public interface IStudentTeamDAO extends IReader<StudentTeam> {

	String TABLE = "studentteams";
	String COLUMN_STUDENT = "_student";
	String COLUMN_TEAM = "_team";

	void create(List<StudentTeam> studentTeams);

	void delete(List<StudentTeam> studentTeams);
}
