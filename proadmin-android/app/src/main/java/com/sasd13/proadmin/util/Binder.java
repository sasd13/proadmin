package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.project.Project;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class Binder {

    public static void bind(Project target, Project source) {
        target.setTitle(source.getTitle());
        target.setAcademicLevel(source.getAcademicLevel());
        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
    }
}
