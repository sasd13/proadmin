package com.sasd13.proadmin.view;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ITeamController extends IController {

    void listTeams();

    void newTeam();

    void createTeam(Team team);

    void showTeam(Team team);

    void updateTeam(Team team, Team teamToUpdate);

    void deleteTeam(Team team);

    void newStudent(Team team);

    void showStudent(Student student);
}
