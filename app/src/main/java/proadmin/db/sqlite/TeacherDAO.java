package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.bean.member.Teacher;
import proadmin.db.TeacherTableAccessor;

public class TeacherDAO extends SQLiteTableDAO<Teacher> implements TeacherTableAccessor {

    @Override
    protected ContentValues getContentValues(Teacher teacher) {
        ContentValues values = new ContentValues();

        //values.put(TEACHER_ID, teacher.getId()); //autoincrement
        values.put(TEACHER_FIRSTNAME, teacher.getFirstName());
        values.put(TEACHER_LASTNAME, teacher.getLastName());
        values.put(TEACHER_EMAIL, teacher.getEmail());
        values.put(TEACHER_PASSWORD, teacher.getPassword());

        return values;
    }

    @Override
    protected Teacher getCursorValues(Cursor cursor) {
        Teacher teacher = new Teacher();

        teacher.setId(cursor.getLong(cursor.getColumnIndex(TEACHER_ID)));
        teacher.setFirstName(cursor.getString(cursor.getColumnIndex(TEACHER_FIRSTNAME)));
        teacher.setLastName(cursor.getString(cursor.getColumnIndex(TEACHER_LASTNAME)));
        teacher.setEmail(cursor.getString(cursor.getColumnIndex(TEACHER_EMAIL)));
        teacher.setPassword(cursor.getString(cursor.getColumnIndex(TEACHER_PASSWORD)));

        return teacher;
    }

    @Override
    public long insert(Teacher teacher) {
        return getDB().insert(TEACHER_TABLE_NAME, null, getContentValues(teacher));
    }

    @Override
    public void update(Teacher teacher) {
        getDB().update(TEACHER_TABLE_NAME, getContentValues(teacher), TEACHER_ID + " = ?", new String[]{String.valueOf(teacher.getId())});
    }

    @Override
    public Teacher select(long id) {
        Teacher teacher = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEACHER_TABLE_NAME
                        + " where " + TEACHER_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            teacher = getCursorValues(cursor);
        }
        cursor.close();

        return teacher;
    }

    @Override
    public Teacher selectByEmail(String email) {
        Teacher teacher = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEACHER_TABLE_NAME
                        + " where " + TEACHER_EMAIL + " = ?", new String[]{String.valueOf(email)});

        if (cursor.moveToNext()) {
            teacher = getCursorValues(cursor);
        }
        cursor.close();

        return teacher;
    }
}
