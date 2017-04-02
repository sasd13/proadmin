package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.member.Team;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ITeamController extends IController {

    void listTeams();

    void newTeam();

    void createTeam(Team team);

    void showTeam(Team team);

    void updateTeam(Team team, Team teamToUpdate);

    void deleteTeams(Team[] teams);
}
