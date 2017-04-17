package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

public interface IRunningService {

	long create(Running running);

	void update(RunningUpdateWrapper updateWrapper);

	void delete(Running running);

	List<Running> read(Map<String, String[]> parameters);

	List<Running> readAll();
}
