package com.sasd13.proadmin.builder.project;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.builder.IBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class ProjectsCodesBuilder implements IBuilder<List<String>> {

    private List<Project> projects;

    public ProjectsCodesBuilder(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        for (Project project : projects) {
            list.add(project.getCode());
        }

        return list;
    }
}
