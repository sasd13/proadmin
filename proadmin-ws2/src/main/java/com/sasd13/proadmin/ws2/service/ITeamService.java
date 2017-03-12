package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

public interface ITeamService {

	void create(Team team);

	void update(List<TeamUpdateWrapper> updateWrappers);

	void delete(List<Team> teams);

	List<Team> read(Map<String, String[]> parameters);
}
