package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.running.RunningBean;

public interface IRunningService {

	void create(RunningBean runningBean);

	void update(RunningBean runningBean);

	void delete(RunningBean runningBean);

	List<RunningBean> read(Map<String, Object> criterias);
}
