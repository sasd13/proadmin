package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.StudentTeam;
import com.sasd13.proadmin.backend.dao.dto.StudentTeamDTO;

public interface IStudentTeamDAO {

	StudentTeamDTO create(StudentTeam studentTeam);

	void delete(StudentTeam studentTeam);

	List<StudentTeamDTO> read(Map<String, String[]> parameters);
}
