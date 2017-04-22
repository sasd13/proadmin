package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Running;

public interface IRunningService {

	void create(Running teacher);

	void update(Running teacher);

	void delete(Running teacher);

	List<Running> read(Map<String, String[]> parameters);
}
