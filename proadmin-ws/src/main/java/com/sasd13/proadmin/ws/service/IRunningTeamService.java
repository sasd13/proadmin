package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

public interface IRunningTeamService {

	long create(RunningTeam runningTeam);

	void update(RunningTeamUpdateWrapper updateWrapper);

	void delete(RunningTeam runningTeam);

	List<RunningTeam> read(Map<String, String[]> parameters);

	List<RunningTeam> readAll();
}
