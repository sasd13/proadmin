package com.sasd13.proadmin.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.view.student.StudentDetailsFragment;
import com.sasd13.proadmin.view.student.StudentNewFragment;
import com.sasd13.proadmin.view.team.TeamDetailsFragment;
import com.sasd13.proadmin.view.team.TeamNewFragment;
import com.sasd13.proadmin.view.team.TeamsFragment;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.content.Extra;

public class TeamsActivity extends MotherActivity {

    private Pager pager;

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container);

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
            startFragmentWithoutBackStack(TeamDetailsFragment.newInstance(getTeamFromIntent()));
        } else {
            startFragmentWithoutBackStack(TeamNewFragment.newInstance());
        }
    }

    public void listTeams() {
        startFragmentWithoutBackStack(TeamsFragment.newInstance());
    }

    private void startFragmentWithoutBackStack(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .commit();
    }

    private Team getTeamFromIntent() {
        String teamNumber = getIntent().getStringExtra(Extra.ID_TEAM_NUMBER);

        return new Team(teamNumber);
    }

    public void showTeam(Team team) {
        startFragment(TeamDetailsFragment.newInstance(team));
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void newTeam() {
        startFragment(TeamNewFragment.newInstance());
    }

    public void showStudent(Student student) {
        startFragment(StudentDetailsFragment.newInstance(student));
    }

    public void newStudent(Team team) {
        startFragment(StudentNewFragment.newInstance(team));
    }

    @Override
    public void onBackPressed() {
        if (pager == null || !pager.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}