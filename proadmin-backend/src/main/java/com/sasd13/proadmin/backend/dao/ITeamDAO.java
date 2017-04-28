package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.model.Team;

public interface ITeamDAO {

	Team create(Team team);

	void update(Team team);

	void delete(Team team);

	List<Team> read(Map<String, String[]> parameters);
}
