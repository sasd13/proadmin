package com.sasd13.proadmin.controller;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.view.report.ReportDetailsFragment;
import com.sasd13.proadmin.view.report.ReportNewFragment;

import java.util.Calendar;

public class ReportsActivity extends MotherActivity {

    private View contentView;
    private Pager pager;

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

        buildView();
    }

    private void buildView() {
        GUIHelper.colorTitles(this);
        showFragment();
    }

    private void showFragment() {
        if (!getIntent().hasExtra(Extra.MODE)) {
            listReports();
        } else {
            if (getIntent().getIntExtra(Extra.MODE, Extra.MODE_NEW) == Extra.MODE_EDIT) {
                if (getIntent().hasExtra(Extra.ID_REPORT_NUMBER)) {
                    startFragmentWithoutBackStack(ReportDetailsFragment.newInstance(getReportFromIntent()));
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
        //startFragmentWithoutBackStack(ReportsFragment.newInstance());
    }

    private void startFragmentWithoutBackStack(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .commit();
    }

    private Report getReportFromIntent() {
        String reportNumber = getIntent().getStringExtra(Extra.ID_REPORT_NUMBER);

        return new Report(reportNumber);
    }

    private RunningTeam getRunningTeamFromIntent() {
        int year = getIntent().getIntExtra(Extra.ID_RUNNING_YEAR, Calendar.getInstance().get(Calendar.YEAR));
        String projectCode = getIntent().getStringExtra(Extra.ID_PROJECT_CODE);
        String teacherNumber = SessionHelper.getExtraIdTeacherNumber(this);
        String teamNumber = getIntent().getStringExtra(Extra.ID_TEAM_NUMBER);
        String academicLevelCode = getIntent().getStringExtra(Extra.ID_ACADEMICLEVEL_CODE);

        return new RunningTeam(year, projectCode, teacherNumber, teamNumber, academicLevelCode);
    }

    public void showReport(Report report) {
        startFragment(ReportDetailsFragment.newInstance(report));
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void newReport() {
        startFragment(ReportNewFragment.newInstance());
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