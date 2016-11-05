package com.sasd13.proadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.running.RunningFragment;
import com.sasd13.proadmin.activities.fragments.running.RunningsFragment;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.SessionHelper;

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

        if (getIntent().hasExtra(Extra.RUNNING_YEAR) && getIntent().hasExtra(Extra.RUNNING_PROJECT_CODE)) {
            showRunning(null);
        } else {
            listRunnings();
        }
    }

    public void listRunnings() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.runnings_fragment, RunningsFragment.newInstance())
                .commit();
    }

    public void showRunning(Running running) {
        startFragment(RunningFragment.newInstance(running, true));
    }

    public void newRunning() {
        startFragment(RunningFragment.newInstance(null, false));
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.runnings_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void showTeam(Team team) {
        SessionHelper.setExtraId(this, Extra.TEAM_NUMBER, team.getNumber());

        startActivity(new Intent(this, RunningsActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}