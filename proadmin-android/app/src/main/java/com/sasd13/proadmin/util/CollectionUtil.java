package com.sasd13.proadmin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;

public class CollectionUtil {

    public static void sortProjectsByAcademicLevel(List<Project> projects) {
        Collections.sort(projects, new Comparator<Project>() {

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

    public static List<Project> filterProjectsByAcademicLevel(List<Project> projects, AcademicLevel academicLevel) {
        List<Project> list = new ArrayList<>();

        for (Project project : projects) {
            if (academicLevel.equals(project.getAcademicLevel())) {
                list.add(project);
            }
        }

        return list;
    }
}
