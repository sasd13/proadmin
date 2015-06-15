package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.Teacher;
import proadmin.content.id.TeacherId;

/**
 * Created by Samir on 02/04/2015.
 */
class TeacherDAO extends AbstractTableDAO {

    public static final String TEACHER_TABLE_NAME = "teachers";

    public static final String TEACHER_ID = "teacher_id";
    public static final String TEACHER_FIRSTNAME = "first_name";
    public static final String TEACHER_LASTNAME = "last_name";
    public static final String TEACHER_EMAIL = "email";
    public static final String TEACHER_PASSWORD = "pwd";

    public long insert(Teacher teacher) {
        return db.insert(TEACHER_TABLE_NAME, null, getContentValues(teacher));
    }

    private ContentValues getContentValues(Teacher teacher) {
        ContentValues values = new ContentValues();

        values.put(TEACHER_ID, teacher.getId().toString());
        values.put(TEACHER_FIRSTNAME, teacher.getFirstName());
        values.put(TEACHER_LASTNAME, teacher.getLastName());
        values.put(TEACHER_EMAIL, teacher.getEmail());
        values.put(TEACHER_PASSWORD, teacher.getPassword());

        return values;
    }

    public long update(Teacher teacher) {
        return db.update(TEACHER_TABLE_NAME, getContentValues(teacher), TEACHER_ID + " = ?", new String[]{teacher.getId().toString()});
    }

    public long delete(TeacherId teacherId) {
        return db.delete(TEACHER_TABLE_NAME, TEACHER_ID + " = ?", new String[]{teacherId.toString()});
    }

    public Teacher select(TeacherId teacherId) {
        Teacher teacher = null;

        Cursor cursor = db.rawQuery(
                "select " + TEACHER_FIRSTNAME + ", " + TEACHER_LASTNAME + ", " + TEACHER_EMAIL + ", " + TEACHER_PASSWORD
                        + " from " + TEACHER_TABLE_NAME
                        + " where " + TEACHER_ID + " = ?", new String[]{teacherId.toString()});

        if (cursor.moveToNext()) {
            teacher = new Teacher();
            teacher.setId(teacherId);
            teacher.setFirstName(cursor.getString(0));
            teacher.setLastName(cursor.getString(1));
            teacher.setEmail(cursor.getString(2));
            teacher.setPassword(cursor.getString(3));
        }
        cursor.close();

        return teacher;
    }

    public Teacher select(String email) {
        Teacher teacher = null;

        Cursor cursor = db.rawQuery(
                "select " + TEACHER_ID + ", " + TEACHER_FIRSTNAME + ", " + TEACHER_LASTNAME + ", " + TEACHER_PASSWORD
                        + " from " + TEACHER_TABLE_NAME
                        + " where " + TEACHER_EMAIL + " = ?", new String[]{email});

        if (cursor.moveToNext()) {
            teacher = new Teacher();
            teacher.setId(new TeacherId(cursor.getString(0)));
            teacher.setFirstName(cursor.getString(1));
            teacher.setLastName(cursor.getString(2));
            teacher.setEmail(email);
            teacher.setPassword(cursor.getString(3));
        }
        cursor.close();

        return teacher;
    }
}
