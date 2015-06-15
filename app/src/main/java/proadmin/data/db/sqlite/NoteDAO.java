package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.MapNotes;
import proadmin.content.Note;
import proadmin.content.id.ReportId;
import proadmin.content.id.StudentId;

/**
 * Created by Samir on 02/04/2015.
 */
class NoteDAO extends AbstractTableDAO {

    public static final String NOTE_TABLE_NAME = "notes";

    public static final String NOTE_NOTE = "note";
    public static final String NOTE_REPORT_ID = "report_id";
    public static final String NOTE_STUDENT_ID = "student_id";

    public void insert(Note note, ReportId reportId, StudentId studentId) {
        db.insert(NOTE_TABLE_NAME, null, getContentValues(note, reportId, studentId));
    }

    private ContentValues getContentValues(Note note, ReportId reportId, StudentId studentId) {
        ContentValues values = new ContentValues();

        values.put(NOTE_NOTE, note.getValue());
        values.put(NOTE_REPORT_ID, reportId.toString());
        values.put(NOTE_STUDENT_ID, studentId.toString());

        return values;
    }

    public void update(Note note, ReportId reportId, StudentId studentId) {
        db.update(NOTE_TABLE_NAME, getContentValues(note, reportId, studentId), NOTE_REPORT_ID + " = ? and " + NOTE_STUDENT_ID + " = ?",
                new String[]{reportId.toString(), studentId.toString()});
    }

    public long deleteAllOfReport(ReportId reportId) {
        return db.delete(NOTE_TABLE_NAME, NOTE_REPORT_ID + " = ?", new String[]{reportId.toString()});
    }

    public MapNotes selectAllOfReport(ReportId reportId) {
        MapNotes mapNotes = new MapNotes();

        Cursor cursor = db.rawQuery(
                "select " + NOTE_NOTE + ", " + NOTE_STUDENT_ID
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_REPORT_ID + " = ?", new String[]{reportId.toString()});

        while (cursor.moveToNext()) {
            mapNotes.put(new StudentId(cursor.getString(1)), new Note(cursor.getLong(0)));
        }
        cursor.close();

        return mapNotes;
    }

    public boolean contains(ReportId reportId, StudentId studentId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + NOTE_REPORT_ID + ", " + NOTE_STUDENT_ID
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_REPORT_ID + " = ? and " + NOTE_STUDENT_ID + " = ?",
                new String[]{reportId.toString(), studentId.toString()});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
