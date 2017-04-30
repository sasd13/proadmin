package com.sasd13.proadmin.android.service.v1.impl;

import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.RunningTeamUpdate;
import com.sasd13.proadmin.android.service.v1.IRunningTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.v1.RunningTeamAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.bean2itf.v1.update.RunningTeamUpdateAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v1.AcademicLevelAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v1.RunningAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v1.RunningTeamAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v1.TeamAdapterI2B;
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
    public ServiceResult<List<RunningTeam>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_WS_RUNNINGTEAMS, RunningTeamResponseBean.class);

        promise.setParameters(parameters);

        RunningTeamResponseBean responseBean = (RunningTeamResponseBean) promise.execute();
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
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters) {
        MultiReadPromise promise = new MultiReadPromise(NBR_REQUESTS);

        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(PARAMATERS_RUNNING, Resources.URL_WS_RUNNINGS, RunningResponseBean.class);
        requests[0].setParameters(allParameters.get(PARAMATERS_RUNNING));

        requests[1] = new MultiReadPromise.Request(PARAMETERS_TEAM, Resources.URL_WS_TEAMS, TeamResponseBean.class);
        requests[1].setParameters(allParameters.get(PARAMETERS_TEAM));

        requests[2] = new MultiReadPromise.Request(PARAMETERS_ACADEMICLEVEL, Resources.URL_WS_ACADEMICLEVELS, AcademicLevelResponseBean.class);
        requests[2].setParameters(allParameters.get(PARAMETERS_ACADEMICLEVEL));

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
        Promise promise = new Promise("POST", Resources.URL_WS_RUNNINGTEAMS);

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
    public ServiceResult<Void> update(RunningTeamUpdate runningTeamUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_WS_RUNNINGTEAMS);

        RunningTeamRequestBean requestBean = new RunningTeamRequestBean();
        List<RunningTeamBean> list = new ArrayList<>();

        list.add(new RunningTeamUpdateAdapterB2I().adapt(runningTeamUpdate));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<RunningTeam> runningTeams) {
        Promise promise = new Promise("DELETE", Resources.URL_WS_RUNNINGTEAMS);

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
