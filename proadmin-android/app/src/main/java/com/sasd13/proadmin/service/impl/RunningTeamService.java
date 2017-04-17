package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.level.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.WSResources;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningTeamService implements IRunningTeamService {

    private static final int NBR_REQUESTS = 3;

    @Override
    public ServiceResult<List<RunningTeam>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", WSResources.URL_WS_RUNNINGTEAMS, RunningTeam.class);

        promise.setParameters(parameters);

        List<RunningTeam> results = (List<RunningTeam>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                results
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters) {
        MultiReadPromise promise = new MultiReadPromise(NBR_REQUESTS);

        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(PARAMATERS_RUNNING, WSResources.URL_WS_RUNNINGS, Running.class);
        requests[0].setParameters(allParameters.get(PARAMATERS_RUNNING));

        requests[1] = new MultiReadPromise.Request(PARAMETERS_TEAM, WSResources.URL_WS_TEAMS, Team.class);
        requests[1].setParameters(allParameters.get(PARAMETERS_TEAM));

        requests[2] = new MultiReadPromise.Request(PARAMETERS_ACADEMICLEVEL, WSResources.URL_WS_ACADEMICLEVELS, AcademicLevel.class);
        requests[2].setParameters(allParameters.get(PARAMETERS_ACADEMICLEVEL));

        Map<String, Object> results = promise.execute(requests, 7000);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.isSuccess() ? 200 : 417,
                Collections.<String, List<String>>emptyMap(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(RunningTeam runningTeam) {
        Promise promise = new Promise("POST", WSResources.URL_WS_RUNNINGTEAMS);

        promise.execute(new RunningTeam[]{runningTeam});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(RunningTeamUpdateWrapper runningTeamUpdateWrapper) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_RUNNINGTEAMS);

        promise.execute(new RunningTeamUpdateWrapper[]{runningTeamUpdateWrapper});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(List<RunningTeam> runningTeams) {
        Promise promise = new Promise("DELETE", WSResources.URL_WS_RUNNINGTEAMS);

        promise.execute(runningTeams);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }
}
