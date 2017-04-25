package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.WSResources;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdate;

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
                promise.getResponseHeaders(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Team team) {
        Promise promise = new Promise("POST", WSResources.URL_WS_TEAMS);

        promise.execute(new Team[]{team});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(TeamUpdate teamUpdate) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_TEAMS);

        promise.execute(new TeamUpdate[]{teamUpdate});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Team> teams) {
        Promise promise = new Promise("DELETE", WSResources.URL_WS_TEAMS);

        promise.execute(teams);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }
}
