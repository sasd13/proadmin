package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeamsService {

    public interface ReadCaller extends ReadService.Caller<Team> {
    }

    public interface ManageCaller extends ManageService.Caller {
    }

    private ReadService<Team> readService;
    private ManageService<Team> manageService;

    public TeamsService(ReadCaller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_TEAMS, Team.class);
    }

    public TeamsService(ManageCaller caller) {
        manageService = new ManageService<>(caller, WSResources.URL_WS_TEAMS);
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
