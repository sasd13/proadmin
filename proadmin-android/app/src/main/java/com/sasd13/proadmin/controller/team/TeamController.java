package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.TeamScope;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.builder.member.DefaultTeamBuilder;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.view.fragment.team.ITeamController;
import com.sasd13.proadmin.view.fragment.team.TeamDetailsFragment;
import com.sasd13.proadmin.view.fragment.team.TeamNewFragment;
import com.sasd13.proadmin.view.fragment.team.TeamsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeamController extends MainController implements ITeamController {

    private TeamScope scope;
    private ITeamService teamService;
    private IStudentService studentService;
    private TeamReadTask teamReadTask;
    private TeamCreateTask teamCreateTask;
    private TeamUpdateTask teamUpdateTask;
    private TeamDeleteTask teamDeleteTask;
    private StudentReadTask studentReadTask;
    private StudentDeleteTask studentDeleteTask;

    public TeamController(MainActivity mainActivity, ITeamService teamService, IStudentService studentService) {
        super(mainActivity);

        scope = new TeamScope();
        this.teamService = teamService;
        this.studentService = studentService;
    }

    @Override
    public Object getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        scope.setTeams(new ArrayList<Team>());
        startFragment(TeamsFragment.newInstance());
        readTeams();
    }

    private void readTeams() {
        if (teamReadTask == null) {
            teamReadTask = new TeamReadTask(this, teamService);
        }

        new Requestor(teamReadTask).execute();
    }

    void onReadTeams(List<Team> teams) {
        for (Team team : teams) {
            if (scope.getTeams().contains(team)) {
                teams.remove(team);
            } else {
                scope.getTeams().add(team);
            }
        }

        scope.setTeamsToAdd(teams);
        scope.setTeamsToAdd(Collections.<Team>emptyList());
    }

    @Override
    public void actionNewTeam() {
        scope.setTeam(new DefaultTeamBuilder().build());
        startFragment(TeamNewFragment.newInstance());
    }

    @Override
    public void actionCreateTeam(Team team) {
        if (teamCreateTask == null) {
            teamCreateTask = new TeamCreateTask(this, teamService);
        }

        new Requestor(teamCreateTask).execute(team);
    }

    void onCreateTeam() {
        display(R.string.message_saved);
        browse();
    }

    @Override
    public void actionShowTeam(Team team) {
        scope.setTeam(team);
        scope.setStudentTeams(new ArrayList<StudentTeam>());
        startFragment(TeamDetailsFragment.newInstance());
        readStudents(team);
    }

    private void readStudents(Team team) {
        if (studentReadTask == null) {
            studentReadTask = new StudentReadTask(this, studentService);
        }

        studentReadTask.clearParameters();
        studentReadTask.putParameter(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        new Requestor(studentReadTask).execute();
    }

    void onReadStudentTeams(List<StudentTeam> studentTeams) {
        scope.setStudentTeams(studentTeams);
    }

    @Override
    public void actionUpdateTeam(TeamUpdateWrapper teamUpdateWrapper) {
        if (teamUpdateTask == null) {
            teamUpdateTask = new TeamUpdateTask(this, teamService);
        }

        new Requestor(teamUpdateTask).execute(teamUpdateWrapper);
    }

    void onUpdateTeam() {
        display(R.string.message_updated);
    }

    @Override
    public void actionRemoveTeam(Team team) {
        if (teamDeleteTask == null) {
            teamDeleteTask = new TeamDeleteTask(this, teamService);
        }

        new Requestor(teamDeleteTask).execute(Arrays.asList(new Team[]{team}));
    }

    void onDeleteTeam() {
        display(R.string.message_deleted);
        browse();
    }

    @Override
    public void actionRemoveStudentTeams(StudentTeam[] studentTeams) {
        if (studentDeleteTask == null) {
            studentDeleteTask = new StudentDeleteTask(this, studentService);
        }

        new Requestor(studentDeleteTask).execute(Arrays.asList(studentTeams));
    }

    void onDeleteStudentTeams() {
        display(R.string.message_deleted);
    }
}