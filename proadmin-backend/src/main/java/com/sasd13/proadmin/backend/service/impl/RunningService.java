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
import com.sasd13.proadmin.backend.util.adapter.itf.RunningITFAdapter;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RunningService implements IRunningService {

	@Autowired
	private IRunningDAO runningDAO;

	private RunningITFAdapter adapter;

	public RunningService() {
		adapter = new RunningITFAdapter();
	}

	@Override
	public void create(RunningBean runningBean) {
		Running running = adapter.adaptI2M(runningBean);

		runningDAO.create(running);
	}

	@Override
	public void update(RunningBean runningBean) {
		Running running = adapter.adaptI2M(runningBean);

		runningDAO.update(running);
	}

	@Override
	public void delete(RunningBean runningBean) {
		Running running = adapter.adaptI2M(runningBean);

		runningDAO.delete(running);
	}

	@Override
	public List<RunningBean> read(Map<String, Object> criterias) {
		List<Running> runnings = runningDAO.read(criterias);

		return adapter.adaptM2I(runnings);
	}
}
