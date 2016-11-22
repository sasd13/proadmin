package com.sasd13.proadmin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.fragment.project.ProjectDetailsFragment;
import com.sasd13.proadmin.activity.fragment.project.ProjectsFragment;
import com.sasd13.proadmin.activity.fragment.running.RunningDetailsFragment;
import com.sasd13.proadmin.activity.fragment.running.RunningNewFragment;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

public class ProjectsActivity extends MotherActivity {

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
        listProjects();
    }

    public void listProjects() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, ProjectsFragment.newInstance())
                .commit();
    }

    public void showProject(Project project) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, ProjectDetailsFragment.newInstance(project))
                .addToBackStack(null)
                .commit();
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

    public void newRunning(Project project) {
        startFragment(RunningNewFragment.newInstance(project));
    }

    @Override
    public void onBackPressed() {
        if (pager == null || !pager.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}