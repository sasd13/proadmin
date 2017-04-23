package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

public interface ITeamService {

	long create(ITeam iTeam);

	void update(TeamUpdateWrapper updateWrapper);

	void delete(ITeam iTeam);

	List<ITeam> read(Map<String, String[]> parameters);

	List<ITeam> readAll();
}
