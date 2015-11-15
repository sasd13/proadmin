package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import proadmin.beans.Project;
import proadmin.beans.Report;
import proadmin.beans.Teacher;
import proadmin.beans.Team;
import proadmin.db.ReportTableAccessor;

public class ReportDAO extends SQLiteTableDAO<Report> implements ReportTableAccessor {

    @Override
    protected ContentValues getContentValues(Report report) {
        ContentValues values = new ContentValues();

        //values.put(REPORT_ID, report.getId()); //autoincrement
        values.put(REPORT_DATEMEETING, String.valueOf(report.getDateMeeting()));
        values.put(REPORT_WEEKNUMBER, report.getWeekNumber());
        values.put(REPORT_TEAMCOMMENT, report.getTeamComment());
        values.put(TEACHERS_TEACHER_ID, report.getTeacher().getId());
        values.put(PROJECTS_PROJECT_ID, report.getProject().getId());
        values.put(TEAMS_TEAM_ID, report.getTeam().getId());

        return values;
    }

    @Override
    protected Report getCursorValues(Cursor cursor) {
        Report report = new Report();

        report.setId(cursor.getLong(cursor.getColumnIndex(REPORT_ID)));
        report.setDateMeeting(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(REPORT_DATEMEETING))));
        report.setWeekNumber(cursor.getLong(cursor.getColumnIndex(REPORT_WEEKNUMBER)));
        report.setTeamComment(cursor.getString(cursor.getColumnIndex(REPORT_TEAMCOMMENT)));

        Teacher teacher = new Teacher();
        teacher.setId(cursor.getLong(cursor.getColumnIndex(TEACHERS_TEACHER_ID)));
        report.setTeacher(teacher);

        Project project = new Project();
        project.setId(cursor.getLong(cursor.getColumnIndex(PROJECTS_PROJECT_ID)));
        report.setProject(project);

        Team team = new Team();
        team.setId(cursor.getLong(cursor.getColumnIndex(TEAMS_TEAM_ID)));
        report.setTeam(team);

        return report;
    }

    @Override
    public long insert(Report report) {
        ContentValues values = getContentValues(report);
        values.put(REPORT_DELETED, false);

        return getDB().insert(REPORT_TABLE_NAME, null, values);
    }

    @Override
    public void update(Report report) {
        getDB().update(REPORT_TABLE_NAME, getContentValues(report), REPORT_ID + " = ?", new String[]{String.valueOf(report.getId())});
    }

    @Override
    public void delete(long id) {
        getDB().delete(REPORT_TABLE_NAME, REPORT_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Report select(long id) {
        Report report = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            report = getCursorValues(cursor);
        }
        cursor.close();

        return report;
    }

    @Override
    public List<Report> selectByTeam(long teamId) {
        List<Report> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + REPORT_TABLE_NAME
                        + " where " + TEAMS_TEAM_ID + " = ?", new String[]{String.valueOf(teamId)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
