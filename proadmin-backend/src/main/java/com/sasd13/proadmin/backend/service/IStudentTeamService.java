package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.StudentTeam;

public interface IStudentTeamService {

	void create(StudentTeam studentTeam);

	void delete(StudentTeam studentTeam);

	List<StudentTeam> read(Map<String, String[]> parameters);
}
