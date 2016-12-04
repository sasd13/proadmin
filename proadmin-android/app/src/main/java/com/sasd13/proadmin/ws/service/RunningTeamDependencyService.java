package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.callback.MultiReadRESTCallback;
import com.sasd13.androidex.ws.rest.service.IWebServiceCaller;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.RunningTeamDependencyWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningTeamDependencyService implements MultiReadRESTCallback.ReadWebService {

    public interface RetrieveCaller extends IWebServiceCaller {

        void onRetrieved(RunningTeamDependencyWrapper dependencyWrapper);
    }

    private static final String CODE_RUNNINGS = "RUNNINGS";
    private static final String CODE_TEAMS = "TEAMS";
    private static final String CODE_ACADEMICLEVELS = "ACADEMICLEVELS";

    private RetrieveCaller caller;
    private MultiReadRESTCallback callback;
    private Map<String, String[]> parametersRunnings, parametersTeams;

    public RunningTeamDependencyService(RetrieveCaller caller) {
        this.caller = caller;
        callback = new MultiReadRESTCallback(this);
        parametersRunnings = new HashMap<>();
        parametersTeams = new HashMap<>();
    }

    public void addParameterRunnings(String parameter, String[] values) {
        parametersRunnings.put(parameter, values);
    }

    public void clearParametersRunnings() {
        parametersRunnings.clear();
    }

    public void addParameterTeams(String parameter, String[] values) {
        parametersTeams.put(parameter, values);
    }

    public void clearParametersTeams() {
        parametersTeams.clear();
    }

    public void read() {
        callback.addRequest(CODE_RUNNINGS, Running.class, WSResources.URL_WS_RUNNINGS, parametersRunnings);
        callback.addRequest(CODE_TEAMS, Team.class, WSResources.URL_WS_TEAMS, parametersTeams);
        callback.addRequest(CODE_ACADEMICLEVELS, AcademicLevel.class, WSResources.URL_WS_ACADEMICLEVELS);
        callback.read();
    }

    @Override
    public void onPreExecute() {
        caller.onWaiting();
    }

    @Override
    public void onReaded(Map<String, List> results) {
        List<Running> runnings = (List<Running>) results.get(CODE_RUNNINGS);
        List<Team> teams = (List<Team>) results.get(CODE_TEAMS);
        List<AcademicLevel> academicLevels = (List<AcademicLevel>) results.get(CODE_ACADEMICLEVELS);

        caller.onRetrieved(new RunningTeamDependencyWrapper(runnings, teams, academicLevels));
    }

    @Override
    public void onErrors(List<String> list) {
        caller.onErrors(list);
    }

    @Override
    public void onErrors(Map<String, List<String>> errors) {
        for (Map.Entry<String, List<String>> entry : errors.entrySet()) {
            caller.onErrors(entry.getValue());
            return;
        }
    }
}