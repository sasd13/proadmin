package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.dao.dto.StudentTeamDTO;
import com.sasd13.proadmin.bean.member.StudentTeam;

public interface IStudentTeamDAO {

	StudentTeamDTO create(StudentTeam studentTeam);

	void delete(List<StudentTeam> studentTeams);

	List<StudentTeamDTO> read(Map<String, String[]> parameters);
}
