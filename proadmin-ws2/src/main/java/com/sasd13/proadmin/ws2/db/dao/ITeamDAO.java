package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.ITeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.TeamDTO;

public interface ITeamDAO {

	TeamDTO create(Team team);

	void update(ITeamUpdateWrapper updateWrapper);

	void delete(Team team);

	List<Team> read(Map<String, String[]> parameters);
}
