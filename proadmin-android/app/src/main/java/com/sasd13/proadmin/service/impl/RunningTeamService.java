package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;
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
    public List<RunningTeam> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_RUNNINGTEAMS, RunningTeam.class);
        }

        promiseRead.setParameters(parameters);

        return (List<RunningTeam>) promiseRead.execute();
    }

    @Override
    public Map<String, Object> retrieve(Map<String, Map<String, String[]>> allParameters) {
        if (multiPromiseRead == null) {
            multiPromiseRead = new MultiReadPromise(NBR_REQUESTS);
        }

        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(REQUEST_RUNNINGS, WSResources.URL_WS_RUNNINGS, Running.class);
        requests[0].setParameters(allParameters.get(REQUEST_RUNNINGS));

        requests[1] = new MultiReadPromise.Request(REQUEST_TEAMS, WSResources.URL_WS_TEAMS, Team.class);
        requests[1].setParameters(allParameters.get(REQUEST_TEAMS));

        requests[2] = new MultiReadPromise.Request(REQUEST_ACADEMICLEVELS, WSResources.URL_WS_ACADEMICLEVELS, AcademicLevel.class);
        requests[2].setParameters(allParameters.get(REQUEST_ACADEMICLEVELS));

        return multiPromiseRead.execute(requests);
    }

    @Override
    public void create(RunningTeam runningTeam) {
        if (promiseCreate == null) {
            promiseCreate = new Promise("POST", WSResources.URL_WS_RUNNINGTEAMS);
        }

        promiseCreate.execute(runningTeam);
    }

    @Override
    public void update(RunningTeamUpdateWrapper runningTeamUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_RUNNINGTEAMS);
        }

        promiseUpdate.execute(runningTeamUpdateWrapper);
    }

    @Override
    public void delete(RunningTeam[] runningTeams) {
        if (promiseDelete == null) {
            promiseDelete = new Promise("DELETE", WSResources.URL_WS_RUNNINGTEAMS);
        }

        promiseDelete.execute(runningTeams);
    }
}
