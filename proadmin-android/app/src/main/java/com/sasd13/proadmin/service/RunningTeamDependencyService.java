package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

public class RunningTeamDependencyService {

    private static final int NBR_REQUESTS = 3;
    public static final String CODE_RUNNINGS = "RUNNINGS";
    public static final String CODE_TEAMS = "TEAMS";
    public static final String CODE_ACADEMICLEVELS = "ACADEMICLEVELS";

    private MultiReadPromise promiseRead;
    private Map<String, Map<String, String[]>> allParameters;

    public RunningTeamDependencyService() {
        promiseRead = new MultiReadPromise(NBR_REQUESTS);
        allParameters = new HashMap<>();

        resetParameters();
    }

    public void addParameter(String code, String key, String[] values) {
        allParameters.get(code).put(key, values);
    }

    public void resetParameters() {
        allParameters.clear();
        allParameters.put(CODE_RUNNINGS, new HashMap<String, String[]>());
        allParameters.put(CODE_TEAMS, new HashMap<String, String[]>());
        allParameters.put(CODE_ACADEMICLEVELS, new HashMap<String, String[]>());
    }

    public void read(ICallback callback) {
        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(CODE_RUNNINGS, WSResources.URL_WS_RUNNINGS, Running.class);
        requests[0].setParameters(allParameters.get(CODE_RUNNINGS));

        requests[1] = new MultiReadPromise.Request(CODE_TEAMS, WSResources.URL_WS_TEAMS, Team.class);
        requests[1].setParameters(allParameters.get(CODE_TEAMS));

        requests[2] = new MultiReadPromise.Request(CODE_ACADEMICLEVELS, WSResources.URL_WS_ACADEMICLEVELS, AcademicLevel.class);
        requests[2].setParameters(allParameters.get(CODE_ACADEMICLEVELS));

        promiseRead.registerCallback(callback);
        promiseRead.execute(requests);
    }
}