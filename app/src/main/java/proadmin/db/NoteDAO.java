package proadmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import proadmin.content.MapNotes;

/**
 * Created by Samir on 02/04/2015.
 */
class NoteDAO extends AbstractDAO {

    public static final String NOTE_TABLE_NAME = "notes";

    public static final String NOTE_NOTE = "note";
    public static final String NOTE_STUDENT_ID = "student_id";
    public static final String NOTE_REPORT_ID = "report_id";

    public NoteDAO(Context context, SQLiteDatabase mDb) {
        super(context, mDb);
    }

    public void insert(MapNotes mapNotes, String reportId) {
        String[] tabStudentsIds = mapNotes.getKeys();

        for (String studentId : tabStudentsIds) {
            mDb.insert(NOTE_TABLE_NAME, null, getContentValues(mapNotes.get(studentId), studentId, reportId));
        }
    }

    private ContentValues getContentValues(long note, String studentId, String reportId) {
        ContentValues values = new ContentValues();

        values.put(NOTE_NOTE, note);
        values.put(NOTE_STUDENT_ID, studentId);
        values.put(NOTE_REPORT_ID, reportId);

        return values;
    }

    public void update(MapNotes mapNotes, String reportId) {
        String[] tabStudentsIds = mapNotes.getKeys();

        for (String studentId : tabStudentsIds) {
            mDb.update(NOTE_TABLE_NAME, getContentValues(mapNotes.get(studentId), studentId, reportId), NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId, reportId});
        }
    }

    public void delete(String studentId, String reportId) {
        mDb.delete(NOTE_TABLE_NAME, NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId, reportId});
    }

    public void deleteAllOfStudent(String studentId) {
        mDb.delete(NOTE_TABLE_NAME, NOTE_STUDENT_ID + " = ?", new String[]{studentId});
    }

    public void deleteAllOfReport(String reportId) {
        mDb.delete(NOTE_TABLE_NAME, NOTE_REPORT_ID + " = ?", new String[]{reportId});
    }

    public Long select(String studentId, String reportId) {
        Long note = null;

        Cursor cursor = mDb.rawQuery(
                "select " + NOTE_NOTE
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId, reportId});

        if (cursor.moveToNext()) {
            note = cursor.getLong(0);
        }
        cursor.close();

        return note;
    }

    public MapNotes selectAllOfReport(String reportId) {
        MapNotes mapNotes = new MapNotes();

        Cursor cursor = mDb.rawQuery(
                "select " + NOTE_NOTE + ", " + NOTE_STUDENT_ID
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_REPORT_ID + " = ?", new String[]{reportId});

        while (cursor.moveToNext()) {
            mapNotes.put(cursor.getString(1), cursor.getLong(0));
        }
        cursor.close();

        return mapNotes;
    }

    public boolean contains(String studentId, String reportId) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + NOTE_REPORT_ID + ", " + NOTE_STUDENT_ID
                        + " from " + NOTE_TABLE_NAME
                        + " where " + NOTE_STUDENT_ID + " = ? and " + NOTE_REPORT_ID + " = ?", new String[]{studentId, reportId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
