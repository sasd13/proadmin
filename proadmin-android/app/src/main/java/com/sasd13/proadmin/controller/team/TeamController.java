package com.sasd13.proadmin.controller.team;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.fragment.IStudentController;
import com.sasd13.proadmin.fragment.ITeamController;
import com.sasd13.proadmin.fragment.student.StudentDetailsFragment;
import com.sasd13.proadmin.fragment.student.StudentNewFragment;
import com.sasd13.proadmin.fragment.team.TeamDetailsFragment;
import com.sasd13.proadmin.fragment.team.TeamNewFragment;
import com.sasd13.proadmin.fragment.team.TeamsFragment;
import com.sasd13.proadmin.service.ws.StudentService;
import com.sasd13.proadmin.service.ws.TeamService;
import com.sasd13.proadmin.util.builder.member.DefaultStudentBuilder;
import com.sasd13.proadmin.util.builder.member.DefaultTeamBuilder;
import com.sasd13.proadmin.util.wrapper.StudentWrapper;
import com.sasd13.proadmin.util.wrapper.TeamWrapper;
import com.sasd13.proadmin.util.wrapper.TeamsWrapper;

import java.util.List;

public class TeamController extends Controller implements ITeamController, IStudentController {

    private TeamService teamService;
    private StudentService studentService;
    private TeamsWrapper teamsWrapper;
    private TeamWrapper teamWrapper;

    public TeamController(MainActivity mainActivity) {
        super(mainActivity);

        teamService = new TeamService(new TeamServiceCaller(this, mainActivity));
        studentService = new StudentService(new StudentServiceCaller(this, mainActivity));
    }

    @Override
    public void entry() {
        mainActivity.clearHistory();
        listTeams();
    }

    @Override
    public void listTeams() {
        teamsWrapper = new TeamsWrapper();

        startProxyFragment();
        teamService.readAll();
    }

    public void onReadTeams(List<Team> teams) {
        if (isProxyFragmentNotDetached()) {
            teamsWrapper.setTeams(teams);
            startFragment(TeamsFragment.newInstance(teamsWrapper));
        }
    }

    @Override
    public void newTeam() {
        teamWrapper = new TeamWrapper(new DefaultTeamBuilder().build());

        startFragment(TeamNewFragment.newInstance(teamWrapper));
    }

    @Override
    public void createTeam(Team team) {
        teamService.create(team);
    }

    @Override
    public void showTeam(Team team) {
        teamWrapper = new TeamWrapper(team);

        startFragment(TeamDetailsFragment.newInstance(teamWrapper));
        studentService.readByTeam(team.getNumber());
    }

    public void onReadStudenTeams(List<StudentTeam> studentTeams) {
        teamWrapper.setStudentTeams(studentTeams);
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
        StudentWrapper studentWrapper = new StudentWrapper(new DefaultStudentBuilder().build());
        studentWrapper.setTeam(team);

        startFragment(StudentNewFragment.newInstance(studentWrapper));
    }

    @Override
    public void createStudent(Student student, Team team) {
        studentService.create(student, team);
    }

    @Override
    public void showStudent(Student student) {
        startFragment(StudentDetailsFragment.newInstance(new StudentWrapper(student)));
    }

    @Override
    public void updateStudent(Student student, Student studentToUpdate) {
        studentService.update(student, studentToUpdate);
    }
}