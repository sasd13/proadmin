package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.ReportDAO;
import com.sasd13.proadmin.dao.util.WhereClauseException;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteReportDAO extends SQLiteEntityDAO<Report> implements ReportDAO {

    @Override
    protected ContentValues getContentValues(Report report) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, report.getId());
        values.put(COLUMN_DATEMEETING, String.valueOf(report.getMeetingDate()));
        values.put(COLUMN_WEEK, report.getWeek());
        values.put(COLUMN_TEAMCOMMENT, report.getComment());
        values.put(COLUMN_RUNNINGTEAM, report.getRunningTeam().getId());

        return values;
    }

    @Override
    protected Report getCursorValues(Cursor cursor) {
        RunningTeam runningteam = new RunningTeam();
        runningteam.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_RUNNINGTEAM)));

        Report report = new Report(runningteam);
        report.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        report.setMeetingDate(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_DATEMEETING))));
        report.setWeek(cursor.getInt(cursor.getColumnIndex(COLUMN_WEEK)));
        report.setComment(cursor.getString(cursor.getColumnIndex(COLUMN_TEAMCOMMENT)));

        return report;
    }

    @Override
    public long insert(Report report) {
        return db.insert(TABLE, null, getContentValues(report));
    }

    @Override
    public void update(Report report) {
        db.update(TABLE, getContentValues(report), COLUMN_ID + " = ?", new String[]{String.valueOf(report.getId())});
    }

    @Override
    public void delete(long id) {
        String query = "UPDATE " + TABLE
                + " SET "
                    + COLUMN_DELETED + " = 1"
                + " WHERE "
                    + COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Report select(long id) {
        Report report = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                report = getCursorValues(cursor);
            }
        }
        cursor.close();

        return report;
    }

    @Override
    public List<Report> select(Map<String, String[]> parameters) {
        List<Report> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                        + WhereClauseParser.parse(ReportDAO.class, parameters);

            Cursor cursor = db.rawQuery(query, null);
            fillListWithCursor(list, cursor);
            cursor.close();
        } catch (WhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void fillListWithCursor(List<Report> list, Cursor cursor) {
        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                list.add(getCursorValues(cursor));
            }
        }
    }

    @Override
    public List<Report> selectAll() {
        List<Report> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);
        fillListWithCursor(list, cursor);
        cursor.close();

        return list;
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
