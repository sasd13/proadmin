package com.sasd13.proadmin.controller.team;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.wrapper.TeamDependencyWrapper;
import com.sasd13.proadmin.view.IStudentController;
import com.sasd13.proadmin.view.ITeamController;
import com.sasd13.proadmin.view.student.StudentDetailsFragment;
import com.sasd13.proadmin.view.student.StudentNewFragment;
import com.sasd13.proadmin.view.team.TeamDetailsFragment;
import com.sasd13.proadmin.view.team.TeamNewFragment;
import com.sasd13.proadmin.view.team.TeamsFragment;
import com.sasd13.proadmin.ws.service.StudentService;
import com.sasd13.proadmin.ws.service.TeamService;

import java.util.List;

public class TeamController extends Controller implements ITeamController, IStudentController {

    private TeamService teamService;
    private StudentService studentService;
    private Team team;
    private TeamDependencyWrapper dependencyWrapper;

    public TeamController(MainActivity mainActivity) {
        super(mainActivity);

        teamService = new TeamService(new TeamServiceCaller(this, mainActivity));
        studentService = new StudentService(new StudentServiceCaller(this, mainActivity));
        dependencyWrapper = new TeamDependencyWrapper();
    }

    @Override
    public void entry() {
        listTeams();
    }

    @Override
    public void listTeams() {
        startProxyFragment();
        teamService.readAll();
    }

    public void onReadTeams(List<Team> teams) {
        if (isProxyFragmentNotDetached()) {
            startFragment(TeamsFragment.newInstance(this, teams));
        }
    }

    @Override
    public void newTeam() {
        startFragment(TeamNewFragment.newInstance(this));
    }

    @Override
    public void createTeam(Team team) {
        teamService.create(team);
    }

    @Override
    public void showTeam(Team team) {
        this.team = team;

        startProxyFragment();
        studentService.readByTeam(team.getNumber());
    }

    public void onReadStudenTeams(List<StudentTeam> studentTeams) {
        if (isProxyFragmentNotDetached()) {
            dependencyWrapper.setStudentTeams(studentTeams);
            startFragment(TeamDetailsFragment.newInstance(this, team, dependencyWrapper));
        }
    }

    @Override
    public void updateTeam(Team team, Team teamToUpdate) {
        teamService.update(team, teamToUpdate);
    }

    @Override
    public void deleteTeam(Team team) {
        teamService.delete(team);
    }

    @Override
    public void newStudent(Team team) {
        startFragment(StudentNewFragment.newInstance(this, team));
    }

    @Override
    public void createStudent(Student student, Team team) {
        studentService.create(student, team);
    }

    @Override
    public void showStudent(Student student) {
        startFragment(StudentDetailsFragment.newInstance(this, student));
    }

    @Override
    public void updateStudent(Student student, Student studentToUpdate) {
        studentService.update(student, studentToUpdate);
    }
}