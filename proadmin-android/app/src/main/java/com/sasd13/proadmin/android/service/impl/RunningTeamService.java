package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.MultiPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.RunningTeamAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.AcademicLevelAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.RunningAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.RunningTeamAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.TeamAdapterI2B;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelResponseBean;
import com.sasd13.proadmin.itf.bean.running.RunningBean;
import com.sasd13.proadmin.itf.bean.running.RunningResponseBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamRequestBean;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamResponseBean;
import com.sasd13.proadmin.itf.bean.team.TeamBean;
import com.sasd13.proadmin.itf.bean.team.TeamResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningTeamService implements IRunningTeamService {

    private static final int NBR_REQUESTS = 3;

    @Override
    public ServiceResult<List<RunningTeam>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_RUNNINGTEAMS + "/search", RunningTeamResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(criterias);

        RunningTeamResponseBean responseBean = (RunningTeamResponseBean) promise.execute(searchBean);
        List<RunningTeam> list = new ArrayList<>();

        if (promise.isSuccess()) {
            RunningTeamAdapterI2B adapter = new RunningTeamAdapterI2B();

            for (RunningTeamBean runningTeamBean : responseBean.getData()) {
                list.add(adapter.adapt(runningTeamBean));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                promise.isSuccess() ? list : Collections.<RunningTeam>emptyList()
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, Object>> allCriterias) {
        MultiPromise promise = new MultiPromise(NBR_REQUESTS);

        MultiPromise.Request[] requests = new MultiPromise.Request[NBR_REQUESTS];
        requests[0] = new MultiPromise.Request(PARAMATERS_RUNNING, "POST", Resources.URL_BACKEND_RUNNINGS + "/search", RunningResponseBean.class);
        requests[1] = new MultiPromise.Request(PARAMETERS_TEAM, "POST", Resources.URL_BACKEND_TEAMS + "/search", TeamResponseBean.class);
        requests[2] = new MultiPromise.Request(PARAMETERS_ACADEMICLEVEL, "GET", Resources.URL_BACKEND_ACADEMICLEVELS + "/read", AcademicLevelResponseBean.class);

        SearchBean searchBeanRunnings = new SearchBean();
        searchBeanRunnings.setCriterias(allCriterias.get(PARAMATERS_RUNNING));
        requests[0].setBody(searchBeanRunnings);
        requests[1].setBody(new SearchBean());

        Map<String, Object> results = promise.execute(requests, 7000);

        RunningResponseBean runningResponseBean = (RunningResponseBean) results.get(PARAMATERS_RUNNING);
        TeamResponseBean teamResponseBean = (TeamResponseBean) results.get(PARAMETERS_TEAM);
        AcademicLevelResponseBean academicLevelResponseBean = (AcademicLevelResponseBean) results.get(PARAMETERS_ACADEMICLEVEL);

        List<Running> runnings = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        List<AcademicLevel> academicLevels = new ArrayList<>();

        if (promise.isSuccess()) {
            RunningAdapterI2B runningAdapter = new RunningAdapterI2B();
            TeamAdapterI2B teamAdapter = new TeamAdapterI2B();
            AcademicLevelAdapterI2B academicLevelAdapter = new AcademicLevelAdapterI2B();

            for (RunningBean runningBean : runningResponseBean.getData()) {
                runnings.add(runningAdapter.adapt(runningBean));
            }

            for (TeamBean teamBean : teamResponseBean.getData()) {
                teams.add(teamAdapter.adapt(teamBean));
            }

            for (AcademicLevelBean academicLevelBean : academicLevelResponseBean.getData()) {
                academicLevels.add(academicLevelAdapter.adapt(academicLevelBean));
            }
        }

        results.put(PARAMATERS_RUNNING, runnings);
        results.put(PARAMETERS_TEAM, teams);
        results.put(PARAMETERS_ACADEMICLEVEL, academicLevels);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.isSuccess() ? 200 : 417,
                Collections.<String, String>emptyMap(),
                promise.isSuccess() ? results : Collections.<String, Object>emptyMap()
        );
    }

    @Override
    public ServiceResult<Void> create(RunningTeam runningTeam) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_RUNNINGTEAMS + "/create");

        RunningTeamRequestBean requestBean = new RunningTeamRequestBean();
        List<RunningTeamBean> list = new ArrayList<>();

        list.add(new RunningTeamAdapterB2I().adapt(runningTeam));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(RunningTeam runningTeam) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_RUNNINGTEAMS + "/update");

        RunningTeamRequestBean requestBean = new RunningTeamRequestBean();
        List<RunningTeamBean> list = new ArrayList<>();

        list.add(new RunningTeamAdapterB2I().adapt(runningTeam));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<RunningTeam> runningTeams) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_RUNNINGTEAMS + "/delete");

        RunningTeamRequestBean requestBean = new RunningTeamRequestBean();
        List<RunningTeamBean> list = new ArrayList<>();
        RunningTeamAdapterB2I adapter = new RunningTeamAdapterB2I();

        for (RunningTeam runningTeam : runningTeams) {
            list.add(adapter.adapt(runningTeam));
        }

        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
