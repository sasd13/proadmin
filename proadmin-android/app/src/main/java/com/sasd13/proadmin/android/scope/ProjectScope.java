package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class ProjectScope extends Observable {

    private boolean loading;
    private List<Project> projects, projectsToAdd;
    private Project project;
    private List<Running> runnings;
    private Running running;

    public ProjectScope() {
        this.projects = new ArrayList<>();
        this.projectsToAdd = Collections.emptyList();
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;

        setChanged();
        notifyObservers();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;

        setChanged();
        notifyObservers();
    }

    public List<Project> getProjectsToAdd() {
        return projectsToAdd;
    }

    public void setProjectsToAdd(List<Project> projectsToAdd) {
        this.projectsToAdd = projectsToAdd;

        setChanged();
        notifyObservers();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;

        setChanged();
        notifyObservers();
    }

    public List<Running> getRunnings() {
        return runnings;
    }

    public void setRunnings(List<Running> runnings) {
        this.runnings = runnings;

        setChanged();
        notifyObservers();
    }

    public Running getRunning() {
        return running;
    }

    public void setRunning(Running running) {
        this.running = running;

        setChanged();
        notifyObservers();
    }
}
