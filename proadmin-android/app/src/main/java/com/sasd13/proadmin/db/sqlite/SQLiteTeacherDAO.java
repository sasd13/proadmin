package com.sasd13.proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.db.TeacherDAO;

import java.util.ArrayList;
import java.util.List;

public class SQLiteTeacherDAO extends SQLiteTableDAO<Teacher> implements TeacherDAO {

    @Override
    protected ContentValues getContentValues(Teacher teacher) {
        ContentValues values = new ContentValues();

        values.put(TEACHER_NUMBER, teacher.getNumber());
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
        teacher.setNumber(cursor.getString(cursor.getColumnIndex(TEACHER_NUMBER)));
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
    public void delete(long id) {
        getDB().delete(TEACHER_TABLE_NAME, TEACHER_ID + " = ?", new String[]{String.valueOf(id)});
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
    public Teacher selectByNumber(String number) {
        Teacher teacher = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEACHER_TABLE_NAME
                        + " where " + TEACHER_NUMBER + " = ?", new String[]{String.valueOf(number)});

        if (cursor.moveToNext()) {
            teacher = getCursorValues(cursor);
        }
        cursor.close();

        return teacher;
    }

    @Override
    public List<Teacher> selectByEmail(String email) {
        List<Teacher> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEACHER_TABLE_NAME
                        + " where " + TEACHER_EMAIL + " = ?", new String[]{String.valueOf(email)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public List<Teacher> selectAll() {
        List<Teacher> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEACHER_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
