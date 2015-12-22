package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.db.TeacherDAO;

import java.util.List;

public class SQLiteTeacherDAO extends SQLiteEntityDAO<Teacher> implements TeacherDAO {

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
        long id = executeInsert(TEACHER_TABLE_NAME, teacher);

        teacher.setId(id);

        return id;
    }

    @Override
    public void update(Teacher teacher) {
        executeUpdate(TEACHER_TABLE_NAME, teacher, TEACHER_ID, teacher.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(TEACHER_TABLE_NAME, TEACHER_ID, id);
    }

    @Override
    public Teacher select(long id) {
        String query = "select * from " + TEACHER_TABLE_NAME
                + " where "
                    + TEACHER_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Teacher> selectAll() {
        String query = "select * from " + TEACHER_TABLE_NAME;

        return executeSelectAll(query);
    }

    @Override
    public Teacher selectByNumber(String number) {
        String query = "select * from " + TEACHER_TABLE_NAME
                + " where "
                    + TEACHER_NUMBER + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(number)});

        return executeSelectSingleResult(cursor);
    }

    @Override
    public List<Teacher> selectByEmail(String email) {
        String query = "select * from " + TEACHER_TABLE_NAME
                + " where "
                    + TEACHER_EMAIL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(email)});

        return executeSelectMultiResult(cursor);
    }
}
