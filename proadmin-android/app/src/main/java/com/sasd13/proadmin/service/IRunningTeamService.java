package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IRunningTeamService {

    String PARAMATERS_RUNNING = "PARAMATERS_RUNNING";
    String PARAMETERS_TEAM = "PARAMETERS_TEAM";
    String PARAMETERS_ACADEMICLEVEL = "PARAMETERS_ACADEMICLEVEL";

    ServiceResult<List<RunningTeam>> read(Map<String, String[]> parameters);

    ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters);

    ServiceResult<Void> create(RunningTeam runningTeam);

    ServiceResult<Void> update(RunningTeamUpdateWrapper runningTeamUpdateWrapper);

    ServiceResult<Void> delete(RunningTeam[] runningTeams);
}
