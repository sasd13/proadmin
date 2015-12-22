package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.db.StudentDAO;

import java.util.ArrayList;
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
        return getDB().insert(STUDENT_TABLE_NAME, null, getContentValues(student));
    }

    @Override
    public void update(Student student) {
        getDB().update(STUDENT_TABLE_NAME, getContentValues(student), STUDENT_ID + " = ?", new String[]{String.valueOf(student.getId())});
    }

    @Override
    public void delete(long id) {
        getDB().delete(STUDENT_TABLE_NAME, STUDENT_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Student select(long id) {
        Student student = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            student = getCursorValues(cursor);
        }
        cursor.close();

        return student;
    }

    @Override
    public Student selectByNumber(String number) {
        Student student = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_NUMBER + " = ?", new String[]{String.valueOf(number)});

        if (cursor.moveToNext()) {
            student = getCursorValues(cursor);
        }
        cursor.close();

        return student;
    }

    @Override
    public List<Student> selectByAcademicLevel(AcademicLevel academicLevel) {
        List<Student> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_ACADEMICLEVEL + " = ?", new String[]{String.valueOf(academicLevel)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public List<Student> selectByEmail(String email) {
        List<Student> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + STUDENT_TABLE_NAME
                        + " where " + STUDENT_EMAIL + " = ?", new String[]{String.valueOf(email)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + STUDENT_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
