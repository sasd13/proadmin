package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

public interface IRunningService {

	void create(Running running);

	void update(List<RunningUpdateWrapper> updateWrappers);

	void delete(List<Running> runnings);

	List<Running> read(Map<String, String[]> parameters);
}
