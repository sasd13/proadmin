package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.ReportDAO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class SQLiteReportDAO extends SQLiteEntityDAO<Report> implements ReportDAO {

    @Override
    protected ContentValues getContentValues(Report report) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATEMEETING, String.valueOf(report.getDateMeeting()));
        values.put(COLUMN_WEEK, report.getWeek());
        values.put(COLUMN_TEAMCOMMENT, report.getTeamComment());
        values.put(COLUMN_TEAM_ID, report.getTeam().getId());

        return values;
    }

    @Override
    protected Report getCursorValues(Cursor cursor) {
        Report report = new Report();

        report.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        report.setDateMeeting(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEMEETING))));
        report.setWeek(cursor.getInt(cursor.getColumnIndex(COLUMN_WEEK)));
        report.setTeamComment(cursor.getString(cursor.getColumnIndex(COLUMN_TEAMCOMMENT)));

        Team team = new Team();
        team.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_TEAM_ID)));
        report.setTeam(team);

        return report;
    }

    @Override
    public long insert(Report report) {
        long id = executeInsert(TABLE, report);

        report.setId(id);

        return id;
    }

    @Override
    public void update(Report report) {
        executeUpdate(TABLE, report, COLUMN_ID, report.getId());
    }

    @Override
    public void delete(long id) {
        //Do nothing
    }

    @Override
    public Report select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Report> select(Map<String, String[]> map) {
        return null;
    }

    @Override
    public List<Report> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }

    @Override
    public void persist(Report report) {
        if (select(report.getId()) == null) {
            insert(report);
        } else {
            update(report);
        }
    }
}
