package com.sasd13.proadmin.ws2.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.ws2.dao.dto.TeamDTO;

public interface ITeamDAO {

	TeamDTO create(Team team);

	void update(List<TeamUpdateWrapper> updateWrappers);

	void delete(List<Team> teams);

	List<TeamDTO> read(Map<String, String[]> parameters);
}
