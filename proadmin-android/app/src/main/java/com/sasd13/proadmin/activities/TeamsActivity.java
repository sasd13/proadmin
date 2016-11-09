package com.sasd13.proadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.team.TeamDetailsFragment;
import com.sasd13.proadmin.activities.fragments.team.TeamsFragment;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.member.TeamBaseBuilder;
import com.sasd13.proadmin.util.builder.project.ProjectBaseBuilder;

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
        } else if (getIntent().hasExtra(Extra.TEAM_NUMBER)) {
            startFragmentWithoutBackStack(TeamDetailsFragment.newInstance(getRunningTeamFromIntent()));
        } else {
            if (getIntent().hasExtra(Extra.PROJECT_CODE)) {
                //startFragmentWithoutBackStack(TeamNewFragment.newInstance(getProjectFromIntent()));
            } else {
                //startFragmentWithoutBackStack(TeamNewFragment.newInstance());
            }
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

    private RunningTeam getRunningTeamFromIntent() {
        int year = getIntent().getIntExtra(Extra.RUNNING_YEAR, 0);
        String projectCode = getIntent().getStringExtra(Extra.PROJECT_CODE);

        //return new TeamBaseBuilder(year, projectCode, SessionHelper.getExtraId(this, Extra.TEACHER_NUMBER)).build();
        return null;
    }

    private Project getProjectFromIntent() {
        String projectCode = getIntent().getStringExtra(Extra.PROJECT_CODE);

        return new ProjectBaseBuilder(projectCode).build();
    }

    /*public void showTeam(Team team) {
        startFragment(TeamEditFragment.newInstance(team));
    }*/

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void newTeam() {
        //startFragment(TeamNewFragment.newInstance());
    }

    public void showTeam(Team team) {
        Intent intent = new Intent(this, TeamsActivity.class);
        intent.putExtra(Extra.TEAM_NUMBER, team.getNumber());

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}