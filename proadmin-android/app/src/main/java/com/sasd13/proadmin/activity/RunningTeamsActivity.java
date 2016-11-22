package com.sasd13.proadmin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.fragment.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.activity.fragment.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.activity.fragment.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.extra.Extra;
import com.sasd13.proadmin.util.SessionHelper;

import java.util.Calendar;

public class RunningTeamsActivity extends MotherActivity {

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
            listRunningTeams();
        } else {
            if (getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_EDIT) {
                if (getIntent().hasExtra(Extra.ID_RUNNING_YEAR)
                        && getIntent().hasExtra(Extra.ID_PROJECT_CODE)
                        && getIntent().hasExtra(Extra.ID_TEAM_NUMBER)
                        && getIntent().hasExtra(Extra.ID_ACADEMICLEVEL_CODE)) {
                    startFragmentWithoutBackStack(RunningTeamDetailsFragment.newInstance(getRunningTeamFromIntent()));
                }
            } else {
                if (getIntent().hasExtra(Extra.ID_RUNNING_YEAR) && getIntent().hasExtra(Extra.ID_PROJECT_CODE)) {
                    startFragmentWithoutBackStack(RunningTeamNewFragment.newInstance(getRunningFromIntent()));
                } else if (getIntent().hasExtra(Extra.ID_TEAM_NUMBER)) {
                    startFragmentWithoutBackStack(RunningTeamNewFragment.newInstance(getTeamFromIntent()));
                } else {
                    startFragmentWithoutBackStack(RunningTeamNewFragment.newInstance());
                }
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
        int year = getIntent().getIntExtra(Extra.ID_RUNNING_YEAR, Calendar.getInstance().get(Calendar.YEAR));
        String projectCode = getIntent().getStringExtra(Extra.ID_PROJECT_CODE);
        String teacherNumber = SessionHelper.getExtraIdTeacherNumber(this);
        String teamNumber = getIntent().getStringExtra(Extra.ID_TEAM_NUMBER);
        String academicLevelCode = getIntent().getStringExtra(Extra.ID_ACADEMICLEVEL_CODE);

        return new RunningTeam(year, projectCode, teacherNumber, teamNumber, academicLevelCode);
    }

    private Running getRunningFromIntent() {
        int year = getIntent().getIntExtra(Extra.ID_RUNNING_YEAR, Calendar.getInstance().get(Calendar.YEAR));
        String projectCode = getIntent().getStringExtra(Extra.ID_PROJECT_CODE);
        String teacherNumber = SessionHelper.getExtraIdTeacherNumber(this);

        return new Running(year, projectCode, teacherNumber);
    }

    private Team getTeamFromIntent() {
        String teamNumber = getIntent().getStringExtra(Extra.ID_TEAM_NUMBER);

        return new Team(teamNumber);
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
        intent.putExtra(Extra.ID_REPORT_NUMBER, report.getNumber());

        startActivity(intent);
    }

    public void newReport(RunningTeam runningTeam) {
        Intent intent = new Intent(this, ReportsActivity.class);
        intent.putExtra(Extra.MODE, Extra.MODE_NEW);
        intent.putExtra(Extra.ID_RUNNING_YEAR, runningTeam.getRunning().getYear());
        intent.putExtra(Extra.ID_PROJECT_CODE, runningTeam.getRunning().getProject().getCode());
        intent.putExtra(Extra.ID_TEAM_NUMBER, runningTeam.getTeam().getNumber());
        intent.putExtra(Extra.ID_ACADEMICLEVEL_CODE, runningTeam.getAcademicLevel().getCode());

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (pager == null || !pager.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}