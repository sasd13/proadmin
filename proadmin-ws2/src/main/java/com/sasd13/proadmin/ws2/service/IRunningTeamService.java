package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;

public interface IRunningTeamService {

	void create(RunningTeam runningTeam);

	void update(IRunningTeamUpdateWrapper updateWrapper);

	void delete(RunningTeam runningTeam);

	List<RunningTeam> read(Map<String, String[]> parameters);
}
