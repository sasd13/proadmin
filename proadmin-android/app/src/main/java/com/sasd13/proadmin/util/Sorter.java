package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.project.Project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class Sorter {

    public static void sortProjectsByCodeAsc(List<Project> projects) {
        sortProjectsByCode(projects, true);
    }

    public static void sortProjectsByCode(List<Project> projects, final boolean byAsc) {
        Collections.sort(projects, new Comparator<Project>() {
            @Override
            public int compare(Project project1, Project project2) {
                if (byAsc) {
                    return project1.getCode().compareTo(project2.getCode());
                } else {
                    return project2.getCode().compareTo(project1.getCode());
                }
            }
        });
    }
}
