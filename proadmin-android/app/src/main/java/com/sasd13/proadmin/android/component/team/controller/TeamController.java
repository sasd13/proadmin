package com.sasd13.proadmin.android.component.team.controller;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.component.MainController;
import com.sasd13.proadmin.android.component.Scope;
import com.sasd13.proadmin.android.component.team.scope.TeamScope;
import com.sasd13.proadmin.android.component.team.task.StudentTeamDeleteTask;
import com.sasd13.proadmin.android.component.team.task.StudentTeamReadTask;
import com.sasd13.proadmin.android.component.team.task.TeamCreateTask;
import com.sasd13.proadmin.android.component.team.task.TeamDeleteTask;
import com.sasd13.proadmin.android.component.team.task.TeamReadTask;
import com.sasd13.proadmin.android.component.team.task.TeamUpdateTask;
import com.sasd13.proadmin.android.component.team.view.ITeamController;
import com.sasd13.proadmin.android.component.team.view.TeamDetailsFragment;
import com.sasd13.proadmin.android.component.team.view.TeamNewFragment;
import com.sasd13.proadmin.android.component.team.view.TeamsFragment;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ITeamService;
import com.sasd13.proadmin.android.util.builder.NewTeamBuilder;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.util.EnumRestriction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TeamController extends MainController implements ITeamController {

    private TeamScope scope;
    private ITeamService teamService;
    private IStudentTeamService studentTeamService;
    private TeamReadTask teamReadTask;
    private TeamCreateTask teamCreateTask;
    private TeamUpdateTask teamUpdateTask;
    private TeamDeleteTask teamDeleteTask;
    private StudentTeamReadTask studentTeamReadTask;
    private StudentTeamDeleteTask studentTeamDeleteTask;
    private Map<String, String[]> studentTeamCriterias;

    public TeamController(MainActivity mainActivity, ITeamService teamService, IStudentTeamService studentTeamService) {
        super(mainActivity);

        scope = new TeamScope();
        this.teamService = teamService;
        this.studentTeamService = studentTeamService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        getActivity().clearHistory();
        startFragment(TeamsFragment.newInstance());
        actionReadTeams();
    }

    @Override
    public void actionReadTeams() {
        readTeams();
    }

    private void readTeams() {
        scope.setLoading(true);

        if (teamReadTask == null) {
            teamReadTask = new TeamReadTask(this, teamService);
        }

        new Requestor(teamReadTask).execute();
    }

    public void onReadTeams(List<Team> teams) {
        int index;
        Team team;

        for (Iterator<Team> it = teams.iterator(); it.hasNext(); ) {
            team = it.next();

            if ((index = scope.getTeams().indexOf(team)) >= 0) {
                it.remove();
                scope.getTeams().set(index, team);
            } else {
                scope.getTeams().add(team);
            }
        }

        scope.setTeamsToAdd(teams);

        if (!teams.isEmpty()) {
            scope.clearTeamsToAdd();
        }

        scope.setLoading(false);
    }

    @Override
    public void actionNewTeam() {
        scope.setTeam(new NewTeamBuilder().build());
        startFragment(TeamNewFragment.newInstance());
    }

    @Override
    public void actionCreateTeam(Team team) {
        if (teamCreateTask == null) {
            teamCreateTask = new TeamCreateTask(this, teamService);
        }

        new Requestor(teamCreateTask).execute(team);
    }

    public void onCreateTeam() {
        display(R.string.message_saved);
        browse();
    }

    @Override
    public void actionShowTeam(Team team) {
        scope.setTeam(team);
        scope.setStudentTeams(new ArrayList<StudentTeam>());
        startFragment(TeamDetailsFragment.newInstance());
        readStudentTeams(team);
    }

    private void readStudentTeams(Team team) {
        if (studentTeamReadTask == null) {
            studentTeamReadTask = new StudentTeamReadTask(this, studentTeamService);
            studentTeamCriterias = new HashMap<>();
        } else {
            studentTeamCriterias.clear();
        }

        Map<String, Object> allCriterias = new HashMap<>();

        studentTeamCriterias.put(EnumCriteria.TEAM.getCode(), new String[]{team.getNumber()});
        allCriterias.put(EnumRestriction.WHERE.getCode(), studentTeamCriterias);

        new Requestor(studentTeamReadTask).execute(allCriterias);
    }

    public void onReadStudentTeams(List<StudentTeam> studentTeams) {
        scope.setStudentTeams(studentTeams);
    }

    @Override
    public void actionUpdateTeam(Team team) {
        if (teamUpdateTask == null) {
            teamUpdateTask = new TeamUpdateTask(this, teamService);
        }

        new Requestor(teamUpdateTask).execute(team);
    }

    public void onUpdateTeam() {
        display(R.string.message_saved);
    }

    @Override
    public void actionRemoveTeam(Team team) {
        if (teamDeleteTask == null) {
            teamDeleteTask = new TeamDeleteTask(this, teamService);
        }

        new Requestor(teamDeleteTask).execute(Arrays.asList(new Team[]{team}));
    }

    public void onDeleteTeam() {
        display(R.string.message_deleted);
        browse();
    }

    @Override
    public void actionRemoveStudentTeams(List<StudentTeam> studentTeams) {
        if (studentTeamDeleteTask == null) {
            studentTeamDeleteTask = new StudentTeamDeleteTask(this, studentTeamService);
        }

        new Requestor(studentTeamDeleteTask).execute(studentTeams);
    }

    public void onDeleteStudentTeams() {
        display(R.string.message_deleted);
    }
}