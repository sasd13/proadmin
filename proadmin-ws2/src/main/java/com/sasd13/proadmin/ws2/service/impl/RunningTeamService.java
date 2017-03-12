package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws2.db.dto.RunningTeamDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.RunningTeamDTOAdapter;
import com.sasd13.proadmin.ws2.service.IRunningTeamService;

@Service
public class RunningTeamService implements IRunningTeamService {

	@Autowired
	private IRunningTeamDAO runningTeamDAO;

	@Override
	public void create(RunningTeam runningTeam) {
		runningTeamDAO.create(runningTeam);
	}

	@Override
	public void update(IRunningTeamUpdateWrapper updateWrapper) {
		runningTeamDAO.update(updateWrapper);
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		runningTeamDAO.delete(runningTeam);
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
