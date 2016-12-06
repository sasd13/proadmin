package com.sasd13.proadmin.util.wrapper;

import com.sasd13.proadmin.bean.running.Running;

import java.util.List;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class ProjectDependencyWrapper {

    private List<Running> runnings;

    public ProjectDependencyWrapper(List<Running> runnings) {
        this.runnings = runnings;
    }

    public List<Running> getRunnings() {
        return runnings;
    }
}
