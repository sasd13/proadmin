package com.sasd13.proadmin.util.wrapper;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class ProjectWrapper extends Observable {

    private Project project;
    private List<Running> runnings;

    public ProjectWrapper() {
        runnings = new ArrayList<>();
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
}
