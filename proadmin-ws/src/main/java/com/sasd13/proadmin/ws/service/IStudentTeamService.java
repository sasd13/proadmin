package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.IStudentTeam;

public interface IStudentTeamService {

	long create(IStudentTeam iStudentTeam);

	void delete(IStudentTeam iStudentTeam);

	List<IStudentTeam> read(Map<String, String[]> parameters);

	List<IStudentTeam> readAll();
}
