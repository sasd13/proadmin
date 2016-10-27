package com.sasd13.proadmin.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.running.RunningFragment;
import com.sasd13.proadmin.activities.fragments.running.RunningsFragment;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

public class RunningsActivity extends MotherActivity {

    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_runnings);

        //TODO : load from savedInstanceState or parcel
        //project = Cache.load();

        buildView();
    }

    private void buildView() {
        GUIHelper.colorTitles(this);
        listRunnings();
    }

    public void listRunnings() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.runnings_fragment, RunningsFragment.newInstance(project))
                .commit();
    }

    public void newRunning() {
        startFragment(RunningFragment.newInstance(project));
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.runnings_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void editRunning(Running running) {
        RunningFragment runningFragment = RunningFragment.newInstance(project);
        runningFragment.setRunning(running);

        startFragment(runningFragment);
    }
}