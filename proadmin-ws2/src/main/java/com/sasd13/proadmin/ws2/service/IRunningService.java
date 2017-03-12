package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;

public interface IRunningService {

	void create(Running running);

	void update(IRunningUpdateWrapper updateWrapper);

	void delete(Running running);

	List<Running> read(Map<String, String[]> parameters);
}
