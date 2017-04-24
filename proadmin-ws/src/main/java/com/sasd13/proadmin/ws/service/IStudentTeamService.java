package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.StudentTeam;

public interface IStudentTeamService {

	long create(StudentTeam studentTeam);

	void delete(StudentTeam studentTeam);

	List<StudentTeam> read(Map<String, String[]> parameters);
}
