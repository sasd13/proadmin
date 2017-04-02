package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IRunningTeamService {

    String REQUEST_RUNNINGS = "RUNNINGS";
    String REQUEST_TEAMS = "TEAMS";
    String REQUEST_ACADEMICLEVELS = "ACADEMICLEVELS";

    List<RunningTeam> read(Map<String, String[]> parameters);

    Map<String, Object> retrieve(Map<String, Map<String, String[]>> allParameters);

    void create(RunningTeam runningTeam);

    void update(RunningTeamUpdateWrapper runningTeamUpdateWrapper);

    void delete(RunningTeam[] runningTeams);
}
