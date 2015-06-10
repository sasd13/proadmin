package proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.Id;
import proadmin.content.ListReports;
import proadmin.content.Note;
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

    public long insert(Report report, Id squadId) {
        return db.insert(REPORT_TABLE_NAME, null, getContentValues(report, squadId));
    }

    private ContentValues getContentValues(Report report, Id squadId) {
        ContentValues values = new ContentValues();

        values.put(REPORT_ID, report.getId().toString());
        values.put(REPORT_NUMBER_WEEK, report.getNumberWeek());
        values.put(REPORT_PLANNING_NOTE, report.getPlanningNote().getValue());
        values.put(REPORT_PLANNING_COMMENT, report.getPlanningComment());
        values.put(REPORT_COMMUNICATION_NOTE, report.getCommunicationNote().getValue());
        values.put(REPORT_COMMUNICATION_COMMENT, report.getCommunicationComment());
        values.put(REPORT_COMMENT, report.getComment());
        values.put(REPORT_SQUAD_ID, squadId.toString());
        values.put(REPORT_STUDENT_ID, report.getProjectLeadId().toString());

        return values;
    }

    public void update(Report report, Id squadId) {
        db.update(REPORT_TABLE_NAME, getContentValues(report, squadId), REPORT_ID + " = ?", new String[]{report.getId().toString()});
    }

    public void delete(Id reportId) {
        db.delete(REPORT_TABLE_NAME, REPORT_ID + " = ?", new String[]{reportId.toString()});
    }

    public void deleteAllOfSquad(Id squadId) {
        db.delete(REPORT_TABLE_NAME, REPORT_SQUAD_ID + " = ?", new String[]{squadId.toString()});
    }

    public Report select(Id reportId) {
        Report report = null;

        Cursor cursor = db.rawQuery(
                "select " + REPORT_NUMBER_WEEK
                        + ", " + REPORT_PLANNING_NOTE + ", " + REPORT_PLANNING_COMMENT
                        + ", " + REPORT_COMMUNICATION_NOTE + ", " + REPORT_COMMUNICATION_COMMENT
                        + ", " + REPORT_COMMENT
                        + ", " + REPORT_STUDENT_ID
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_ID + " = ?", new String[]{reportId.toString()});

        if (cursor.moveToNext()) {
            report = new Report();
            report.setId(reportId);
            report.setNumberWeek(cursor.getLong(0));
            report.setPlanningNote(new Note(cursor.getLong(1)));
            report.setPlanningComment(cursor.getString(2));
            report.setCommunicationNote(new Note(cursor.getLong(3)));
            report.setCommunicationComment(cursor.getString(4));
            report.setComment(cursor.getString(5));
            report.setProjectLeadId(new Id(cursor.getString(6)));
        }
        cursor.close();

        return report;
    }

    public ListReports selectAllOfSquad(Id squadId) {
        ListReports listReports = new ListReports();

        Cursor cursor = db.rawQuery(
                "select " + REPORT_ID + ", " + REPORT_NUMBER_WEEK
                        + ", " + REPORT_PLANNING_NOTE + ", " + REPORT_PLANNING_COMMENT
                        + ", " + REPORT_COMMUNICATION_NOTE + ", " + REPORT_COMMUNICATION_COMMENT
                        + ", " + REPORT_COMMENT
                        + ", " + REPORT_STUDENT_ID
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_SQUAD_ID + " = ?", new String[]{squadId.toString()});

        Report report;

        while (cursor.moveToNext()) {
            report = new Report();
            report.setId(new Id(cursor.getString(0)));
            report.setNumberWeek(cursor.getLong(1));
            report.setPlanningNote(new Note(cursor.getLong(2)));
            report.setPlanningComment(cursor.getString(3));
            report.setCommunicationNote(new Note(cursor.getLong(4)));
            report.setCommunicationComment(cursor.getString(5));
            report.setComment(cursor.getString(6));
            report.setProjectLeadId(new Id(cursor.getString(7)));

            listReports.add(report);
        }
        cursor.close();

        return listReports;
    }

    public boolean contains(Id reportId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + REPORT_ID
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_ID + " = ?", new String[]{reportId.toString()});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
