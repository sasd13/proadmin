package com.sasd13.proadmin.android.view;

import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.TeamUpdate;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ITeamController extends IController, IBrowsable {

    void actionLoadTeams();

    void actionNewTeam();

    void actionCreateTeam(Team team);

    void actionShowTeam(Team team);

    void actionUpdateTeam(TeamUpdate teamUpdate);

    void actionRemoveTeam(Team team);

    void actionRemoveStudentTeams(StudentTeam[] studentTeams);
}
