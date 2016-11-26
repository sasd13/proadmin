package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.androidex.ws.rest.ExecutorReadTasks;
import com.sasd13.javaex.ws.IWebService;
import com.sasd13.javaex.ws.rest.BeansWebServiceClient;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;

import java.util.List;
import java.util.Map;

public class RunningTeamRetrieveRESTCallback<T> extends RESTCallback {

    private static final String CODE_PROJECTS = "projects";
    private static final String CODE_TEAMS = "teams";
    private static final String CODE_ACADEMICLEVELS = "academiclevels";

    public interface RetrieveService extends IWebService {

        void onRetrieve(List<Project> projects, List<Team> teams, List<AcademicLevel> academicLevels);
    }

    private RetrieveService webService;
    private ExecutorReadTasks executorReadTasks;
    private BeansWebServiceClient<Project> readTaskProjects;
    private BeansWebServiceClient<Team> readTaskTeams;
    private BeansWebServiceClient<AcademicLevel> readTaskAcademicLevels;

    public RunningTeamRetrieveRESTCallback(Context context, RetrieveService webService) {
        super(context, null, webService);

        this.webService = webService;
        executorReadTasks = new ExecutorReadTasks(this);
    }

    public void read(Map<String, String[]> parametersProjects, Map<String, String[]> parametersTeams, Map<String, String[]> parametersAcademicLevels) {
        executorReadTasks.putService(CODE_PROJECTS, readTaskProjects, Project.class, parametersProjects);
        executorReadTasks.putService(CODE_TEAMS, readTaskTeams, Team.class, parametersTeams);
        executorReadTasks.putService(CODE_ACADEMICLEVELS, readTaskAcademicLevels, AcademicLevel.class, parametersAcademicLevels);

        executorReadTasks.execute();
    }

    public void readAll() {
        executorReadTasks.putService(CODE_PROJECTS, readTaskProjects, Project.class);
        executorReadTasks.putService(CODE_TEAMS, readTaskTeams, Team.class);
        executorReadTasks.putService(CODE_ACADEMICLEVELS, readTaskAcademicLevels, AcademicLevel.class);

        executorReadTasks.execute();
    }

    @Override
    public void onLoad() {
        webService.onPreExecute();
    }

    @Override
    public void onSuccess() {
        List<Project> projects = (List<Project>) executorReadTasks.getResults().get(CODE_PROJECTS);
        List<Team> teams = (List<Team>) executorReadTasks.getResults().get(CODE_TEAMS);
        List<AcademicLevel> academicLevels = (List<AcademicLevel>) executorReadTasks.getResults().get(CODE_ACADEMICLEVELS);

        webService.onRetrieve(projects, teams, academicLevels);
    }

    @Override
    public void onFail(int httpResponseCode) {
        switch (httpResponseCode) {
            case ExecutorReadTasks.TASKS_CANCELLED:
                webService.onError(context.getResources().getString(R.string.error_ws_cancelled));
                break;
            case ExecutorReadTasks.TASKS_INTERRUPTED:
                webService.onError(context.getResources().getString(R.string.error_ws_exception_interrupted));
                break;
            case ExecutorReadTasks.TASKS_NOT_TERMINATED:
                webService.onError(context.getResources().getString(R.string.error_ws_exception_execution));
                break;
            default:
                webService.onError(context.getResources().getString(R.string.error_unknown));
                break;
        }
    }
}