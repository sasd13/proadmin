package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.ReportDAO;
import com.sasd13.proadmin.dao.condition.ConditionBuilder;
import com.sasd13.proadmin.dao.condition.ConditionException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteReportDAO extends SQLiteEntityDAO<Report> implements ReportDAO {

    private SQLiteLeadEvaluationDAO leadEvaluationDAO;
    private SQLiteIndividualEvaluationDAO individualEvaluationDAO;

    public SQLiteReportDAO() {
        leadEvaluationDAO = new SQLiteLeadEvaluationDAO();
        individualEvaluationDAO = new SQLiteIndividualEvaluationDAO();
    }

    @Override
    public LeadEvaluationDAO getLeadEvaluationDAO() {
        return leadEvaluationDAO;
    }

    @Override
    public IndividualEvaluationDAO getIndividualEvaluationDAO() {
        return individualEvaluationDAO;
    }

    @Override
    public void setDB(SQLiteDatabase db) {
        super.setDB(db);

        leadEvaluationDAO.setDB(db);
        individualEvaluationDAO.setDB(db);
    }

    @Override
    protected ContentValues getContentValues(Report report) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, report.getId());
        values.put(COLUMN_MEETINGDATE, String.valueOf(report.getMeetingDate()));
        values.put(COLUMN_SESSIONNUMBER, report.getSessionNumber());
        values.put(COLUMN_COMMENT, report.getComment());
        values.put(COLUMN_RUNNINGTEAM, report.getRunningTeam().getId());

        return values;
    }

    @Override
    protected Report getCursorValues(Cursor cursor) {
        RunningTeam runningteam = new RunningTeam();
        runningteam.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_RUNNINGTEAM)));

        Report report = new Report(runningteam);
        report.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        report.setMeetingDate(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MEETINGDATE))));
        report.setSessionNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_SESSIONNUMBER)));
        report.setComment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)));

        return report;
    }

    @Override
    public long insert(Report report) {
        long id = 0;

        db.beginTransaction();

        try {
            db.insert(TABLE, null, getContentValues(report));

            leadEvaluationDAO.insert(report.getLeadEvaluation());

            for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
                individualEvaluationDAO.insert(individualEvaluation);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(Report report) {
        db.beginTransaction();

        try {
            db.update(TABLE, getContentValues(report), COLUMN_ID + " = ?", new String[]{ String.valueOf(report.getId()) });

            leadEvaluationDAO.update(report.getLeadEvaluation());

            for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
                individualEvaluationDAO.update(individualEvaluation);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void delete(Report report) {
        db.beginTransaction();

        leadEvaluationDAO.delete(report.getLeadEvaluation());

        for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
            individualEvaluationDAO.delete(individualEvaluation);
        }

        String query = "UPDATE " + TABLE
                + " SET "
                    + COLUMN_DELETED + " = 1"
                + " WHERE "
                    + COLUMN_ID + " = " + report.getId();

        try {
            db.execSQL(query);

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public Report select(long id) {
        Report report = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ? AND "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            report = getCursorValues(cursor);
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
                        + ConditionBuilder.parse(parameters, ReportDAO.class) + " AND "
                        + COLUMN_DELETED + " = ?";

            Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (ConditionException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Report> selectAll() {
        List<Report> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
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
