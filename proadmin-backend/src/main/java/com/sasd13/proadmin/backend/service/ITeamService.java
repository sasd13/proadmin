package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Team;

public interface ITeamService {

	void create(Team team);

	void update(Team team);

	void delete(Team team);

	List<Team> read(Map<String, String[]> parameters);
}
