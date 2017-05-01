package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.StudentTeam;

public interface IStudentTeamService {

	void create(List<StudentTeam> studentTeams);

	void delete(List<StudentTeam> studentTeams);

	List<StudentTeam> read(Map<String, String[]> criterias);
}
