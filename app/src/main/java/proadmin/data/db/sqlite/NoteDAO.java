package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.beans.running.IndividualEvaluation;

/**
 * Created by Samir on 02/04/2015.
 */
class NoteDAO extends AbstractTableDAO {

    public static final String NOTE_TABLE_NAME = "notes";

    public static final String NOTE_NOTE = "note";
    public static final String NOTE_REPORT_ID = "report_id";
    public static final String NOTE_STUDENT_ID = "student_id";

    public void insert(IndividualEvaluation individualEvaluation, String reportId, String studentId) {
        db.insert(NOTE_TABLE_NAME, null, getContentValues(individualEvaluation, reportId, studentId));
    }

    private ContentValues getContentValues(IndividualEvaluation individualEvaluation, String reportId, String studentId) {
        ContentValues values = new ContentValues();

        values.put(NOTE_NOTE, individualEvaluation.getValue());
        values.put(NOTE_REPORT_ID, reportId);
        values.put(NOTE_STUDENT_ID, studentId);

        return values;
    }

    public void update(IndividualEvaluation individualEvaluation, String reportId, String studentId) {
        db.update(NOTE_TABLE_NAME, getContentValues(individualEvaluation, reportId, studentId), NOTE_REPORT_ID + " = ? and " + NOTE_STUDENT_ID + " = ?",
                new String[]{reportId, studentId});
    }

    public long deleteAllOfReport(String reportId) {
        return db.delete(NOTE_TABLE_NAME, NOTE_REPORT_ID + " = ?", new String[]{reportId});
    }

    public MapNotes selectAllOfReport(String reportId) {
        MapNotes mapNotes = new MapNotes();

        Cursor cursor = db.rawQuery(
                "select " + NOTE_NOTE + ", " + NOTE_STUDENT_ID
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_REPORT_ID + " = ?", new String[]{reportId});

        while (cursor.moveToNext()) {
            mapNotes.put(new String(cursor.getString(1)), new IndividualEvaluation(cursor.getLong(0)));
        }
        cursor.close();

        return mapNotes;
    }

    public boolean contains(String reportId, String studentId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + NOTE_REPORT_ID + ", " + NOTE_STUDENT_ID
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_REPORT_ID + " = ? and " + NOTE_STUDENT_ID + " = ?",
                new String[]{reportId, studentId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
