package proadmin.db;

import java.util.List;

import proadmin.beans.AcademicLevel;
import proadmin.beans.projects.Project;

public interface ProjectTableAccessor {

    String PROJECT_TABLE_NAME = "projects";

    String PROJECT_ID = "project_id";
    String PROJECT_CODE = "project_code";
    String PROJECT_ACADEMICLEVEL = "project_academiclevel";
    String PROJECT_TITLE = "project_title";
    String PROJECT_DESCRIPTION = "project_description";
    String PROJECT_DELETED = "project_deleted";

    long insert(Project project);

    void update(Project project);

    void delete(long id);

    Project select(long id);

    List<Project> selectByCode(String code);

    List<Project> selectByAcademicLevel(AcademicLevel academicLevel);
}
