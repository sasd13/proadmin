package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.project.Project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionSorter {

    public static void sortProjectsByAcademicLevel(List<Project> list) {
        Collections.sort(list, new Comparator<Project>() {

            @Override
            public int compare(Project project1, Project project2) {
                int ordinalLevel1 = project1.getAcademicLevel().ordinal();
                int ordinalLevel2 = project2.getAcademicLevel().ordinal();

                return (ordinalLevel1 < ordinalLevel2
                            ? -1
                            : (ordinalLevel1 == ordinalLevel2)
                                ? 0
                                : 1);
            }
        });
    }
}
