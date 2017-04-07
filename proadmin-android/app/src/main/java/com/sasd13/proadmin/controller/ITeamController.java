package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ITeamController extends IController {

    void actionNewTeam();

    void actionCreateTeam(Team team);

    void actionShowTeam(Team team);

    void actionUpdateTeam(Team team, Team teamToUpdate);

    void actionRemoveTeam(Team team);

    void actionRemoveStudentTeams(StudentTeam[] studentTeams);

    void actionNewStudentTeam(Team team);

    void actionShowStudentTeam(StudentTeam studentTeam);
}
