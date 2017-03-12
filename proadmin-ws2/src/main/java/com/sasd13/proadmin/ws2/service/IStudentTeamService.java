package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.StudentTeam;

public interface IStudentTeamService {

	void create(StudentTeam studentTeam);

	void delete(List<StudentTeam> studentTeams);

	List<StudentTeam> read(Map<String, String[]> parameters);
}
