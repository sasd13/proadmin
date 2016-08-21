package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

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

    public static void bind(Running target, Running source) {
        target.setYear(source.getYear());
        target.setTeacher(source.getTeacher());
        target.setProject(source.getProject());
    }
}
