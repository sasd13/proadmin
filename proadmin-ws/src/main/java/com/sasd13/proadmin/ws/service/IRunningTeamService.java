package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.RunningTeam;
import com.sasd13.proadmin.ws.bean.update.RunningTeamUpdate;

public interface IRunningTeamService {

	long create(RunningTeam runningTeam);

	void update(RunningTeamUpdate runningTeamUpdate);

	void delete(RunningTeam runningTeam);

	List<RunningTeam> read(Map<String, String[]> parameters);

	List<RunningTeam> readAll();
}
