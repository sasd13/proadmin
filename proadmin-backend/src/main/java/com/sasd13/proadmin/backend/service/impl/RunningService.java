package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IRunningDAO;
import com.sasd13.proadmin.backend.entity.Running;
import com.sasd13.proadmin.backend.service.IRunningService;
import com.sasd13.proadmin.backend.util.adapter.entity2itf.RunningAdapterM2I;
import com.sasd13.proadmin.backend.util.adapter.itf2entity.RunningAdapterI2M;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RunningService implements IRunningService {

	@Autowired
	private IRunningDAO runningDAO;

	@Override
	public void create(RunningBean runningBean) {
		Running running = adaptI2M(runningBean);

		runningDAO.create(running);
	}

	private Running adaptI2M(RunningBean runningBean) {
		return new RunningAdapterI2M().adapt(runningBean);
	}

	@Override
	public void update(RunningBean runningBean) {
		Running running = adaptI2M(runningBean);

		runningDAO.update(running);
	}

	@Override
	public void delete(RunningBean runningBean) {
		Running running = adaptI2M(runningBean);

		runningDAO.delete(running);
	}

	@Override
	public List<RunningBean> read(Map<String, Object> criterias) {
		List<Running> runnings = runningDAO.read(criterias);

		return adaptM2I(runnings);
	}

	private List<RunningBean> adaptM2I(List<Running> runnings) {
		List<RunningBean> list = new ArrayList<>();
		RunningAdapterM2I adapter = new RunningAdapterM2I();

		for (Running running : runnings) {
			list.add(adapter.adapt(running));
		}

		return list;
	}
}
