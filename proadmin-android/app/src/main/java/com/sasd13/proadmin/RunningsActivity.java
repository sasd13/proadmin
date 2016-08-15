package com.sasd13.proadmin;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.fragment.running.RunningFragment;
import com.sasd13.proadmin.fragment.running.RunningsFragment;
import com.sasd13.proadmin.util.SessionHelper;

public class RunningsActivity extends MotherActivity {

    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_runnings);
        GUIHelper.colorTitles(this);

        builView();
    }

    private void builView() {
        project = Cache.load(
                this,
                SessionHelper.getIntentExtraId(this, Extra.PROJECT_ID),
                Project.class);

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

    public void startFragment(Fragment fragment) {
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