package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.fragment.project.ProjectFragment;
import com.sasd13.proadmin.fragment.project.ProjectsFragment;

public class ProjectsActivity extends MotherActivity {

    private ProjectsFragment projectsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);
        GUIHelper.colorTitles(this);

        buildView();
    }

    private void buildView() {
        listProjects();
    }

    public void listProjects() {
        if (projectsFragment == null) {
            projectsFragment = ProjectsFragment.newInstance();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.projects_fragment, projectsFragment)
                .commit();
    }

    public void showProject(Project project) {
        ProjectFragment projectFragment = ProjectFragment.newInstance(project);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.projects_fragment, projectFragment)
                .addToBackStack(null)
                .commit();
    }

    public void listRunnings(Project project) {
        Intent intent = new Intent(this, RunningsActivity.class);
        intent.putExtra(Extra.PROJECT_ID, project.getId());

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}