package proadmin.db.sqlite;

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
    public static final String NOTE_STUDENT_ID = "student_id";
    public static final String NOTE_REPORT_ID = "report_id";

    public void insert(MapNotes mapNotes, ReportId reportId) {
        StudentId[] tabStudentsIds = mapNotes.getKeys();

        for (StudentId studentId : tabStudentsIds) {
            db.insert(NOTE_TABLE_NAME, null, getContentValues(mapNotes.get(studentId), studentId, reportId));
        }
    }

    private ContentValues getContentValues(Note note, StudentId studentId, ReportId reportId) {
        ContentValues values = new ContentValues();

        values.put(NOTE_NOTE, note.getValue());
        values.put(NOTE_STUDENT_ID, studentId.toString());
        values.put(NOTE_REPORT_ID, reportId.toString());

        return values;
    }

    public void update(MapNotes mapNotes, ReportId reportId) {
        StudentId[] tabStudentsIds = mapNotes.getKeys();

        for (StudentId studentId : tabStudentsIds) {
            db.update(NOTE_TABLE_NAME, getContentValues(mapNotes.get(studentId), studentId, reportId), NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId.toString(), reportId.toString()});
        }
    }

    public long delete(StudentId studentId, ReportId reportId) {
        return db.delete(NOTE_TABLE_NAME, NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId.toString(), reportId.toString()});
    }

    public long deleteAllOfStudent(StudentId studentId) {
        return db.delete(NOTE_TABLE_NAME, NOTE_STUDENT_ID + " = ?", new String[]{studentId.toString()});
    }

    public long deleteAllOfReport(ReportId reportId) {
        return db.delete(NOTE_TABLE_NAME, NOTE_REPORT_ID + " = ?", new String[]{reportId.toString()});
    }

    public Note select(StudentId studentId, ReportId reportId) {
        Note note = null;

        Cursor cursor = db.rawQuery(
                "select " + NOTE_NOTE
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId.toString(), reportId.toString()});

        if (cursor.moveToNext()) {
            note = new Note(cursor.getLong(0));
        }
        cursor.close();

        return note;
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
}
