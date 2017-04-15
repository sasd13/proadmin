package com.sasd13.proadmin.view;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ITeamController extends IController, IBrowsable {

    void actionNewTeam();

    void actionCreateTeam(Team team);

    void actionShowTeam(Team team);

    void actionUpdateTeam(TeamUpdateWrapper teamUpdateWrapper);

    void actionRemoveTeam(Team team);

    void actionRemoveStudentTeams(StudentTeam[] studentTeams);
}
