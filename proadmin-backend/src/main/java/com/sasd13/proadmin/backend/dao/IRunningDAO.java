package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.backend.dao.dto.RunningDTO;

public interface IRunningDAO {

	RunningDTO create(Running teacher);

	void update(Running teacher);

	void delete(Running teacher);

	List<RunningDTO> read(Map<String, String[]> parameters);
}
