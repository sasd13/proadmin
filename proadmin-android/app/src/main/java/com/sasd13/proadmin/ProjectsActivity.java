package com.sasd13.proadmin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.fragment.project.ProjectFragment;
import com.sasd13.proadmin.fragment.project.ProjectsFragment;

public class ProjectsActivity extends MotherActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);
        GUIHelper.colorTitles(this);

        builView();
    }

    private void builView() {
        listProjects();
    }

    public void finishFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().remove(fragment).commit();
        fragmentManager.popBackStack();
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

    }
}