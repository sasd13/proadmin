package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.RunningDTO;

public interface IRunningDAO {

	RunningDTO create(Running running);

	void update(IRunningUpdateWrapper updateWrapper);

	void delete(Running running);

	List<RunningDTO> read(Map<String, String[]> parameters);
}
