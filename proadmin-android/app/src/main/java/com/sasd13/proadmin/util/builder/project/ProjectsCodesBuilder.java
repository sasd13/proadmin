package com.sasd13.proadmin.util.builder.project;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.project.Project;

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

        String code;

        for (Project project : projects) {
            code = project.getCode();

            if (!list.contains(code)) {
                list.add(code);
            }
        }

        return list;
    }
}
