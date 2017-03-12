package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws2.db.dto.RunningTeamDTO;
import com.sasd13.proadmin.ws2.service.IRunningTeamService;
import com.sasd13.proadmin.ws2.util.adapter.RunningTeamDTOAdapter;

@Service
public class RunningTeamService implements IRunningTeamService {

	@Autowired
	private IRunningTeamDAO runningTeamDAO;

	@Override
	public void create(RunningTeam runningTeam) {
		runningTeamDAO.create(runningTeam);
	}

	@Override
	public void update(List<RunningTeamUpdateWrapper> updateWrappers) {
		runningTeamDAO.update(updateWrappers);
	}

	@Override
	public void delete(List<RunningTeam> runningTeams) {
		runningTeamDAO.delete(runningTeams);
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) {
		List<RunningTeam> runningTeams = new ArrayList<>();

		List<RunningTeamDTO> dtos = runningTeamDAO.read(parameters);
		RunningTeamDTOAdapter adapter = new RunningTeamDTOAdapter();

		for (RunningTeamDTO dto : dtos) {
			runningTeams.add(adapter.adapt(dto));
		}

		return runningTeams;
	}
}
