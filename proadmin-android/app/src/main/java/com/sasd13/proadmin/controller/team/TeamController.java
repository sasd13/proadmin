package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.controller.IStudentController;
import com.sasd13.proadmin.controller.ITeamController;
import com.sasd13.proadmin.fragment.student.StudentDetailsFragment;
import com.sasd13.proadmin.fragment.student.StudentNewFragment;
import com.sasd13.proadmin.fragment.team.TeamDetailsFragment;
import com.sasd13.proadmin.fragment.team.TeamNewFragment;
import com.sasd13.proadmin.fragment.team.TeamsFragment;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.builder.member.DefaultStudentBuilder;
import com.sasd13.proadmin.util.builder.member.DefaultTeamBuilder;
import com.sasd13.proadmin.util.wrapper.StudentWrapper;
import com.sasd13.proadmin.util.wrapper.TeamWrapper;
import com.sasd13.proadmin.util.wrapper.TeamsWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

import java.util.List;

public class TeamController extends Controller implements ITeamController, IStudentController {

    private Requestor requestor;
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
    private TeamsWrapper teamsWrapper;
    private TeamWrapper teamWrapper;

    public TeamController(MainActivity mainActivity, Requestor requestor, ITeamService teamService, IStudentService studentService) {
        super(mainActivity);

        this.requestor = requestor;
        this.teamService = teamService;
        this.studentService = studentService;
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
        readTeams();
    }

    private void readTeams() {
        if (teamReadStrategy == null) {
            teamReadStrategy = new TeamReadStrategy(this, teamService);
        }

        requestor.setStrategy(teamReadStrategy);
        requestor.execute();
    }

    void onReadTeams(List<Team> teams) {
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
        if (teamCreateStrategy == null) {
            teamCreateStrategy = new TeamCreateStrategy(this, teamService);
        }

        requestor.setStrategy(teamCreateStrategy);
        requestor.execute(team);
    }

    @Override
    public void showTeam(Team team) {
        teamWrapper = new TeamWrapper(team);

        startFragment(TeamDetailsFragment.newInstance(teamWrapper));
        listStudents(team);
    }

    void onReadStudenTeams(List<StudentTeam> studentTeams) {
        teamWrapper.setStudentTeams(studentTeams);
    }

    @Override
    public void updateTeam(Team team, Team teamToUpdate) {
        if (teamUpdateStrategy == null) {
            teamUpdateStrategy = new TeamUpdateStrategy(this, teamService);
        }

        requestor.setStrategy(teamUpdateStrategy);
        requestor.execute(getTeamUpdateWrapper(team, teamToUpdate));
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

        requestor.setStrategy(teamDeleteStrategy);
        requestor.execute(teams);
    }

    @Override
    public void listStudents(Team team) {
        if (studentReadStrategy == null) {
            studentReadStrategy = new StudentReadStrategy(this, studentService);
        }

        studentReadStrategy.clearParameters();
        studentReadStrategy.putParameter(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        requestor.setStrategy(studentReadStrategy);
        requestor.execute();
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

        requestor.setStrategy(studentCreateStrategy);
        requestor.execute(getStudentTeam(student, team));
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

        requestor.setStrategy(studentUpdateStrategy);
        requestor.execute(getStudentUpdateWrapper(student, studentToUpdate));
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

        requestor.setStrategy(studentDeleteStrategy);
        requestor.execute(studentTeams);
    }
}