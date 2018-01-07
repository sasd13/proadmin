package com.sasd13.proadmin.android.component.team.view;

import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.util.browser.IBrowsable;
import com.sasd13.proadmin.android.component.IController;

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
