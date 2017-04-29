package com.sasd13.proadmin.android.controller.team;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.TeamUpdate;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.scope.TeamScope;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ITeamService;
import com.sasd13.proadmin.android.util.builder.member.NewTeamBuilder;
import com.sasd13.proadmin.android.view.ITeamController;
import com.sasd13.proadmin.android.view.fragment.team.TeamDetailsFragment;
import com.sasd13.proadmin.android.view.fragment.team.TeamNewFragment;
import com.sasd13.proadmin.android.view.fragment.team.TeamsFragment;
import com.sasd13.proadmin.util.EnumParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TeamController extends MainController implements ITeamController {

    private TeamScope scope;
    private ITeamService teamService;
    private IStudentTeamService studentTeamService;
    private TeamReadTask teamReadTask;
    private TeamCreateTask teamCreateTask;
    private TeamUpdateTask teamUpdateTask;
    private TeamDeleteTask teamDeleteTask;
    private StudentReadTask studentReadTask;
    private StudentDeleteTask studentDeleteTask;

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
        mainActivity.clearHistory();
        startFragment(TeamsFragment.newInstance());
        actionLoadTeams();
    }

    @Override
    public void actionLoadTeams() {
        readTeams();
    }

    private void readTeams() {
        scope.setLoading(true);

        if (teamReadTask == null) {
            teamReadTask = new TeamReadTask(this, teamService);
        }

        new Requestor(teamReadTask).execute();
    }

    void onReadTeams(List<Team> teams) {
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
            studentReadTask = new StudentReadTask(this, studentTeamService);
        }

        studentReadTask.clearParameters();
        studentReadTask.putParameter(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        new Requestor(studentReadTask).execute();
    }

    void onReadStudentTeams(List<StudentTeam> studentTeams) {
        scope.setStudentTeams(studentTeams);
    }

    @Override
    public void actionUpdateTeam(TeamUpdate teamUpdate) {
        if (teamUpdateTask == null) {
            teamUpdateTask = new TeamUpdateTask(this, teamService);
        }

        new Requestor(teamUpdateTask).execute(teamUpdate);
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
            studentDeleteTask = new StudentDeleteTask(this, studentTeamService);
        }

        new Requestor(studentDeleteTask).execute(Arrays.asList(studentTeams));
    }

    void onDeleteStudentTeams() {
        display(R.string.message_deleted);
    }
}