package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.android.model.Running;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class RunningScope extends Scope {

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
