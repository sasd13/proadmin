package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Team;
import com.sasd13.proadmin.backend.dao.dto.TeamDTO;

public interface ITeamDAO {

	TeamDTO create(Team team);

	void update(Team team);

	void delete(Team team);

	List<TeamDTO> read(Map<String, String[]> parameters);
}
