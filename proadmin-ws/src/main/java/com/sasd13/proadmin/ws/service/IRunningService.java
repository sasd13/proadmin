package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.update.RunningUpdate;

public interface IRunningService {

	long create(Running running);

	void update(RunningUpdate runningUpdate);

	void delete(Running running);

	List<Running> read(Map<String, String[]> parameters);

	List<Running> readAll();
}
