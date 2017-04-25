package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.TeamUpdate;
import com.sasd13.proadmin.android.service.ITeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeamService implements ITeamService {

    @Override
    public ServiceResult<List<Team>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_WS_TEAMS, Team.class);

        promise.setParameters(parameters);

        List<Team> results = (List<Team>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Team team) {
        Promise promise = new Promise("POST", Resources.URL_WS_TEAMS);

        promise.execute(new Team[]{team});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(TeamUpdate teamUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_WS_TEAMS);

        promise.execute(new TeamUpdate[]{teamUpdate});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Team> teams) {
        Promise promise = new Promise("DELETE", Resources.URL_WS_TEAMS);

        promise.execute(teams);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }
}
