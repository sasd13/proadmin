package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.project.Project;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class RunningTeamWrapper {

    private List<Project> projects;
    private List<AcademicLevel> academicLevels;

    public RunningTeamWrapper(List<Project> projects, List<AcademicLevel> academicLevels) {
        this.projects = projects;
        this.academicLevels = academicLevels;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<AcademicLevel> getAcademicLevels() {
        return academicLevels;
    }
}
