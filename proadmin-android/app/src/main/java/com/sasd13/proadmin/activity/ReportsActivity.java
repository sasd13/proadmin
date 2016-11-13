package com.sasd13.proadmin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.fragment.report.ReportDetailsFragment;
import com.sasd13.proadmin.activity.fragment.report.ReportNewFragment;
import com.sasd13.proadmin.activity.fragment.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.extra.Extra;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.ReportBaseBuilder;
import com.sasd13.proadmin.util.builder.running.RunningTeamBaseBuilder;

import java.util.Calendar;

public class ReportsActivity extends MotherActivity {

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
        //showFragment();
    }

    private void showFragment() {
        if (!getIntent().hasExtra(Extra.MODE)) {
            listReports();
        } else {
            if (getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_EDIT) {
                if (getIntent().hasExtra(Extra.ID_REPORT_NUMBER)) {
                    startFragmentWithoutBackStack(ReportDetailsFragment.newInstance(getReportIntent()));
                }
            } else {
                if (getIntent().hasExtra(Extra.ID_RUNNING_YEAR)
                        && getIntent().hasExtra(Extra.ID_PROJECT_CODE)
                        && getIntent().hasExtra(Extra.ID_TEAM_NUMBER)
                        && getIntent().hasExtra(Extra.ID_ACADEMICLEVEL_CODE)) {
                    startFragmentWithoutBackStack(ReportNewFragment.newInstance(getRunningTeamFromIntent()));
                }
            }
        }
    }

    public void listReports() {
        startFragmentWithoutBackStack(RunningTeamsFragment.newInstance());
    }

    private void startFragmentWithoutBackStack(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .commit();
    }

    private Report getReportIntent() {
        String reportNumber = SessionHelper.getExtraId(getApplication(), Extra.ID_REPORT_NUMBER);

        return new ReportBaseBuilder(reportNumber).build();
    }

    private RunningTeam getRunningTeamFromIntent() {
        int year = getIntent().getIntExtra(Extra.ID_RUNNING_YEAR, Calendar.getInstance().get(Calendar.YEAR));
        String projectCode = getIntent().getStringExtra(Extra.ID_PROJECT_CODE);
        String teacherNumber = SessionHelper.getExtraId(getApplication(), Extra.ID_TEACHER_NUMBER);
        String teamNumber = getIntent().getStringExtra(Extra.ID_TEAM_NUMBER);
        String academicLevelCode = getIntent().getStringExtra(Extra.ID_ACADEMICLEVEL_CODE);

        return new RunningTeamBaseBuilder(year, projectCode, teacherNumber, teamNumber, academicLevelCode).build();
    }

    public void showReport(Report report) {
        //TODO
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void newReport() {
        //TODO
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}