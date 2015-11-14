package proadmin.db;

import java.util.List;

import proadmin.beans.Project;

public interface ProjectTableAccessor {

    String TABLE_NAME = "projects";

    String PROJECT_ID = "project_id";
    String PROJECT_CODE = "project_code";
    String PROJECT_ACADEMICLEVEL = "project_academiclevel";
    String PROJECT_TITLE = "project_title";
    String PROJECT_DESCRIPTION = "project_description";
    String PROJECT_DELETED = "project_deleted";

    void insert(Project project);

    void update(Project project);

    void delete(Project project);

    Project select(long id);

    List<Project> selectByCode(String code);

    List<Project> selectByAcademicLevel(String academicLevel);
}
