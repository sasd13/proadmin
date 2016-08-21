package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.project.Project;

import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class Finder {

    public static Project findProjectWithCode(String code, List<Project> projects) {
        for (Project project : projects) {
            if (project.getCode().equals(code)) {
                return project;
            }
        }

        return null;
    }
}
