package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

public interface IRunningService {

	long create(IRunning iRunning);

	void update(RunningUpdateWrapper updateWrapper);

	void delete(IRunning iRunning);

	List<IRunning> read(Map<String, String[]> parameters);

	List<IRunning> readAll();
}
