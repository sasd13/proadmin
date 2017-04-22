package com.sasd13.proadmin.ws2.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws2.dao.dto.RunningDTO;

public interface IRunningDAO {

	RunningDTO create(Running running);

	void update(List<RunningUpdateWrapper> updateWrappers);

	void delete(List<Running> runnings);

	List<RunningDTO> read(Map<String, String[]> parameters);
}
