package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.model.StudentTeam;

public interface IStudentTeamDAO {

	List<StudentTeam> create(List<StudentTeam> studentTeams);

	void delete(List<StudentTeam> studentTeams);

	List<StudentTeam> read(Map<String, Object> criterias);
}
