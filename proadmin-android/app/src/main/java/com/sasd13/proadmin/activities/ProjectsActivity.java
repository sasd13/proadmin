package com.sasd13.proadmin.activities;

import android.content.Intent;
import android.os.Bundle;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.project.ProjectFragment;
import com.sasd13.proadmin.activities.fragments.project.ProjectsFragment;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.SessionHelper;

public class ProjectsActivity extends MotherActivity {

    private IPagerHandler pagerHandler;

    public void setPagerHandler(IPagerHandler pagerHandler) {
        this.pagerHandler = pagerHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);

        buildView();
    }

    private void buildView() {
        GUIHelper.colorTitles(this);
        listProjects();
    }

    public void listProjects() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.projects_fragment, ProjectsFragment.newInstance())
                .commit();
    }

    public void showProject(Project project) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.projects_fragment, ProjectFragment.newInstance(project))
                .addToBackStack(null)
                .commit();
    }

    public void showRunning(Running running) {
        SessionHelper.setExtraId(this, Extra.RUNNING_YEAR, String.valueOf(running.getYear()));
        SessionHelper.setExtraId(this, Extra.RUNNING_PROJECT_CODE, running.getProject().getCode());

        startActivity(new Intent(this, RunningsActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}