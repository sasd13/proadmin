package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.ReportDAO;

import java.sql.Timestamp;
import java.util.List;

public class SQLiteReportDAO extends SQLiteEntityDAO<Report> implements ReportDAO {

    @Override
    protected ContentValues getContentValues(Report report) {
        ContentValues values = new ContentValues();

        values.put(REPORT_DATEMEETING, String.valueOf(report.getDateMeeting()));
        values.put(REPORT_WEEKNUMBER, report.getWeekNumber());
        values.put(REPORT_TEAMCOMMENT, report.getTeamComment());
        values.put(TEAMS_TEAM_ID, report.getTeam().getId());

        return values;
    }

    @Override
    protected Report getCursorValues(Cursor cursor) {
        Report report = new Report();

        report.setId(cursor.getLong(cursor.getColumnIndex(REPORT_ID)));
        report.setDateMeeting(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(REPORT_DATEMEETING))));
        report.setWeekNumber(cursor.getInt(cursor.getColumnIndex(REPORT_WEEKNUMBER)));
        report.setTeamComment(cursor.getString(cursor.getColumnIndex(REPORT_TEAMCOMMENT)));

        Team team = new Team();
        team.setId(cursor.getLong(cursor.getColumnIndex(TEAMS_TEAM_ID)));
        report.setTeam(team);

        return report;
    }

    @Override
    public long insert(Report report) {
        long id = executeInsert(REPORT_TABLE_NAME, report);

        report.setId(id);

        return id;
    }

    @Override
    public void update(Report report) {
        executeUpdate(REPORT_TABLE_NAME, report, REPORT_ID, report.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(REPORT_TABLE_NAME, REPORT_ID, id);
    }

    @Override
    public Report select(long id) {
        String query = "SELECT * FROM " + REPORT_TABLE_NAME
                + " WHERE "
                    + REPORT_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Report> selectAll() {
        String query = "SELECT * FROM " + REPORT_TABLE_NAME;

        return executeSelectAll(query);
    }

    @Override
    public List<Report> selectByTeam(long teamId) {
        String query = "SELECT * FROM " + REPORT_TABLE_NAME
                + " WHERE "
                    + TEAMS_TEAM_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(teamId)});

        return executeSelectMultiResult(cursor);
    }
}
