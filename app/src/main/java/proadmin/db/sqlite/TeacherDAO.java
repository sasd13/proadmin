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
        ContentValues values = getContentValues(teacher);
        values.put(TEACHER_DELETED, false);

        return getDB().insert(TEACHER_TABLE_NAME, null, values);
    }

    @Override
    public void update(Teacher teacher) {
        getDB().update(TEACHER_TABLE_NAME, getContentValues(teacher), TEACHER_ID + " = ?", new String[]{String.valueOf(teacher.getId())});
    }

    @Override
    public void delete(long id) {
        Teacher teacher = select(id);

        try {
            ContentValues values = getContentValues(teacher);
            values.put(TEACHER_DELETED, true);

            getDB().update(TEACHER_TABLE_NAME, values, TEACHER_ID + " = ?", new String[]{String.valueOf(id)});
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher select(long id) {
        return select(id, false);
    }

    public Teacher select(long id, boolean includeDeleted) {
        Teacher teacher = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEACHER_TABLE_NAME
                        + " where " + TEACHER_ID + " = ?" + getConditionDeleted(includeDeleted), new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            teacher = getCursorValues(cursor);
        }
        cursor.close();

        return teacher;
    }

    private String getConditionDeleted(boolean includeDeleted) {
        return (includeDeleted) ? "" : " and " + TEACHER_DELETED + " = 0";
    }

    @Override
    public Teacher selectByEmail(String email) {
        return selectByEmail(email, false);
    }

    public Teacher selectByEmail(String email, boolean includeDeleted) {
        Teacher teacher = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEACHER_TABLE_NAME
                        + " where " + TEACHER_EMAIL + " = ?" + getConditionDeleted(includeDeleted), new String[]{String.valueOf(email)});

        if (cursor.moveToNext()) {
            teacher = getCursorValues(cursor);
        }
        cursor.close();

        return teacher;
    }
}
