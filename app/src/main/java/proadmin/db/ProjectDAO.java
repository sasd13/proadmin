package proadmin.db;

import java.util.List;

import proadmin.bean.AcademicLevel;
import proadmin.bean.project.Project;

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
}
