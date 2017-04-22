package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.backend.dao.IRunningDAO;
import com.sasd13.proadmin.backend.dao.dto.RunningDTO;
import com.sasd13.proadmin.backend.service.IRunningService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.RunningAdapterD2B;

@Service
public class RunningService implements IRunningService {

	@Autowired
	private IRunningDAO runningDAO;

	@Override
	public void create(Running running) {
		runningDAO.create(running);
	}

	@Override
	public void update(Running running) {
		runningDAO.update(running);
	}

	@Override
	public void delete(Running running) {
		runningDAO.delete(running);
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		List<Running> list = new ArrayList<>();

		List<RunningDTO> dtos = runningDAO.read(parameters);
		RunningAdapterD2B adapter = new RunningAdapterD2B();

		for (RunningDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}
