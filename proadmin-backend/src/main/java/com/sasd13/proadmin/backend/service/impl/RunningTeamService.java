package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.backend.dao.IRunningTeamDAO;
import com.sasd13.proadmin.backend.dao.dto.RunningTeamDTO;
import com.sasd13.proadmin.backend.service.IRunningTeamService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.RunningTeamAdapterD2B;

@Service
public class RunningTeamService implements IRunningTeamService {

	@Autowired
	private IRunningTeamDAO runningTeamDAO;

	@Override
	public void create(RunningTeam runningTeam) {
		runningTeamDAO.create(runningTeam);
	}

	@Override
	public void update(RunningTeam runningTeam) {
		runningTeamDAO.update(runningTeam);
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		runningTeamDAO.delete(runningTeam);
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) {
		List<RunningTeam> list = new ArrayList<>();

		List<RunningTeamDTO> dtos = runningTeamDAO.read(parameters);
		RunningTeamAdapterD2B adapter = new RunningTeamAdapterD2B();

		for (RunningTeamDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}
