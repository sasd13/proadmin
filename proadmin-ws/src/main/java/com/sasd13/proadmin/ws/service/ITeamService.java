package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.ws.bean.update.TeamUpdate;

public interface ITeamService {

	long create(ITeam iTeam);

	void update(TeamUpdate updateWrapper);

	void delete(ITeam iTeam);

	List<ITeam> read(Map<String, String[]> parameters);

	List<ITeam> readAll();
}
