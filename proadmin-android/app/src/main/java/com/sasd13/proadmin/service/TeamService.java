package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeamService {

    private Promise promiseRead, promiseCreate, promiseUpdate, promiseDelete;
    private Map<String, String[]> parameters;

    public TeamService() {
        promiseRead = new Promise("GET", WSResources.URL_WS_TEAMS, Team.class);
        promiseCreate = new Promise("POST", WSResources.URL_WS_TEAMS);
        promiseUpdate = new Promise("PUT", WSResources.URL_WS_TEAMS);
        promiseDelete = new Promise("DELETE", WSResources.URL_WS_TEAMS);
        parameters = new HashMap<>();

        promiseRead.setParameters(parameters);
    }

    public void readByNumber(ICallback callback, String number) {
        parameters.clear();
        parameters.put(EnumParameter.NUMBER.getName(), new String[]{number});
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void readAll(ICallback callback) {
        parameters.clear();
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void create(ICallback callback, Team team) {
        promiseCreate.registerCallback(callback);
        promiseCreate.execute(team);
    }

    public void update(ICallback callback, Team team, Team teamToUpdate) {
        promiseUpdate.registerCallback(callback);
        promiseUpdate.execute(getUpdateWrapper(team, teamToUpdate));
    }

    private TeamUpdateWrapper getUpdateWrapper(Team team, Team teamToUpdate) {
        TeamUpdateWrapper updateWrapper = new TeamUpdateWrapper();

        updateWrapper.setWrapped(team);
        updateWrapper.setNumber(teamToUpdate.getNumber());

        return updateWrapper;
    }

    public void delete(ICallback callback, Team team) {
        promiseDelete.registerCallback(callback);
        promiseDelete.execute(team);
    }
}
