package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeamService implements ITeamService {

    @Override
    public ServiceResult<List<Team>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", WSResources.URL_WS_TEAMS, Team.class);

        promise.setParameters(parameters);

        List<Team> results = (List<Team>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Team team) {
        Promise promise = new Promise("POST", WSResources.URL_WS_TEAMS);

        promise.execute(team);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(TeamUpdateWrapper teamUpdateWrapper) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_TEAMS);

        promise.execute(teamUpdateWrapper);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(Team[] teams) {
        Promise promise = new Promise("DELETE", WSResources.URL_WS_TEAMS);

        promise.execute(teams);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }
}
