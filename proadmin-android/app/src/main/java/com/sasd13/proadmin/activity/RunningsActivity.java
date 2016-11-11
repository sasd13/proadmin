package com.sasd13.proadmin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.fragment.running.RunningDetailsFragment;
import com.sasd13.proadmin.activity.fragment.running.RunningNewFragment;
import com.sasd13.proadmin.activity.fragment.running.RunningsFragment;
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

        setContentView(R.layout.activity_container);

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
            startFragmentWithoutBackStack(RunningDetailsFragment.newInstance(getRunningFromIntent()));
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
                .replace(R.id.activity_container_fragment, fragment)
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
        startFragment(RunningDetailsFragment.newInstance(running));
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void newRunning() {
        startFragment(RunningNewFragment.newInstance());
    }

    public void showTeam(Team team) {
        Intent intent = new Intent(this, TeamsActivity.class);
        intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
        intent.putExtra(Extra.TEAM_NUMBER, team.getNumber());

        startActivity(intent);
    }

    public void newRunningTeam(Running running) {
        Intent intent = new Intent(this, RunningTeamsActivity.class);
        intent.putExtra(Extra.MODE, Extra.MODE_NEW);
        intent.putExtra(Extra.RUNNING_YEAR, running.getYear());
        intent.putExtra(Extra.PROJECT_CODE, running.getProject().getCode());

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}