package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IRunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

public interface IRunningTeamService {

	long create(IRunningTeam iRunningTeam);

	void update(RunningTeamUpdateWrapper updateWrapper);

	void delete(IRunningTeam iRunningTeam);

	List<IRunningTeam> read(Map<String, String[]> parameters);

	List<IRunningTeam> readAll();
}
