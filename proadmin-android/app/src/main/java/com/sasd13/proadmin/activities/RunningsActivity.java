package com.sasd13.proadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.running.RunningEditFragment;
import com.sasd13.proadmin.activities.fragments.running.RunningNewFragment;
import com.sasd13.proadmin.activities.fragments.running.RunningsFragment;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.project.ProjectBaseBuilder;
import com.sasd13.proadmin.util.builder.running.RunningBaseBuilder;

public class RunningsActivity extends MotherActivity {

    private IPagerHandler pagerHandler;

    public void setPagerHandler(IPagerHandler pagerHandler) {
        this.pagerHandler = pagerHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_runnings);

        buildView();
    }

    private void buildView() {
        GUIHelper.colorTitles(this);
        showFragment();
    }

    private void showFragment() {
        if (!getIntent().hasExtra(Extra.MODE)) {
            listRunnings();
        } else if (getIntent().hasExtra(Extra.RUNNING_YEAR) && getIntent().hasExtra(Extra.PROJECT_CODE)) {
            startFragmentWithoutBackStack(RunningEditFragment.newInstance(getRunningFromIntent()));
        } else {
            if (getIntent().hasExtra(Extra.PROJECT_CODE)) {
                startFragmentWithoutBackStack(RunningNewFragment.newInstance(getProjectFromIntent()));
            } else {
                startFragmentWithoutBackStack(RunningNewFragment.newInstance());
            }
        }
    }

    public void listRunnings() {
        startFragmentWithoutBackStack(RunningsFragment.newInstance());
    }

    private void startFragmentWithoutBackStack(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.runnings_fragment, fragment)
                .commit();
    }

    private Running getRunningFromIntent() {
        int year = getIntent().getIntExtra(Extra.RUNNING_YEAR, 0);
        String projectCode = getIntent().getStringExtra(Extra.PROJECT_CODE);

        return new RunningBaseBuilder(year, projectCode, SessionHelper.getExtraId(this, Extra.TEACHER_NUMBER)).build();
    }

    private Project getProjectFromIntent() {
        String projectCode = getIntent().getStringExtra(Extra.PROJECT_CODE);

        return new ProjectBaseBuilder(projectCode).build();
    }

    public void showRunning(Running running) {
        startFragment(RunningEditFragment.newInstance(running));
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.runnings_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void newRunning() {
        startFragment(RunningNewFragment.newInstance());
    }

    public void showTeam(Team team) {
        Intent intent = new Intent(this, TeamActivity.class);
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