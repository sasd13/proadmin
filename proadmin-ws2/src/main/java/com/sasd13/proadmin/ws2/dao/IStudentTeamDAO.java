package com.sasd13.proadmin.ws2.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.ws2.dao.dto.StudentTeamDTO;

public interface IStudentTeamDAO {

	StudentTeamDTO create(StudentTeam studentTeam);

	void delete(List<StudentTeam> studentTeams);

	List<StudentTeamDTO> read(Map<String, String[]> parameters);
}
