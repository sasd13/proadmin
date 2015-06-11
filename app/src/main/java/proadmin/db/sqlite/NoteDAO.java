package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.Id;
import proadmin.content.MapNotes;
import proadmin.content.Note;

/**
 * Created by Samir on 02/04/2015.
 */
class NoteDAO extends AbstractDAO {

    public static final String NOTE_TABLE_NAME = "notes";

    public static final String NOTE_NOTE = "note";
    public static final String NOTE_STUDENT_ID = "student_id";
    public static final String NOTE_REPORT_ID = "report_id";

    public void insert(MapNotes mapNotes, Id reportId) {
        Id[] tabStudentsIds = mapNotes.getKeys();

        for (Id studentId : tabStudentsIds) {
            db.insert(NOTE_TABLE_NAME, null, getContentValues(mapNotes.get(studentId), studentId, reportId));
        }
    }

    private ContentValues getContentValues(Note note, Id studentId, Id reportId) {
        ContentValues values = new ContentValues();

        values.put(NOTE_NOTE, note.getValue());
        values.put(NOTE_STUDENT_ID, studentId.toString());
        values.put(NOTE_REPORT_ID, reportId.toString());

        return values;
    }

    public void update(MapNotes mapNotes, Id reportId) {
        Id[] tabStudentsIds = mapNotes.getKeys();

        for (Id studentId : tabStudentsIds) {
            db.update(NOTE_TABLE_NAME, getContentValues(mapNotes.get(studentId), studentId, reportId), NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId.toString(), reportId.toString()});
        }
    }

    public void delete(Id studentId, Id reportId) {
        db.delete(NOTE_TABLE_NAME, NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId.toString(), reportId.toString()});
    }

    public void deleteAllOfStudent(Id studentId) {
        db.delete(NOTE_TABLE_NAME, NOTE_STUDENT_ID + " = ?", new String[]{studentId.toString()});
    }

    public void deleteAllOfReport(Id reportId) {
        db.delete(NOTE_TABLE_NAME, NOTE_REPORT_ID + " = ?", new String[]{reportId.toString()});
    }

    public Long select(Id studentId, Id reportId) {
        Long note = null;

        Cursor cursor = db.rawQuery(
                "select " + NOTE_NOTE
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId.toString(), reportId.toString()});

        if (cursor.moveToNext()) {
            note = cursor.getLong(0);
        }
        cursor.close();

        return note;
    }

    public MapNotes selectAllOfReport(Id reportId) {
        MapNotes mapNotes = new MapNotes();

        Cursor cursor = db.rawQuery(
                "select " + NOTE_NOTE + ", " + NOTE_STUDENT_ID
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_REPORT_ID + " = ?", new String[]{reportId.toString()});

        while (cursor.moveToNext()) {
            mapNotes.put(new Id(cursor.getString(1)), new Note(cursor.getLong(0)));
        }
        cursor.close();

        return mapNotes;
    }

    public boolean contains(Id studentId, Id reportId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + NOTE_REPORT_ID + ", " + NOTE_STUDENT_ID
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId.toString(), reportId.toString()});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
