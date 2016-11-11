package com.sasd13.proadmin.wrapper.read.running;

import com.sasd13.proadmin.bean.running.Running;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class RunningReadWrapper implements IRunningReadWrapper {

    private List<Running> runnings;

    public RunningReadWrapper(List<Running> runnings) {
        this.runnings = runnings;
    }

    @Override
    public List<Running> getWrapped() {
        return runnings;
    }
}
