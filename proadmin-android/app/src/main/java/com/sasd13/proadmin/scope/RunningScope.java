package com.sasd13.proadmin.scope;

import com.sasd13.proadmin.bean.running.Running;

import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class RunningScope extends Observable {

    private Running running;

    public Running getRunning() {
        return running;
    }

    public void setRunning(Running running) {
        this.running = running;

        setChanged();
        notifyObservers();
    }
}
