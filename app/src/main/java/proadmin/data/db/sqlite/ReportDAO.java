package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.id.ListIds;
import proadmin.content.ListReports;
import proadmin.content.MapNotes;
import proadmin.content.Note;
import proadmin.content.Report;
import proadmin.content.id.ReportId;
import proadmin.content.id.SquadId;
import proadmin.content.id.StudentId;

/**
 * Created by Samir on 02/04/2015.
 */
class ReportDAO extends AbstractTableDAO {

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

    public long insert(Report report) {
        return db.insert(REPORT_TABLE_NAME, null, getContentValues(report));
    }

    private ContentValues getContentValues(Report report) {
        ContentValues values = new ContentValues();

        values.put(REPORT_ID, report.getId().toString());
        values.put(REPORT_NUMBER_WEEK, report.getNumberWeek());
        values.put(REPORT_PLANNING_NOTE, report.getPlanningNote().getValue());
        values.put(REPORT_PLANNING_COMMENT, report.getPlanningComment());
        values.put(REPORT_COMMUNICATION_NOTE, report.getCommunicationNote().getValue());
        values.put(REPORT_COMMUNICATION_COMMENT, report.getCommunicationComment());
        values.put(REPORT_COMMENT, report.getComment());
        values.put(REPORT_SQUAD_ID, report.getSquadId().toString());
        values.put(REPORT_STUDENT_ID, report.getProjectLeadId().toString());

        return values;
    }

    public long update(Report report) {
        return db.update(REPORT_TABLE_NAME, getContentValues(report), REPORT_ID + " = ?", new String[]{report.getId().toString()});
    }

    public long delete(ReportId reportId) {
        return db.delete(REPORT_TABLE_NAME, REPORT_ID + " = ?", new String[]{reportId.toString()});
    }

    public Report select(ReportId reportId) {
        Report report = null;

        Cursor cursor = db.rawQuery(
                "select " + REPORT_NUMBER_WEEK
                        + ", " + REPORT_PLANNING_NOTE + ", " + REPORT_PLANNING_COMMENT
                        + ", " + REPORT_COMMUNICATION_NOTE + ", " + REPORT_COMMUNICATION_COMMENT
                        + ", " + REPORT_COMMENT
                        + ", " + REPORT_SQUAD_ID + ", " + REPORT_STUDENT_ID
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
            report.setSquadId(new SquadId(cursor.getString(6)));
            report.setProjectLeadId(new StudentId(cursor.getString(7)));
        }
        cursor.close();

        MapNotes mapNotes = SQLiteDAO.getInstance().selectNotesOfReport(reportId);
        try {
            report.setMapNotes(mapNotes);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return report;
    }

    public ListReports selectAllOfSquad(SquadId squadId) {
        ListReports listReports = new ListReports();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + REPORT_ID
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_SQUAD_ID + " = ?", new String[]{squadId.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new ReportId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listReports.add(select((ReportId) id));
        }

        return listReports;
    }

    public ListReports selectAllOfSquadAndStudent(SquadId squadId, StudentId studentId) {
        ListReports listReports = new ListReports();

        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + REPORT_ID
                        + " from " + REPORT_TABLE_NAME
                        + " where " + REPORT_SQUAD_ID + " = ? and " + REPORT_STUDENT_ID  + " = ?",
                new String[]{squadId.toString(), studentId.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new ReportId(cursor.getString(0)));
        }
        cursor.close();

        for (Object id : listIds) {
            listReports.add(select((ReportId) id));
        }

        return listReports;
    }

    public boolean contains(ReportId reportId) {
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
