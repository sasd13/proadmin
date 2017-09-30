package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.service.ITeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.bean2itf.TeamAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.TeamAdapterI2B;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.team.TeamBean;
import com.sasd13.proadmin.itf.bean.team.TeamRequestBean;
import com.sasd13.proadmin.itf.bean.team.TeamResponseBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeamService implements ITeamService {

    private static final String URL_WS2_TEAMS = AppProperties.getProperty(Names.URL_WS2_TEAMS);

    @Override
    public ServiceResult<List<Team>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", URL_WS2_TEAMS + "/search", TeamResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(criterias);

        TeamResponseBean responseBean = (TeamResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        List<Team> list = new ArrayList<>();

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                TeamAdapterI2B adapter = new TeamAdapterI2B();

                for (TeamBean teamBean : responseBean.getData()) {
                    list.add(adapter.adapt(teamBean));
                }
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
    public ServiceResult<Void> create(Team team) {
        Promise promise = new Promise("POST", URL_WS2_TEAMS + "/create");

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
    public ServiceResult<Void> update(Team team) {
        Promise promise = new Promise("POST", URL_WS2_TEAMS + "/update");

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
    public ServiceResult<Void> delete(List<Team> teams) {
        Promise promise = new Promise("POST", URL_WS2_TEAMS + "/delete");

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
