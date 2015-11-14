package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Samir on 02/04/2015.
 */
class StudentHasSquadDAO extends AbstractTableDAO {

    public static final String STUDENT_HAS_SQUAD_TABLE_NAME = "students_have_squads";

    public static final String STUDENT_HAS_SQUAD_STUDENT_ID = StudentDAO.STUDENT_ID;
    public static final String STUDENT_HAS_SQUAD_SQUAD_ID = SquadDAO.SQUAD_ID;

    public long insert(String studentId, String squadId) {
        return db.insert(STUDENT_HAS_SQUAD_TABLE_NAME, null, getContentValues(studentId, squadId));
    }

    private ContentValues getContentValues(String studentId, String squadId) {
        ContentValues values = new ContentValues();

        values.put(STUDENT_HAS_SQUAD_STUDENT_ID, studentId.toString());
        values.put(STUDENT_HAS_SQUAD_SQUAD_ID, squadId.toString());

        return values;
    }

    public long delete(String studentId, String squadId) {
        return db.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_STUDENT_ID + " = ? and " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{studentId.toString(), squadId.toString()});
    }

    public ListIds selectAllOfSquad(String squadId) {
        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + STUDENT_HAS_SQUAD_STUDENT_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{squadId.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new String(cursor.getString(0)));
        }
        cursor.close();

        return listIds;
    }

    public boolean contains(String studentId, String squadId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + STUDENT_HAS_SQUAD_STUDENT_ID + ", " + STUDENT_HAS_SQUAD_SQUAD_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_STUDENT_ID + " = ? and " + STUDENT_HAS_SQUAD_SQUAD_ID,
                new String[]{studentId.toString(), squadId.toString()});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
