package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningTeamService implements IRunningTeamService {

    private static final int NBR_REQUESTS = 3;

    private Promise promiseRead, promiseCreate, promiseUpdate, promiseDelete;
    private MultiReadPromise multiPromiseRead;

    @Override
    public ServiceResult<List<RunningTeam>> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_RUNNINGTEAMS, RunningTeam.class);
        }

        promiseRead.setParameters(parameters);

        List<RunningTeam> results = (List<RunningTeam>) promiseRead.execute();

        return new ServiceResult<>(
                promiseRead.isSuccess(),
                promiseRead.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters) {
        if (multiPromiseRead == null) {
            multiPromiseRead = new MultiReadPromise(NBR_REQUESTS);
        }

        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(PARAMATERS_RUNNING, WSResources.URL_WS_RUNNINGS, Running.class);
        requests[0].setParameters(allParameters.get(PARAMATERS_RUNNING));

        requests[1] = new MultiReadPromise.Request(PARAMETERS_TEAM, WSResources.URL_WS_TEAMS, Team.class);
        requests[1].setParameters(allParameters.get(PARAMETERS_TEAM));

        requests[2] = new MultiReadPromise.Request(PARAMETERS_ACADEMICLEVEL, WSResources.URL_WS_ACADEMICLEVELS, AcademicLevel.class);
        requests[2].setParameters(allParameters.get(PARAMETERS_ACADEMICLEVEL));

        Map<String, Object> results = multiPromiseRead.execute(requests);

        return new ServiceResult<>(
                multiPromiseRead.isSuccess(),
                0,
                results
        );
    }

    @Override
    public ServiceResult<Void> create(RunningTeam runningTeam) {
        if (promiseCreate == null) {
            promiseCreate = new Promise("POST", WSResources.URL_WS_RUNNINGTEAMS);
        }

        promiseCreate.execute(runningTeam);

        return new ServiceResult<>(
                promiseCreate.isSuccess(),
                promiseCreate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(RunningTeamUpdateWrapper runningTeamUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_RUNNINGTEAMS);
        }

        promiseUpdate.execute(runningTeamUpdateWrapper);

        return new ServiceResult<>(
                promiseUpdate.isSuccess(),
                promiseUpdate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(RunningTeam[] runningTeams) {
        if (promiseDelete == null) {
            promiseDelete = new Promise("DELETE", WSResources.URL_WS_RUNNINGTEAMS);
        }

        promiseDelete.execute(runningTeams);

        return new ServiceResult<>(
                promiseDelete.isSuccess(),
                promiseDelete.getResponseCode(),
                null
        );
    }
}
