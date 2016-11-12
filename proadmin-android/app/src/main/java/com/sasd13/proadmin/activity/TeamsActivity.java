package com.sasd13.proadmin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.fragment.student.StudentDetailsFragment;
import com.sasd13.proadmin.activity.fragment.student.StudentNewFragment;
import com.sasd13.proadmin.activity.fragment.team.TeamDetailsFragment;
import com.sasd13.proadmin.activity.fragment.team.TeamNewFragment;
import com.sasd13.proadmin.activity.fragment.team.TeamsFragment;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.content.extra.Extra;
import com.sasd13.proadmin.util.builder.member.TeamBaseBuilder;

public class TeamsActivity extends MotherActivity {

    private IPagerHandler pagerHandler;

    public void setPagerHandler(IPagerHandler pagerHandler) {
        this.pagerHandler = pagerHandler;
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

        return new TeamBaseBuilder(teamNumber).build();
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
        if (pagerHandler == null || !pagerHandler.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}