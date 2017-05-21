package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public interface IRunningTeamService {

	void create(RunningTeamBean runningTeamBean);

	void update(RunningTeamBean runningTeamBean);

	void delete(RunningTeamBean runningTeamBean);

	List<RunningTeamBean> read(Map<String, Object> criterias);
}
