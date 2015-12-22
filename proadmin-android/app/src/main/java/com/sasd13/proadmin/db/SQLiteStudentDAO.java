package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.db.StudentDAO;

import java.util.List;

public class SQLiteStudentDAO extends SQLiteEntityDAO<Student> implements StudentDAO {

    @Override
    protected ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();

        values.put(STUDENT_NUMBER, student.getNumber());
        values.put(STUDENT_ACADEMICLEVEL, String.valueOf(student.getAcademicLevel()));
        values.put(STUDENT_FIRSTNAME, student.getFirstName());
        values.put(STUDENT_LASTNAME, student.getLastName());
        values.put(STUDENT_EMAIL, student.getEmail());

        return values;
    }

    @Override
    protected Student getCursorValues(Cursor cursor) {
        Student student = new Student();

        student.setId(cursor.getLong(cursor.getColumnIndex(STUDENT_ID)));
        student.setNumber(cursor.getString(cursor.getColumnIndex(STUDENT_NUMBER)));
        student.setAcademicLevel(AcademicLevel.valueOf(cursor.getString(cursor.getColumnIndex(STUDENT_ACADEMICLEVEL))));
        student.setFirstName(cursor.getString(cursor.getColumnIndex(STUDENT_FIRSTNAME)));
        student.setLastName(cursor.getString(cursor.getColumnIndex(STUDENT_LASTNAME)));
        student.setEmail(cursor.getString(cursor.getColumnIndex(STUDENT_EMAIL)));

        return student;
    }

    @Override
    public long insert(Student student) {
        long id = executeInsert(STUDENT_TABLE_NAME, student);

        student.setId(id);

        return id;
    }

    @Override
    public void update(Student student) {
        executeUpdate(STUDENT_TABLE_NAME, student, STUDENT_ID, student.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(STUDENT_TABLE_NAME, STUDENT_ID, id);
    }

    @Override
    public Student select(long id) {
        String query = "SELECT * FROM " + STUDENT_TABLE_NAME
                + " WHERE "
                    + STUDENT_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Student> selectAll() {
        String query = "SELECT * FROM " + STUDENT_TABLE_NAME;

        return executeSelectAll(query);
    }

    @Override
    public Student selectByNumber(String number) {
        String query = "SELECT * FROM " + STUDENT_TABLE_NAME
                + " WHERE "
                    + STUDENT_NUMBER + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(number)});

        return executeSelectSingleResult(cursor);
    }

    @Override
    public List<Student> selectByAcademicLevel(AcademicLevel academicLevel) {
        String query = "SELECT * FROM " + STUDENT_TABLE_NAME
                + " WHERE "
                    + STUDENT_ACADEMICLEVEL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(academicLevel)});

        return executeSelectMultiResult(cursor);
    }

    @Override
    public List<Student> selectByEmail(String email) {
        String query = "SELECT * FROM " + STUDENT_TABLE_NAME
                + " WHERE "
                    + STUDENT_EMAIL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(email)});

        return executeSelectMultiResult(cursor);
    }
}
