package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.RunningTeamDTO;

public interface IRunningTeamDAO {

	RunningTeamDTO create(RunningTeam runningTeam);

	void update(List<RunningTeamUpdateWrapper> updateWrappers);

	void delete(List<RunningTeam> runningTeams);

	List<RunningTeamDTO> read(Map<String, String[]> parameters);
}
