package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class ProjectScope extends Scope {

    private List<Project> projects, projectsToAdd;
    private Project project;
    private List<Running> runnings;
    private Running running;
    private Teacher teacher;
    private String patternDate;

    public ProjectScope() {
        this.projects = new ArrayList<>();
        this.projectsToAdd = Collections.emptyList();
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

    public void clearProjectsToAdd() {
        projectsToAdd = Collections.emptyList();
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;

        setChanged();
        notifyObservers();
    }

    public String getPatternDate() {
        return patternDate;
    }

    public void setPatternDate(String patternDate) {
        this.patternDate = patternDate;

        setChanged();
        notifyObservers();
    }
}
