package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.model.Running;

public interface IRunningDAO {

	Running create(Running running);

	void update(Running running);

	void delete(Running running);

	List<Running> read(Map<String, String[]> parameters);
}
