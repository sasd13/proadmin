package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.entity.RunningTeam;

public interface IRunningTeamDAO {

	RunningTeam create(RunningTeam runningTeam);

	void update(RunningTeam runningTeam);

	void delete(RunningTeam runningTeam);

	List<RunningTeam> read(Map<String, Object> criterias);
}
