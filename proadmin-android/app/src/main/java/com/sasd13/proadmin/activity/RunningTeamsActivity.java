package com.sasd13.proadmin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.fragment.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.activity.fragment.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.activity.fragment.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.member.TeamBaseBuilder;
import com.sasd13.proadmin.util.builder.project.ProjectBaseBuilder;
import com.sasd13.proadmin.util.builder.running.RunningTeamBaseBuilder;

public class RunningTeamsActivity extends MotherActivity {

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
            listRunningTeams();
        } else if (getIntent().hasExtra(Extra.RUNNING_YEAR)
                && getIntent().hasExtra(Extra.PROJECT_CODE)
                && getIntent().hasExtra(Extra.TEAM_NUMBER)
                && getIntent().hasExtra(Extra.ACADEMICLEVEL_CODE)) {
            startFragmentWithoutBackStack(RunningTeamDetailsFragment.newInstance(getRunningTeamFromIntent()));
        } else {
            if (getIntent().hasExtra(Extra.PROJECT_CODE)) {
                startFragmentWithoutBackStack(RunningTeamNewFragment.newInstance(getProjectFromIntent()));
            } else if (getIntent().hasExtra(Extra.TEAM_NUMBER)) {
                startFragmentWithoutBackStack(RunningTeamNewFragment.newInstance(getProjectFromIntent()));
            } else {
                startFragmentWithoutBackStack(RunningTeamNewFragment.newInstance());
            }
        }
    }

    public void listRunningTeams() {
        startFragmentWithoutBackStack(RunningTeamsFragment.newInstance());
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

        return new RunningTeamBaseBuilder(year, projectCode, SessionHelper.getExtraId(getApplication(), Extra.TEACHER_NUMBER), null, null).build();
    }

    private Project getProjectFromIntent() {
        String projectCode = getIntent().getStringExtra(Extra.PROJECT_CODE);

        return new ProjectBaseBuilder(projectCode).build();
    }

    private Team getTeamFromIntent() {
        String teamNumber = getIntent().getStringExtra(Extra.TEAM_NUMBER);

        return new TeamBaseBuilder(teamNumber).build();
    }

    public void showRunningTeam(RunningTeam runningTeam) {
        startFragment(RunningTeamDetailsFragment.newInstance(runningTeam));
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void newRunningTeam() {
        startFragment(RunningTeamNewFragment.newInstance());
    }

    public void showReport(Report report) {
        Intent intent = new Intent(this, ReportsActivity.class);
        intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
        intent.putExtra(Extra.REPORT_NUMBER, report.getNumber());

        startActivity(intent);
    }

    public void newReport(RunningTeam runningTeam) {
        Intent intent = new Intent(this, ReportsActivity.class);
        intent.putExtra(Extra.MODE, Extra.MODE_NEW);
        intent.putExtra(Extra.RUNNING_YEAR, runningTeam.getRunning().getYear());
        intent.putExtra(Extra.PROJECT_CODE, runningTeam.getRunning().getProject().getCode());
        intent.putExtra(Extra.TEAM_NUMBER, runningTeam.getTeam().getNumber());
        intent.putExtra(Extra.ACADEMICLEVEL_CODE, runningTeam.getAcademicLevel().getCode());

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}