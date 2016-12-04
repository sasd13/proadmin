package com.sasd13.proadmin.controller;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.controller.caller.team.TeamsServiceCaller;
import com.sasd13.proadmin.view.student.StudentDetailsFragment;
import com.sasd13.proadmin.view.student.StudentNewFragment;
import com.sasd13.proadmin.view.team.TeamDetailsFragment;
import com.sasd13.proadmin.view.team.TeamNewFragment;
import com.sasd13.proadmin.view.team.TeamsFragment;
import com.sasd13.proadmin.ws.service.StudentsService;
import com.sasd13.proadmin.ws.service.TeamsService;

import java.util.List;

public class TeamsActivity extends MotherActivity {

    private View contentView;
    private Pager pager;

    private Team team;

    private TeamsService teamsService;
    private StudentsService studentsService;

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .commit();
    }

    private void startFragmentWithBackStack(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container);
        contentView = findViewById(android.R.id.content);
        teamsService = new TeamsService(new TeamsServiceCaller(this));
        //studentsService = new StudentsService();

        buildView();
    }

    private void buildView() {
        GUIHelper.colorTitles(this);
        showFragment();
    }

    private void showFragment() {
        if (!getIntent().hasExtra(Extra.MODE)) {
            listTeams();
        } else if (getIntent().hasExtra(Extra.ID_TEAM_NUMBER)) {
            readTeam(getIntent().getStringExtra(Extra.ID_TEAM_NUMBER));
        } else {
            newTeam();
        }
    }

    public void listTeams() {
        teamsService.readAll();
    }

    public void onReadTeams(List<Team> teams) {
        startFragment(TeamsFragment.newInstance(teams));
    }

    private void readTeam(String number) {
        teamsService.read(number);
        //studentsService.read();
    }

    public void onReadTeam(Team team, List<StudentTeam> studentTeams) {
        startFragment(TeamDetailsFragment.newInstance(team, studentTeams));
    }

    public void newTeam() {
        startFragment(TeamNewFragment.newInstance());
    }

    public void showTeam(Team team) {
        readTeam(team.getNumber());
    }

    public void showStudent(Student student) {
        startFragment(StudentDetailsFragment.newInstance(student));
    }

    public void newStudent() {
        startFragment(StudentNewFragment.newInstance(team));
    }

    public void createTeam(Team team) {
        teamsService.create(team);
    }

    public void updateTeam(Team team, Team teamToUpdate) {
        teamsService.update(team, teamToUpdate);
    }

    public void deleteTeam(Team team) {
        teamsService.delete(team);
    }

    public void createStudent(Student student, Team team) {
        studentsService.create(student, team);
    }

    public void updateStudent(Student student, Student studentToUpdate) {
        studentsService.update(student, studentToUpdate);
    }

    public void deleteStudentFromTeam(StudentTeam studentTeam) {
        studentsService.delete(studentTeam);
    }

    public void displayMessage(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (pager == null || !pager.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}