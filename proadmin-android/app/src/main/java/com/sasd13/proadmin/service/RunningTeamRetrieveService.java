package com.sasd13.proadmin.service;

import android.content.Context;

import com.sasd13.androidex.ws.rest.callback.MultiReadRESTCallback;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.ws.caller.IRunningTeamDependencyCaller;

import java.util.List;
import java.util.Map;

public class RunningTeamRetrieveService implements MultiReadRESTCallback.ReadService {

    private static final String CODE_PROJECTS = "projects";
    private static final String CODE_TEAMS = "teams";
    private static final String CODE_ACADEMICLEVELS = "academiclevels";

    private Context context;
    private IRunningTeamDependencyCaller caller;
    private MultiReadRESTCallback callback;
    private Map<String, String[]> parametersProjects, parametersTeams;

    public RunningTeamRetrieveService(Context context, IRunningTeamDependencyCaller caller) {
        this.context = context;
        this.caller = caller;
        callback = new MultiReadRESTCallback(this);
    }

    public void setParametersProjects(Map<String, String[]> parametersProjects) {
        this.parametersProjects = parametersProjects;
    }

    public void setParametersTeams(Map<String, String[]> parametersTeams) {
        this.parametersTeams = parametersTeams;
    }

    public void read() {
        callback.putRequest(CODE_PROJECTS, Project.class, WSResources.URL_WS_PROJECTS, parametersProjects);
        callback.putRequest(CODE_TEAMS, Team.class, WSResources.URL_WS_TEAMS, parametersTeams);
        callback.putRequest(CODE_ACADEMICLEVELS, AcademicLevel.class, WSResources.URL_WS_ACADEMICLEVELS);

        callback.execute();
    }

    @Override
    public void onPreExecute() {
        caller.onWaiting();
    }

    @Override
    public void onReaded() {
        List<Project> projects = (List<Project>) callback.getResults().get(CODE_PROJECTS);
        List<Team> teams = (List<Team>) callback.getResults().get(CODE_TEAMS);
        List<AcademicLevel> academicLevels = (List<AcademicLevel>) callback.getResults().get(CODE_ACADEMICLEVELS);

        caller.onRetrieved(projects, teams, academicLevels);
    }

    @Override
    public void onError(List<String> errors) {
        WebServiceUtils.handleErrors(context, caller, errors);
    }
}