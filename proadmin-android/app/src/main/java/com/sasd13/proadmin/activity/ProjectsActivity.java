package com.sasd13.proadmin.activity;

import android.content.Intent;
import android.os.Bundle;

import com.sasd13.androidex.gui.widget.pager.IPagerHandler;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.fragment.project.ProjectDetailsFragment;
import com.sasd13.proadmin.activity.fragment.project.ProjectsFragment;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.content.Extra;

public class ProjectsActivity extends MotherActivity {

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
        Intent intent = new Intent(this, RunningsActivity.class);
        intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
        intent.putExtra(Extra.RUNNING_YEAR, running.getYear());
        intent.putExtra(Extra.PROJECT_CODE, running.getProject().getCode());

        startActivity(intent);
    }

    public void newRunning(Project project) {
        Intent intent = new Intent(this, RunningsActivity.class);
        intent.putExtra(Extra.MODE, Extra.MODE_NEW);
        intent.putExtra(Extra.PROJECT_CODE, project.getCode());

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (pagerHandler == null || !pagerHandler.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}