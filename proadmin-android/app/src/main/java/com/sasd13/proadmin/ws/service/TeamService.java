package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.IWebServiceCaller;
import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeamService {

    public interface Caller extends ReadService.Caller<Team>, ManageService.Caller {
    }

    private ReadService<Team> readService;
    private ManageService<Team> manageService;

    public TeamService(Caller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_TEAMS, Team.class);
        manageService = new ManageService<>(caller, WSResources.URL_WS_TEAMS);
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
