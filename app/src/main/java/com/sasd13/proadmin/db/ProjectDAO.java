package com.sasd13.proadmin.db;

import com.sasd13.wsprovider.proadmin.bean.AcademicLevel;
import com.sasd13.wsprovider.proadmin.bean.project.Project;

import java.util.List;

public interface ProjectDAO {

    String PROJECT_TABLE_NAME = "projects";

    String PROJECT_ID = "project_id";
    String PROJECT_CODE = "project_code";
    String PROJECT_ACADEMICLEVEL = "project_academiclevel";
    String PROJECT_TITLE = "project_title";
    String PROJECT_DESCRIPTION = "project_description";

    long insert(Project project);

    void update(Project project);

    Project select(long id);

    List<Project> selectByAcademicLevel(AcademicLevel academicLevel);

    List<Project> selectAll();

    boolean contains(long id);
}
