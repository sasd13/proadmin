package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.ws.IWebService;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunningTeamRetrieveRESTCallback<T> extends RESTCallback {

    public interface RetrieveService extends IWebService {

        void onRetrieve(List<Project> projects, List<Team> teams, List<AcademicLevel> academicLevels);
    }

    private ExecutorService executorService;
    private ReadTask<Project> readTaskProjects;
    private ReadTask<Team> readTaskTeams;
    private ReadTask<AcademicLevel> readTaskAcademicLevels;
    private List<Project> projects;
    private List<Team> teams;
    private List<AcademicLevel> academicLevels;

    public RunningTeamRetrieveRESTCallback(Context context, RetrieveService webService) {
        super(context, null, webService);

        executorService = Executors.newFixedThreadPool(3);
    }

    public void read(Map<String, String[]> parametersProjects, Map<String, String[]> parametersTeams, Map<String, String[]> parametersAcademicLevels) {
        readTaskProjects = new ReadTask<>(WSResources.URL_WS_PROJECTS, this, Project.class);
        readTaskTeams = new ReadTask<>(WSResources.URL_WS_TEAMS, this, Team.class);
        readTaskAcademicLevels = new ReadTask<>(WSResources.URL_WS_ACADEMICLEVELS, this, AcademicLevel.class);

        readTaskProjects.setParameters(parametersProjects);
        readTaskTeams.setParameters(parametersTeams);
        readTaskAcademicLevels.setParameters(parametersAcademicLevels);

        execute();
    }

    public void readAll() {
        readTaskProjects = new ReadTask<>(WSResources.URL_WS_PROJECTS, this, Project.class);
        readTaskTeams = new ReadTask<>(WSResources.URL_WS_TEAMS, this, Team.class);
        readTaskAcademicLevels = new ReadTask<>(WSResources.URL_WS_ACADEMICLEVELS, this, AcademicLevel.class);

        execute();
    }

    private void execute() {
        webService.onPreExecute();

        executeTaskProjects();
        executeTaskTeams();
        executeTaskAcademicLevels();
    }

    private void executeTaskProjects() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                readTaskProjects.execute();

                try {
                    projects = readTaskProjects.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void executeTaskTeams() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                readTaskTeams.execute();

                try {
                    teams = readTaskTeams.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void executeTaskAcademicLevels() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                readTaskAcademicLevels.execute();

                try {
                    academicLevels = readTaskAcademicLevels.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onLoad() {
        //Do nothing
    }

    @Override
    public void onSuccess() {
        if (executorService.isTerminated()) {
            ((RetrieveService) webService).onRetrieve(projects, teams, academicLevels);
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        executorService.shutdownNow();

        if (executorService.isShutdown()) {
            super.onFail(httpResponseCode);
        }
    }
}