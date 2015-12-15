package com.sasd13.proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import proadminlib.bean.running.Report;
import proadminlib.bean.running.Team;
import proadminlib.db.ReportDAO;

public class SQLiteReportDAO extends SQLiteTableDAO<Report> implements ReportDAO {

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
        return getDB().insert(REPORT_TABLE_NAME, null, getContentValues(report));
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

    @Override
    public List<Report> selectAll() {
        List<Report> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + REPORT_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
