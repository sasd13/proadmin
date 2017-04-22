package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.backend.dao.dto.RunningTeamDTO;

public interface IRunningTeamDAO {

	RunningTeamDTO create(RunningTeam runningTeam);

	void update(RunningTeam runningTeam);

	void delete(RunningTeam runningTeam);

	List<RunningTeamDTO> read(Map<String, String[]> parameters);
}
