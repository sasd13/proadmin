package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.MultiReadPromise;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

public class RunningTeamDependencyService {

    public static final String CODE_RUNNINGS = "RUNNINGS";
    public static final String CODE_TEAMS = "TEAMS";
    public static final String CODE_ACADEMICLEVELS = "ACADEMICLEVELS";

    private MultiReadPromise promise;
    private Map<String, String[]> parametersRunnings;

    public RunningTeamDependencyService(MultiReadPromise.Callback callback) {
        promise = new MultiReadPromise(callback);
        parametersRunnings = new HashMap<>();
    }

    public void addParameterRunnings(String parameter, String[] values) {
        parametersRunnings.put(parameter, values);
    }

    public void clearParametersRunnings() {
        parametersRunnings.clear();
    }

    public void read() {
        promise.clearRequests();
        promise.addRequest(CODE_RUNNINGS, Running.class, WSResources.URL_WS_RUNNINGS, parametersRunnings);
        promise.addRequest(CODE_TEAMS, Team.class, WSResources.URL_WS_TEAMS);
        promise.addRequest(CODE_ACADEMICLEVELS, AcademicLevel.class, WSResources.URL_WS_ACADEMICLEVELS);
        promise.read();
    }
}