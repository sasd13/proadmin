package com.sasd13.proadmin.android.service.v2;

import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.service.ServiceResult;

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

    ServiceResult<Void> update(RunningTeam runningTeam);

    ServiceResult<Void> delete(List<RunningTeam> runningTeams);
}
