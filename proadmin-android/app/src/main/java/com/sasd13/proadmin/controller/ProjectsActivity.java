package com.sasd13.proadmin.controller;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.androidex.gui.widget.pager.Pager;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.caller.project.ProjectsServiceCaller;
import com.sasd13.proadmin.controller.caller.running.RunningsServiceCaller;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.view.project.ProjectDetailsFragment;
import com.sasd13.proadmin.view.project.ProjectsFragment;
import com.sasd13.proadmin.view.running.RunningDetailsFragment;
import com.sasd13.proadmin.view.running.RunningNewFragment;
import com.sasd13.proadmin.ws.service.ProjectsService;
import com.sasd13.proadmin.ws.service.RunningsService;

import java.util.List;

public class ProjectsActivity extends MotherActivity {

    private View contentView;
    private Pager pager;

    private Project project;

    private ProjectsService projectsService;
    private RunningsService runningsService;

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .commit();
    }

    private void startFragmentWithBackStack(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container);
        contentView = findViewById(android.R.id.content);
        projectsService = new ProjectsService(new ProjectsServiceCaller(this));
        runningsService = new RunningsService(new RunningsServiceCaller(this));

        buildView();
    }

    private void buildView() {
        GUIHelper.colorTitles(this);
        listProjects();
    }

    public void listProjects() {
        projectsService.readAll();
    }

    public void onReadProjects(List<Project> projects) {
        startFragment(ProjectsFragment.newInstance(projects));
    }

    public void showProject(Project project) {
        this.project = project;

        runningsService.read(project, SessionHelper.getExtraIdTeacherNumber(this));
    }

    public void onReadRunnings(List<Running> runnings) {
        startFragmentWithBackStack(ProjectDetailsFragment.newInstance(project, runnings));
    }

    public void showRunning(Running running) {
        startFragment(RunningDetailsFragment.newInstance(running));
    }

    public void newRunning() {
        startFragment(RunningNewFragment.newInstance(project));
    }

    public void createRunning(Running running) {
        runningsService.create(running);
    }

    public void deleteRunning(Running running) {
        runningsService.delete(running);
    }

    public void displayMessage(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (pager == null || !pager.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}