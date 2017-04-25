package com.sasd13.proadmin.android.bean.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.android.bean.Project;

public class ProjectUpdate implements IUpdateWrapper<Project> {

    private Project project;
    private String code;

    @Override
    public Project getWrapped() {
        return project;
    }

    @Override
    public void setWrapped(Project project) {
        this.project = project;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
