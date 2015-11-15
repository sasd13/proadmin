package proadmin.db;

import java.util.List;

import proadmin.bean.running.Report;

public interface ReportTableAccessor {

    String REPORT_TABLE_NAME = "reports";

    String REPORT_ID = "report_id";
    String REPORT_DATEMEETING = "report_datemeeting";
    String REPORT_WEEKNUMBER = "report_weeknumber";
    String REPORT_TEAMCOMMENT = "report_teamcomment";
    String REPORT_DELETED = "report_deleted";
    String TEACHERS_TEACHER_ID = "teachers_teacher_id";
    String PROJECTS_PROJECT_ID = "project_project_id";
    String TEAMS_TEAM_ID = "teams_team_id";

    long insert(Report report);

    void update(Report report);

    void delete(long id);

    Report select(long id);

    List<Report> selectByTeam(long teamId);
}
