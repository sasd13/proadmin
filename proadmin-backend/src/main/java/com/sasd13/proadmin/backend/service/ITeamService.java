package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Team;

public interface ITeamService {

	void create(Team teacher);

	void update(Team teacher);

	void delete(Team teacher);

	List<Team> read(Map<String, String[]> parameters);
}
