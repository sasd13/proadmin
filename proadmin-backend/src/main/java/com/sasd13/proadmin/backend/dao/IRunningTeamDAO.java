package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.dao.dto.RunningTeamDTO;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

public interface IRunningTeamDAO {

	RunningTeamDTO create(RunningTeam runningTeam);

	void update(List<RunningTeamUpdateWrapper> updateWrappers);

	void delete(List<RunningTeam> runningTeams);

	List<RunningTeamDTO> read(Map<String, String[]> parameters);
}
