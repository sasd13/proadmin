package com.sasd13.proadmin.util.wrapper.read.project;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class ProjectReadWrapper implements IReadWrapper<Project> {

    private List<Project> projects;

    public ProjectReadWrapper(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public List<Project> getWrapped() {
        return projects;
    }
}
