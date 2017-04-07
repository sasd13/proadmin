package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.IBrowsable;
import com.sasd13.proadmin.controller.IStudentController;
import com.sasd13.proadmin.controller.ITeamController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.TeamScope;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.builder.member.DefaultTeamBuilder;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.view.fragment.team.TeamDetailsFragment;
import com.sasd13.proadmin.view.fragment.team.TeamNewFragment;
import com.sasd13.proadmin.view.fragment.team.TeamsFragment;

import java.util.List;

public class TeamController extends MainController implements ITeamController, IBrowsable {

    private TeamScope scope;
    private ITeamService teamService;
    private IStudentService studentService;
    private TeamReadStrategy teamReadStrategy;
    private TeamCreateStrategy teamCreateStrategy;
    private TeamUpdateStrategy teamUpdateStrategy;
    private TeamDeleteStrategy teamDeleteStrategy;
    private StudentReadStrategy studentReadStrategy;
    private StudentDeleteStrategy studentDeleteStrategy;

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
        listTeams();
    }

    private void listTeams() {
        startFragment(TeamsFragment.newInstance());
        readTeams();
    }

    private void readTeams() {
        if (teamReadStrategy == null) {
            teamReadStrategy = new TeamReadStrategy(this, teamService);
        }

        new Requestor(teamReadStrategy).execute();
    }

    void onReadTeams(List<Team> teams) {
        scope.setTeams(teams);
    }

    @Override
    public void actionRemoveTeam(Team team) {
        if (teamDeleteStrategy == null) {
            teamDeleteStrategy = new TeamDeleteStrategy(this, teamService);
        }

        new Requestor(teamDeleteStrategy).execute(new Team[]{team});
    }

    void onDeleteTeam() {
        display(R.string.message_deleted);
        //TODO entry();
    }

    @Override
    public void actionNewTeam() {
        scope.setTeam(new DefaultTeamBuilder().build());
        startFragment(TeamNewFragment.newInstance());
    }

    @Override
    public void actionCreateTeam(Team team) {
        if (teamCreateStrategy == null) {
            teamCreateStrategy = new TeamCreateStrategy(this, teamService);
        }

        new Requestor(teamCreateStrategy).execute(team);
    }

    void onCreateTeam() {
        display(R.string.message_saved);
        //TODO entry();
    }

    @Override
    public void actionShowTeam(Team team) {
        startFragment(TeamDetailsFragment.newInstance());
        listStudents(team);
    }

    private void listStudents(Team team) {
        if (studentReadStrategy == null) {
            studentReadStrategy = new StudentReadStrategy(this, studentService);
        }

        studentReadStrategy.clearParameters();
        studentReadStrategy.putParameter(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        new Requestor(studentReadStrategy).execute();
    }

    void onReadStudentTeams(List<StudentTeam> studentTeams) {
        scope.setStudentTeams(studentTeams);
    }

    @Override
    public void actionUpdateTeam(Team team, Team teamToUpdate) {
        if (teamUpdateStrategy == null) {
            teamUpdateStrategy = new TeamUpdateStrategy(this, teamService);
        }

        new Requestor(teamUpdateStrategy).execute(getTeamUpdateWrapper(team, teamToUpdate));
    }

    private TeamUpdateWrapper getTeamUpdateWrapper(Team team, Team teamToUpdate) {
        TeamUpdateWrapper updateWrapper = new TeamUpdateWrapper();

        updateWrapper.setWrapped(team);
        updateWrapper.setNumber(teamToUpdate.getNumber());

        return updateWrapper;
    }

    void onUpdateTeam() {
        display(R.string.message_updated);
    }

    @Override
    public void actionRemoveStudentTeams(StudentTeam[] studentTeams) {
        if (studentDeleteStrategy == null) {
            studentDeleteStrategy = new StudentDeleteStrategy(this, studentService);
        }

        new Requestor(studentDeleteStrategy).execute(studentTeams);
    }

    void onDeleteStudentTeams() {
        display(R.string.message_deleted);
        //TODO entry();
    }

    @Override
    public void actionNewStudentTeam(Team team) {
        ((IStudentController) mainActivity.lookup(IStudentController.class)).newStudent(team);
    }

    @Override
    public void actionShowStudentTeam(StudentTeam studentTeam) {
        ((IStudentController) mainActivity.lookup(IStudentController.class)).showStudent(studentTeam);
    }
}