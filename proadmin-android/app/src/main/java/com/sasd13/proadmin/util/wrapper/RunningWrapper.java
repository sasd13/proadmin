package com.sasd13.proadmin.util.wrapper;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class RunningWrapper extends Observable {

    private Running running;
    private Project project;

    public RunningWrapper(Running running) {
        this.running = running;
    }

    public Running getRunning() {
        return running;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;

        setChanged();
        notifyObservers();
    }
}
