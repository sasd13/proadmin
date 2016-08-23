package com.sasd13.proadmin.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.project.Project;

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
