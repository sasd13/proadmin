package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.db.StudentDAO;

import java.util.List;
import java.util.Map;

public class SQLiteStudentDAO extends SQLiteEntityDAO<Student> implements StudentDAO {

    @Override
    protected ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NUMBER, student.getNumber());
        values.put(COLUMN_ACADEMICLEVEL, String.valueOf(student.getAcademicLevel()));
        values.put(COLUMN_FIRSTNAME, student.getFirstName());
        values.put(COLUMN_LASTNAME, student.getLastName());
        values.put(COLUMN_EMAIL, student.getEmail());

        return values;
    }

    @Override
    protected Student getCursorValues(Cursor cursor) {
        Student student = new Student();

        student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        student.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
        student.setAcademicLevel(AcademicLevel.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_ACADEMICLEVEL))));
        student.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
        student.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
        student.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));

        return student;
    }

    @Override
    public long insert(Student student) {
        long id = executeInsert(TABLE, student);

        student.setId(id);

        return id;
    }

    @Override
    public void update(Student student) {
        executeUpdate(TABLE, student, COLUMN_ID, student.getId());
    }

    @Override
    public void delete(long id) {
        //Do nothing
    }

    @Override
    public Student select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Student> select(Map<String, String[]> map) {
        return null;
    }

    @Override
    public List<Student> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
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
