package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

public interface IRunningTeamService {

	void create(RunningTeam runningTeam);

	void update(List<RunningTeamUpdateWrapper> updateWrappers);

	void delete(List<RunningTeam> runningTeams);

	List<RunningTeam> read(Map<String, String[]> parameters);
}
