package com.sasd13.proadmin.util.wrapper;

import com.sasd13.proadmin.bean.project.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class ProjectsWrapper extends Observable {

    private List<Project> projects, nextProjects;

    public ProjectsWrapper() {
        projects = new ArrayList<>();
        nextProjects = new ArrayList<>();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;

        setChanged();
        notifyObservers();
    }

    public List<Project> getNextProjects() {
        return nextProjects;
    }

    public void setNextProjects(List<Project> nextProjects) {
        this.nextProjects = nextProjects;

        setChanged();
        notifyObservers();
    }
}
