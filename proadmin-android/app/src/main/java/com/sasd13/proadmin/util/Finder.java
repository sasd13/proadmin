package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.project.Project;

import java.util.List;

/**
 * Created by ssaidali2 on 06/11/2016.
 */
public class Finder {

    public static int indexOf(String projectCode, List<Project> projects) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getCode().equalsIgnoreCase(projectCode)) {
                return i;
            }
        }

        return -1;
    }
}
