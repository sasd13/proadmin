package com.sasd13.proadmin.ws.wrapper.running;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class RunningReadWrapper implements IReadWrapper<List<Running>> {

    private List<Running> runnings;

    public RunningReadWrapper(List<Running> runnings) {
        this.runnings = runnings;
    }

    @Override
    public List<Running> getWrapped() {
        return runnings;
    }
}
