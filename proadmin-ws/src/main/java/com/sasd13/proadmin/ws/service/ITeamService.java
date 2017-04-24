package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.bean.update.TeamUpdate;

public interface ITeamService {

	long create(Team team);

	void update(TeamUpdate teamUpdate);

	void delete(Team team);

	List<Team> read(Map<String, String[]> parameters);
}
