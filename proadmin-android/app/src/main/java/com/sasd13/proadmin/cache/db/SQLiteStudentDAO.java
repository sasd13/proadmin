package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.javaex.db.condition.ConditionBuilder;
import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.StudentDAO;
import com.sasd13.proadmin.dao.condition.StudentConditionExpression;

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
        student.setAcademicLevel(EnumAcademicLevel.find(cursor.getString(cursor.getColumnIndex(COLUMN_ACADEMICLEVEL))));
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
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + student.getId());

        try {
            db.execSQL(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student select(long id) {
        Student student = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
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
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, StudentConditionExpression.class));
            builder.append(" AND ");
            builder.append(COLUMN_DELETED + " = ?");

            Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (ConditionBuilderException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> list = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
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
