package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ManagePromise;
import com.sasd13.androidex.ws.rest.promise.ReadPromise;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeamService {

    public interface Callback extends ReadPromise.Callback<Team>, ManagePromise.Callback {
    }

    private ReadPromise<Team> readService;
    private ManagePromise<Team> manageService;

    public TeamService(Callback callback) {
        readService = new ReadPromise<>(callback, WSResources.URL_WS_TEAMS, Team.class);
        manageService = new ManagePromise<>(callback, WSResources.URL_WS_TEAMS);
    }

    public void readByNumber(String number) {
        readService.clearParameters();
        readService.putParameters(EnumParameter.NUMBER.getName(), new String[]{number});
        readService.read();
    }

    public void readAll() {
        readService.read();
    }

    public void create(Team team) {
        manageService.create(team);
    }

    public void update(Team team, Team teamToUpdate) {
        manageService.update(getUpdateWrapper(team, teamToUpdate));
    }

    private TeamUpdateWrapper getUpdateWrapper(Team team, Team teamToUpdate) {
        TeamUpdateWrapper updateWrapper = new TeamUpdateWrapper();

        updateWrapper.setWrapped(team);
        updateWrapper.setNumber(teamToUpdate.getNumber());

        return updateWrapper;
    }

    public void delete(Team team) {
        manageService.delete(team);
    }
}
