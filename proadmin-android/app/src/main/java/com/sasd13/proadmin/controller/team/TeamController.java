package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.IStudentController;
import com.sasd13.proadmin.controller.ITeamController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.StudentWrapper;
import com.sasd13.proadmin.scope.TeamScope;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.builder.member.DefaultStudentBuilder;
import com.sasd13.proadmin.util.builder.member.DefaultTeamBuilder;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.view.fragment.student.StudentDetailsFragment;
import com.sasd13.proadmin.view.fragment.student.StudentNewFragment;
import com.sasd13.proadmin.view.fragment.team.TeamDetailsFragment;
import com.sasd13.proadmin.view.fragment.team.TeamNewFragment;
import com.sasd13.proadmin.view.fragment.team.TeamsFragment;

import java.util.List;

public class TeamController extends MainController implements ITeamController, IStudentController {

    private TeamScope scope;
    private ITeamService teamService;
    private IStudentService studentService;
    private TeamReadStrategy teamReadStrategy;
    private TeamCreateStrategy teamCreateStrategy;
    private TeamUpdateStrategy teamUpdateStrategy;
    private TeamDeleteStrategy teamDeleteStrategy;
    private StudentReadStrategy studentReadStrategy;
    private StudentCreateStrategy studentCreateStrategy;
    private StudentUpdateStrategy studentUpdateStrategy;
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
    public void entry() {
        mainActivity.clearHistory();
        listTeams();
    }

    @Override
    public void listTeams() {
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
    public void newTeam() {
        startFragment(TeamNewFragment.newInstance());

        scope.setTeam(new DefaultTeamBuilder().build());
    }

    @Override
    public void createTeam(Team team) {
        if (teamCreateStrategy == null) {
            teamCreateStrategy = new TeamCreateStrategy(this, teamService);
        }

        new Requestor(teamCreateStrategy).execute(team);
    }

    @Override
    public void showTeam(Team team) {
        startFragment(TeamDetailsFragment.newInstance());
        listStudents(team);
    }

    void onReadStudenTeams(List<StudentTeam> studentTeams) {
        scope.setStudentTeams(studentTeams);
    }

    @Override
    public void updateTeam(Team team, Team teamToUpdate) {
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

    @Override
    public void deleteTeams(Team[] teams) {
        if (teamDeleteStrategy == null) {
            teamDeleteStrategy = new TeamDeleteStrategy(this, teamService);
        }

        new Requestor(teamDeleteStrategy).execute(teams);
    }

    @Override
    public void listStudents(Team team) {
        if (studentReadStrategy == null) {
            studentReadStrategy = new StudentReadStrategy(this, studentService);
        }

        studentReadStrategy.clearParameters();
        studentReadStrategy.putParameter(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        new Requestor(studentReadStrategy).execute();
    }

    @Override
    public void newStudent(Team team) {
        StudentWrapper studentWrapper = new StudentWrapper(new DefaultStudentBuilder().build());
        studentWrapper.setTeam(team);

        startFragment(StudentNewFragment.newInstance(studentWrapper));
    }

    @Override
    public void createStudent(Student student, Team team) {
        if (studentCreateStrategy == null) {
            studentCreateStrategy = new StudentCreateStrategy(this, studentService);
        }

        new Requestor(studentCreateStrategy).execute(getStudentTeam(student, team));
    }

    private StudentTeam getStudentTeam(Student student, Team team) {
        StudentTeam studentTeam = new StudentTeam();

        studentTeam.setStudent(student);
        studentTeam.setTeam(team);

        return studentTeam;
    }

    @Override
    public void showStudent(Student student) {
        startFragment(StudentDetailsFragment.newInstance(new StudentWrapper(student)));
    }

    @Override
    public void updateStudent(Student student, Student studentToUpdate) {
        if (studentUpdateStrategy == null) {
            studentUpdateStrategy = new StudentUpdateStrategy(this, studentService);
        }

        new Requestor(studentUpdateStrategy).execute(getStudentUpdateWrapper(student, studentToUpdate));
    }

    private StudentUpdateWrapper getStudentUpdateWrapper(Student student, Student studentToUpdate) {
        StudentUpdateWrapper updateWrapper = new StudentUpdateWrapper();

        updateWrapper.setWrapped(student);
        updateWrapper.setNumber(studentToUpdate.getNumber());

        return updateWrapper;
    }

    @Override
    public void deleteStudentTeams(StudentTeam[] studentTeams) {
        if (studentDeleteStrategy == null) {
            studentDeleteStrategy = new StudentDeleteStrategy(this, studentService);
        }

        new Requestor(studentDeleteStrategy).execute(studentTeams);
    }
}