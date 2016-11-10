package com.sasd13.proadmin.wrapper.impl;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.wrapper.IProjectReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class ProjectReadWrapper implements IProjectReadWrapper {

    private List<Project> projects;

    public ProjectReadWrapper(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public List<Project> getProjects() {
        return projects;
    }
}
