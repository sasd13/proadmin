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

    private Promise promiseRead, promiseCreate, promiseUpdate, promiseDelete;

    @Override
    public ServiceResult<List<Team>> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_TEAMS, Team.class);
        }

        promiseRead.setParameters(parameters);

        List<Team> results = (List<Team>) promiseRead.execute();

        return new ServiceResult<>(
                promiseRead.isSuccess(),
                promiseRead.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Team team) {
        if (promiseCreate == null) {
            promiseCreate = new Promise("POST", WSResources.URL_WS_TEAMS);
        }

        promiseCreate.execute(team);

        return new ServiceResult<>(
                promiseCreate.isSuccess(),
                promiseCreate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(TeamUpdateWrapper teamUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_TEAMS);
        }

        promiseUpdate.execute(teamUpdateWrapper);

        return new ServiceResult<>(
                promiseUpdate.isSuccess(),
                promiseUpdate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(Team[] teams) {
        if (promiseDelete == null) {
            promiseDelete = new Promise("DELETE", WSResources.URL_WS_TEAMS);
        }

        promiseDelete.execute(teams);

        return new ServiceResult<>(
                promiseDelete.isSuccess(),
                promiseDelete.getResponseCode(),
                null
        );
    }
}
