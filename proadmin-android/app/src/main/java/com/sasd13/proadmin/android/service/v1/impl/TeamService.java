package com.sasd13.proadmin.android.service.v1.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.TeamUpdate;
import com.sasd13.proadmin.android.service.v1.ITeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.v1.TeamAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.bean2itf.v1.update.TeamUpdateAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v1.TeamAdapterI2B;
import com.sasd13.proadmin.itf.bean.team.TeamBean;
import com.sasd13.proadmin.itf.bean.team.TeamRequestBean;
import com.sasd13.proadmin.itf.bean.team.TeamResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeamService implements ITeamService {

    @Override
    public ServiceResult<List<Team>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_WS_TEAMS, TeamResponseBean.class);

        promise.setParameters(parameters);

        TeamResponseBean responseBean = (TeamResponseBean) promise.execute();
        List<Team> list = new ArrayList<>();

        if (promise.isSuccess()) {
            TeamAdapterI2B adapter = new TeamAdapterI2B();

            for (TeamBean teamBean : responseBean.getData()) {
                list.add(adapter.adapt(teamBean));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                promise.isSuccess() ? list : Collections.<Team>emptyList()
        );
    }

    @Override
    public ServiceResult<Void> create(Team team) {
        Promise promise = new Promise("POST", Resources.URL_WS_TEAMS);

        TeamRequestBean requestBean = new TeamRequestBean();
        List<TeamBean> list = new ArrayList<>();

        list.add(new TeamAdapterB2I().adapt(team));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(TeamUpdate teamUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_WS_TEAMS);

        TeamRequestBean requestBean = new TeamRequestBean();
        List<TeamBean> list = new ArrayList<>();

        list.add(new TeamUpdateAdapterB2I().adapt(teamUpdate));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Team> teams) {
        Promise promise = new Promise("DELETE", Resources.URL_WS_TEAMS);

        TeamRequestBean requestBean = new TeamRequestBean();
        List<TeamBean> list = new ArrayList<>();
        TeamAdapterB2I adapter = new TeamAdapterB2I();

        for (Team team : teams) {
            list.add(adapter.adapt(team));
        }

        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
