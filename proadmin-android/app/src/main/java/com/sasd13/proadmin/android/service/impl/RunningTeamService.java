package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.MultiPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.model.AcademicLevel;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.itf.AcademicLevelITFAdapter;
import com.sasd13.proadmin.android.util.adapter.itf.RunningITFAdapter;
import com.sasd13.proadmin.android.util.adapter.itf.RunningTeamITFAdapter;
import com.sasd13.proadmin.android.util.adapter.itf.TeamITFAdapter;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelResponseBean;
import com.sasd13.proadmin.itf.bean.running.RunningResponseBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamRequestBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamResponseBean;
import com.sasd13.proadmin.itf.bean.team.TeamResponseBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningTeamService implements IRunningTeamService {

    private static final String URL_WS2_RUNNINGTEAMS = AppProperties.getProperty(Names.URL_WS2_RUNNINGTEAMS);
    private static final String URL_WS2_RUNNINGS = AppProperties.getProperty(Names.URL_WS2_RUNNINGS);
    private static final String URL_WS2_TEAMS = AppProperties.getProperty(Names.URL_WS2_TEAMS);
    private static final String URL_WS2_ACADEMICLEVELS = AppProperties.getProperty(Names.URL_WS2_ACADEMICLEVELS);
    private static final int NBR_REQUESTS = 3;

    private RunningTeamITFAdapter runningTeamAdapter;
    private RunningITFAdapter runningAdapter;
    private TeamITFAdapter teamAdapter;
    private AcademicLevelITFAdapter academicLevelAdapter;

    public RunningTeamService() {
        runningTeamAdapter = new RunningTeamITFAdapter();
        runningAdapter = new RunningITFAdapter();
        teamAdapter = new TeamITFAdapter();
        academicLevelAdapter = new AcademicLevelITFAdapter();
    }

    @Override
    public ServiceResult<List<RunningTeam>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", URL_WS2_RUNNINGTEAMS + "/search", RunningTeamResponseBean.class);
        SearchBean searchBean = new SearchBean();

        searchBean.setCriterias(criterias);

        RunningTeamResponseBean responseBean = (RunningTeamResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        List<RunningTeam> list = new ArrayList<>();

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                list = runningTeamAdapter.adaptI2M(responseBean.getData());
            }
        }

        return new ServiceResult<>(
                promise.isSuccess() && errors.isEmpty(),
                promise.getResponseCode(),
                errors,
                list
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, Object>> allCriterias) {
        MultiPromise.Request[] requests = new MultiPromise.Request[NBR_REQUESTS];
        requests[0] = new MultiPromise.Request(PARAMATERS_RUNNING, "POST", URL_WS2_RUNNINGS + "/search", RunningResponseBean.class);
        requests[1] = new MultiPromise.Request(PARAMETERS_TEAM, "POST", URL_WS2_TEAMS + "/search", TeamResponseBean.class);
        requests[2] = new MultiPromise.Request(PARAMETERS_ACADEMICLEVEL, "GET", URL_WS2_ACADEMICLEVELS + "/read", AcademicLevelResponseBean.class);

        SearchBean searchBeanRunnings = new SearchBean();
        searchBeanRunnings.setCriterias(allCriterias.get(PARAMATERS_RUNNING));
        requests[0].setBody(searchBeanRunnings);
        requests[1].setBody(new SearchBean());

        MultiPromise promise = new MultiPromise(NBR_REQUESTS);
        Map<String, Object> results = promise.execute(requests, 7000);

        RunningResponseBean runningResponseBean = (RunningResponseBean) results.get(PARAMATERS_RUNNING);
        TeamResponseBean teamResponseBean = (TeamResponseBean) results.get(PARAMETERS_TEAM);
        AcademicLevelResponseBean academicLevelResponseBean = (AcademicLevelResponseBean) results.get(PARAMETERS_ACADEMICLEVEL);

        List<Running> runnings = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        List<AcademicLevel> academicLevels = new ArrayList<>();

        if (promise.isSuccess()) {
            runnings = runningAdapter.adaptI2M(runningResponseBean.getData());
            teams = teamAdapter.adaptI2M(teamResponseBean.getData());
            academicLevels = academicLevelAdapter.adaptI2M(academicLevelResponseBean.getData());
        }

        results.put(PARAMATERS_RUNNING, runnings);
        results.put(PARAMETERS_TEAM, teams);
        results.put(PARAMETERS_ACADEMICLEVEL, academicLevels);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.isSuccess() ? 200 : 417,
                Collections.<String, String>emptyMap(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(RunningTeam runningTeam) {
        Promise promise = new Promise("POST", URL_WS2_RUNNINGTEAMS + "/create");
        RunningTeamRequestBean requestBean = new RunningTeamRequestBean();

        requestBean.setData(Arrays.asList(runningTeamAdapter.adaptM2I(runningTeam)));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(RunningTeam runningTeam) {
        Promise promise = new Promise("POST", URL_WS2_RUNNINGTEAMS + "/update");
        RunningTeamRequestBean requestBean = new RunningTeamRequestBean();

        requestBean.setData(Arrays.asList(runningTeamAdapter.adaptM2I(runningTeam)));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<RunningTeam> runningTeams) {
        Promise promise = new Promise("POST", URL_WS2_RUNNINGTEAMS + "/delete");
        RunningTeamRequestBean requestBean = new RunningTeamRequestBean();

        requestBean.setData(runningTeamAdapter.adaptM2I(runningTeams));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
