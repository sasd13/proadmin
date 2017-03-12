package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningDAO;
import com.sasd13.proadmin.ws2.db.dto.RunningDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.RunningDTOAdapter;
import com.sasd13.proadmin.ws2.service.IRunningService;

@Service
public class RunningService implements IRunningService {

	@Autowired
	private IRunningDAO runningDAO;

	@Override
	public void create(Running running) {
		runningDAO.create(running);
	}

	@Override
	public void update(IRunningUpdateWrapper updateWrapper) {
		runningDAO.update(updateWrapper);
	}

	@Override
	public void delete(Running running) {
		runningDAO.delete(running);
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
