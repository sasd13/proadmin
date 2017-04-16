package com.sasd13.proadmin.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

public interface ITeamService {

	long create(Team team);

	void update(TeamUpdateWrapper updateWrapper);

	void delete(Team team);

	List<Team> read(Map<String, String[]> parameters);

	List<Team> readAll();
}