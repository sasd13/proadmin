package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.RunningTeam;

public interface IRunningTeamService {

	void create(RunningTeam runningTeam);

	void update(RunningTeam runningTeam);

	void delete(RunningTeam runningTeam);

	List<RunningTeam> read(Map<String, String[]> parameters);
}
