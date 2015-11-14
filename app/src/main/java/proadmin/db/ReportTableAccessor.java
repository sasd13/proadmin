package proadmin.db;

import java.util.List;

import proadmin.beans.Project;
import proadmin.beans.Report;
import proadmin.beans.Teacher;
import proadmin.beans.Team;

public interface ReportTableAccessor {

    String TABLE_NAME = "reports";

    String REPORT_ID = "report_id";
    String REPORT_DATEMEETING = "report_datemeeting";
    String REPORT_WEEKNUMBER = "report_weeknumber";
    String REPORT_TEAMCOMMENT = "report_teamcomment";
    String REPORT_DELETED = "report_deleted";
    String TEACHERS_TEACHER_ID = "teachers_teacher_id";
    String PROJECTS_PROJECT_ID = "project_project_id";
    String TEAMS_TEAM_ID = "teams_team_id";

    void insert(Report report, Teacher teacher, Project project, Team team);

    void update(Report report);

    void delete(Report report);

    Report select(long id);

    List<Report> selectByTeam(Team team);
}
