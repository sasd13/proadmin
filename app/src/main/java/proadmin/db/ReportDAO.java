package proadmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import proadmin.content.ListReports;
import proadmin.content.MapNotes;
import proadmin.content.Report;

/**
 * Created by Samir on 02/04/2015.
 */
class ReportDAO extends AbstractDAO {

    public static final String REPORT_TABLE_NAME = "reports";

    public static final String REPORT_ID = "report_id";
    public static final String REPORT_NUMBER_WEEK = "number_week";
    public static final String REPORT_PLANNING_NOTE = "planning_note";
    public static final String REPORT_PLANNING_COMMENT = "planning_comment";
    public static final String REPORT_COMMUNICATION_NOTE = "communication_note";
    public static final String REPORT_COMMUNICATION_COMMENT = "communication_comment";
    public static final String REPORT_COMMENT = "report_comment";
    public static final String REPORT_SQUAD_ID = "squad_id";
    public static final String REPORT_STUDENT_ID = "student_id";

    public ReportDAO(Context context, SQLiteDatabase mDb) {
        super(context, mDb);
    }

    public long insert(Report report, String squadId) {
        return mDb.insert(REPORT_TABLE_NAME, null, getContentValues(report, squadId));
    }

    private ContentValues getContentValues(Report report, String squadId) {
        ContentValues values = new ContentValues();

        values.put(REPORT_ID, report.getId());
        values.put(REPORT_NUMBER_WEEK, report.getNumberWeek());
        values.put(REPORT_PLANNING_NOTE, report.getPlanningNote());
        values.put(REPORT_PLANNING_COMMENT, report.getPlanningComment());
        values.put(REPORT_COMMUNICATION_NOTE, report.getCommunicationNote());
        values.put(REPORT_COMMUNICATION_COMMENT, report.getCommunicationComment());
        values.put(REPORT_COMMENT, report.getComment());
        values.put(REPORT_SQUAD_ID, squadId);
        values.put(REPORT_STUDENT_ID, report.getProjectLeadId());

        return values;
    }

    public void update(Report report, String squadId) {
        mDb.update(REPORT_TABLE_NAME, getContentValues(report, squadId), REPORT_ID + " = ?", new String[]{report.getId()});
    }

    public void delete(String reportId) {
        mDb.delete(REPORT_TABLE_NAME, REPORT_ID + " = ?", new String[]{reportId});
    }

    public void deleteAllOfSquad(String squadId) {
        mDb.delete(REPORT_TABLE_NAME, REPORT_SQUAD_ID + " = ?", new String[]{squadId});
    }

    public Report select(String reportId) {
        Report report = null;

        Cursor cursor = mDb.rawQuery(
                "select " + REPORT_NUMBER_WEEK
                        + ", " + REPORT_PLANNING_NOTE + ", " + REPORT_PLANNING_COMMENT
                        + ", " + REPORT_COMMUNICATION_NOTE + ", " + REPORT_COMMUNICATION_COMMENT
                        + ", " + REPORT_COMMENT
                        + ", " + REPORT_STUDENT_ID
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_ID + " = ?", new String[]{reportId});

        if (cursor.moveToNext()) {
            report = new Report();
            report.setId(reportId);
            report.setNumberWeek(cursor.getLong(0));
            report.setPlanningNote(cursor.getLong(1));
            report.setPlanningComment(cursor.getString(2));
            report.setCommunicationNote(cursor.getLong(3));
            report.setCommunicationComment(cursor.getString(4));
            report.setComment(cursor.getString(5));
            report.setProjectLeadId(cursor.getString(6));
        }
        cursor.close();

        return report;
    }

    public ListReports selectAllOfSquad(String squadId) {
        ListReports listReports = new ListReports();

        Cursor cursor = mDb.rawQuery(
                "select " + REPORT_ID + ", " + REPORT_NUMBER_WEEK
                        + ", " + REPORT_PLANNING_NOTE + ", " + REPORT_PLANNING_COMMENT
                        + ", " + REPORT_COMMUNICATION_NOTE + ", " + REPORT_COMMUNICATION_COMMENT
                        + ", " + REPORT_COMMENT
                        + ", " + REPORT_STUDENT_ID
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_SQUAD_ID + " = ?", new String[]{squadId});

        Report report;

        while (cursor.moveToNext()) {
            report = new Report();
            report.setId(cursor.getString(0));
            report.setNumberWeek(cursor.getLong(1));
            report.setPlanningNote(cursor.getLong(2));
            report.setPlanningComment(cursor.getString(3));
            report.setCommunicationNote(cursor.getLong(4));
            report.setCommunicationComment(cursor.getString(5));
            report.setComment(cursor.getString(6));
            report.setProjectLeadId(cursor.getString(7));

            listReports.add(report);
        }
        cursor.close();

        return listReports;
    }

    public boolean contains(String reportId) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + REPORT_ID
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_ID + " = ?", new String[]{reportId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
