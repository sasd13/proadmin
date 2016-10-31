package com.sasd13.proadmin.activities;

import android.content.Intent;
import android.os.Bundle;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.activities.fragments.project.ProjectFragment;
import com.sasd13.proadmin.activities.fragments.project.ProjectsFragment;

public class ProjectsActivity extends MotherActivity {

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

    public void listRunnings(Project project) {
        Intent intent = new Intent(this, RunningsActivity.class);
        intent.putExtra(Extra.PROJECT, project.getCode());

        startActivity(intent);
    }
}