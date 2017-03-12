package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.ws2.db.dto.StudentTeamDTO;

public interface IStudentTeamDAO {

	StudentTeamDTO create(StudentTeam studentTeam);

	void delete(StudentTeam studentTeam);

	List<StudentTeamDTO> read(Map<String, String[]> parameters);
}
