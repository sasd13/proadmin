package proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import proadmin.content.Id;
import proadmin.content.ListIds;

/**
 * Created by Samir on 02/04/2015.
 */
class StudentHasSquadDAO extends AbstractDAO {

    public static final String STUDENT_HAS_SQUAD_TABLE_NAME = "students_have_squads";

    public static final String STUDENT_HAS_SQUAD_STUDENT_ID = "student_id";
    public static final String STUDENT_HAS_SQUAD_SQUAD_ID = "squad_id";

    public StudentHasSquadDAO(SQLiteDatabase mDb) {
        super(mDb);
    }

    public long insert(Id studentId, Id squadId) {
        return mDb.insert(STUDENT_HAS_SQUAD_TABLE_NAME, null, getContentValues(studentId, squadId));
    }

    private ContentValues getContentValues(Id studentId, Id squadId) {
        ContentValues values = new ContentValues();

        values.put(STUDENT_HAS_SQUAD_STUDENT_ID, studentId.toString());
        values.put(STUDENT_HAS_SQUAD_SQUAD_ID, squadId.toString());

        return values;
    }

    public void deleteAllOfSquad(Id squadId) {
        mDb.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{squadId.toString()});
    }

    public void deleteAllOfStudent(Id studentId) {
        mDb.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_STUDENT_ID + " = ?", new String[]{studentId.toString()});
    }

    public void delete(Id studentId, Id squadId) {
        mDb.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_STUDENT_ID + " = ? and " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{studentId.toString(), squadId.toString()});
    }

    public ListIds selectAllOfSquad(Id squadId) {
        ListIds listIds = new ListIds();

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_SQUAD_STUDENT_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{squadId.toString()});

        if (cursor.moveToNext()) {
            listIds.add(new Id(cursor.getString(0)));
        }
        cursor.close();

        return listIds;
    }

    public ListIds selectAllOfStudent(Id studentId) {
        ListIds listIds = new ListIds();

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_SQUAD_SQUAD_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_STUDENT_ID + " = ?", new String[]{studentId.toString()});

        if (cursor.moveToNext()) {
            listIds.add(new Id(cursor.getString(0)));
        }
        cursor.close();

        return listIds;
    }

    public boolean contains(Id studentId, Id squadId) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + STUDENT_HAS_SQUAD_STUDENT_ID + ", " + STUDENT_HAS_SQUAD_SQUAD_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_STUDENT_ID + " = ? and " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{studentId.toString(), squadId.toString()});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
