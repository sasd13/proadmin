package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningDAO;
import com.sasd13.proadmin.ws2.db.dto.RunningDTO;
import com.sasd13.proadmin.ws2.service.IRunningService;
import com.sasd13.proadmin.ws2.util.adapter.RunningDTOAdapter;

@Service
public class RunningService implements IRunningService {

	@Autowired
	private IRunningDAO runningDAO;

	@Override
	public void create(Running running) {
		runningDAO.create(running);
	}

	@Override
	public void update(List<RunningUpdateWrapper> updateWrappers) {
		runningDAO.update(updateWrappers);
	}

	@Override
	public void delete(List<Running> runnings) {
		runningDAO.delete(runnings);
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		List<Running> runnings = new ArrayList<>();

		List<RunningDTO> dtos = runningDAO.read(parameters);
		RunningDTOAdapter adapter = new RunningDTOAdapter();

		for (RunningDTO dto : dtos) {
			runnings.add(adapter.adapt(dto));
		}

		return runnings;
	}
}
