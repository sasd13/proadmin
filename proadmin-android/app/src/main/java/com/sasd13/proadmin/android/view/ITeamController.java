package com.sasd13.proadmin.android.view;

import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.Team;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ITeamController extends IController, IBrowsable {

    void actionReadTeams();

    void actionNewTeam();

    void actionCreateTeam(Team team);

    void actionShowTeam(Team team);

    void actionUpdateTeam(Team team);

    void actionRemoveTeam(Team team);

    void actionRemoveStudentTeams(List<StudentTeam> studentTeams);
}
