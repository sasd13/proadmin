package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.ListIds;
import proadmin.content.id.SquadId;
import proadmin.content.id.StudentId;

/**
 * Created by Samir on 02/04/2015.
 */
class StudentHasSquadDAO extends AbstractDAO {

    public static final String STUDENT_HAS_SQUAD_TABLE_NAME = "students_have_squads";

    public static final String STUDENT_HAS_SQUAD_STUDENT_ID = "student_id";
    public static final String STUDENT_HAS_SQUAD_SQUAD_ID = "squad_id";

    public long insert(StudentId studentId, SquadId squadId) {
        return db.insert(STUDENT_HAS_SQUAD_TABLE_NAME, null, getContentValues(studentId, squadId));
    }

    private ContentValues getContentValues(StudentId studentId, SquadId squadId) {
        ContentValues values = new ContentValues();

        values.put(STUDENT_HAS_SQUAD_STUDENT_ID, studentId.toString());
        values.put(STUDENT_HAS_SQUAD_SQUAD_ID, squadId.toString());

        return values;
    }

    public long deleteAllOfSquad(SquadId squadId) {
        return db.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{squadId.toString()});
    }

    public long deleteAllOfStudent(StudentId studentId) {
        return db.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_STUDENT_ID + " = ?", new String[]{studentId.toString()});
    }

    public long delete(StudentId studentId, SquadId squadId) {
        return db.delete(STUDENT_HAS_SQUAD_TABLE_NAME, STUDENT_HAS_SQUAD_STUDENT_ID + " = ? and " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{studentId.toString(), squadId.toString()});
    }

    public ListIds selectAllOfSquad(SquadId squadId) {
        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + STUDENT_HAS_SQUAD_STUDENT_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_SQUAD_ID + " = ?", new String[]{squadId.toString()});

        if (cursor.moveToNext()) {
            listIds.add(new StudentId(cursor.getString(0)));
        }
        cursor.close();

        return listIds;
    }

    public ListIds selectAllOfStudent(StudentId studentId) {
        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + STUDENT_HAS_SQUAD_SQUAD_ID
                        + " from " + STUDENT_HAS_SQUAD_TABLE_NAME
                        + " where " + STUDENT_HAS_SQUAD_STUDENT_ID + " = ?", new String[]{studentId.toString()});

        if (cursor.moveToNext()) {
            listIds.add(new SquadId(cursor.getString(0)));
        }
        cursor.close();

        return listIds;
    }
}
