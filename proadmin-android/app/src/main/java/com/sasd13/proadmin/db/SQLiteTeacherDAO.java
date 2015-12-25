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

        values.put(COLUMN_NUMBER, teacher.getNumber());
        values.put(COLUMN_FIRSTNAME, teacher.getFirstName());
        values.put(COLUMN_LASTNAME, teacher.getLastName());
        values.put(COLUMN_EMAIL, teacher.getEmail());
        values.put(COLUMN_PASSWORD, teacher.getPassword());

        return values;
    }

    @Override
    protected Teacher getCursorValues(Cursor cursor) {
        Teacher teacher = new Teacher();

        teacher.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        teacher.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
        teacher.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
        teacher.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
        teacher.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
        teacher.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));

        return teacher;
    }

    @Override
    public long insert(Teacher teacher) {
        long id = executeInsert(TABLE, teacher);

        teacher.setId(id);

        return id;
    }

    @Override
    public void update(Teacher teacher) {
        executeUpdate(TABLE, teacher, COLUMN_ID, teacher.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(TABLE, COLUMN_ID, id);
    }

    @Override
    public Teacher select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Teacher> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }
}
