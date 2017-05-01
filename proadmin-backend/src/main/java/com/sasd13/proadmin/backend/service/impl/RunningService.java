package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IRunningDAO;
import com.sasd13.proadmin.backend.model.Running;
import com.sasd13.proadmin.backend.service.IRunningService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
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
	public List<Running> read(Map<String, Object> criterias) {
		return runningDAO.read(criterias);
	}
}
