package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.StudentTeam;
import com.sasd13.proadmin.backend.dao.dto.StudentTeamDTO;

public interface IStudentTeamDAO {

	List<StudentTeamDTO> create(List<StudentTeam> studentTeams);

	void delete(List<StudentTeam> studentTeams);

	List<StudentTeamDTO> read(Map<String, String[]> parameters);
}
