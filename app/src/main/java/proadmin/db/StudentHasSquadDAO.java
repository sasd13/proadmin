package proadmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import proadmin.content.ListIds;

/**
 * Created by Samir on 02/04/2015.
 */
class StudentHasSquadDAO extends AbstractDAO {

    public static final String STUDENT_HAS_SQUAD_TABLE_NAME = "students_have_squads";

    public static final String STUDENT_HAS_SQUAD_STUDENT_ID = "student_id";
    public static final String STUDENT_HAS_SQUAD_SQUAD_ID = "squad_id";

    public StudentHasSquadDAO(Context context, SQLiteDatabase mDb) {
        super(context, mDb);
    }

    public long insert(String studentId,String squadId) {
        return mDb.insert(STUDENT_HAS_SQUAD_TABLE_NAME, null, getContentValues(studentId, squadId));
    }

    private ContentValues getContentValues(String studentId, String squadId) {
        ContentValues values = new ContentValues();

        values.put(STUDENT_HAS_SQUAD_STUDENT_ID, studentId);
        values.put(STUDENT_HAS_SQUAD_SQUAD_ID, squadId);

        return values;
    }

    public void deleteAllOfSquad(String squadId) {
        mDb.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{squadId});
    }

    public void deleteAllOfStudent(String studentId) {
        mDb.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_STUDENT_ID + " = ?", new String[]{studentId});
    }

    public void delete(String studentId, String squadId) {
        mDb.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_STUDENT_ID + " = ? and " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{studentId, squadId});
    }

    public ListIds selectAllOfSquad(String squadId) {
        ListIds listIds = new ListIds();

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_SQUAD_STUDENT_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{squadId});

        if (cursor.moveToNext()) {
            listIds.add(cursor.getString(0));
        }
        cursor.close();

        return listIds;
    }

    public ListIds selectAllOfStudent(String studentId) {
        ListIds listIds = new ListIds();

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_SQUAD_SQUAD_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_STUDENT_ID + " = ?", new String[]{studentId});

        if (cursor.moveToNext()) {
            listIds.add(cursor.getString(0));
        }
        cursor.close();

        return listIds;
    }

    public boolean contains(String studentId, String squadId) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_SQUAD_STUDENT_ID + ", " + STUDENT_HAS_SQUAD_SQUAD_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_STUDENT_ID + " = ? and " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{studentId, squadId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
