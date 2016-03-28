package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.StudentDAO;
import com.sasd13.proadmin.dao.util.SQLWhereClauseException;
import com.sasd13.proadmin.dao.util.SQLWhereClauseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteStudentDAO extends SQLiteEntityDAO<Student> implements StudentDAO {

    @Override
    protected ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NUMBER, student.getNumber());
        values.put(COLUMN_FIRSTNAME, student.getFirstName());
        values.put(COLUMN_LASTNAME, student.getLastName());
        values.put(COLUMN_EMAIL, student.getEmail());
        values.put(COLUMN_ACADEMICLEVEL, student.getAcademicLevel().getName());

        return values;
    }

    @Override
    protected Student getCursorValues(Cursor cursor) {
        Student student = new Student();

        student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        student.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
        student.setAcademicLevel(AcademicLevel.find(cursor.getString(cursor.getColumnIndex(COLUMN_ACADEMICLEVEL))));
        student.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
        student.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
        student.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));

        return student;
    }

    @Override
    public long insert(Student student) {
        return db.insert(TABLE, null, getContentValues(student));
    }

    @Override
    public void update(Student student) {
        db.update(TABLE, getContentValues(student), COLUMN_ID + " = ?", new String[]{ String.valueOf(student.getId()) });
    }

    @Override
    public void delete(Student student) {
        String query = "UPDATE " + TABLE
                + " SET "
                    + COLUMN_DELETED + " = 1"
                + " WHERE "
                    + COLUMN_ID + " = " + student.getId();

        try {
            db.execSQL(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student select(long id) {
        Student student = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ? AND "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            student = getCursorValues(cursor);
        }
        cursor.close();

        return student;
    }

    @Override
    public List<Student> select(Map<String, String[]> parameters) {
        List<Student> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                        + SQLWhereClauseParser.parse(parameters, StudentDAO.class) + " AND "
                        + COLUMN_DELETED + " = ?";

            Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (SQLWhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(0) });
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public void persist(Student student) {
        if (select(student.getId()) == null) {
            insert(student);
        } else {
            update(student);
        }
    }
}
