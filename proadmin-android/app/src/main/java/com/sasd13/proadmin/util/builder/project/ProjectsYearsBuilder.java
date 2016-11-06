package com.sasd13.proadmin.util.builder.project;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.project.Project;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class ProjectsYearsBuilder implements IBuilder<List<Integer>> {

    private List<Project> projects;

    public ProjectsYearsBuilder(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public List<Integer> build() {
        List<Integer> list = new ArrayList<>();

        int year;
        for (Project project : projects) {
            year = new DateTime(project.getDateCreation()).getYear();

            if (!list.contains(year)) {
                list.add(year);
            }
        }

        return list;
    }
}
