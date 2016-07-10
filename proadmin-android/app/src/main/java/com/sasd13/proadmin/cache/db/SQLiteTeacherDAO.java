package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.dao.condition.ConditionBuilder;
import com.sasd13.proadmin.dao.condition.ConditionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteTeacherDAO extends SQLiteEntityDAO<Teacher> implements TeacherDAO {

    @Override
    protected ContentValues getContentValues(Teacher teacher) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, teacher.getId());
        values.put(COLUMN_NUMBER, teacher.getNumber());
        values.put(COLUMN_FIRSTNAME, teacher.getFirstName());
        values.put(COLUMN_LASTNAME, teacher.getLastName());
        values.put(COLUMN_EMAIL, teacher.getEmail());

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

        return teacher;
    }

    @Override
    public long insert(Teacher teacher) {
        return db.insert(TABLE, null, getContentValues(teacher));
    }

    @Override
    public void update(Teacher teacher) {
        db.update(TABLE, getContentValues(teacher), COLUMN_ID + " = ?", new String[]{ String.valueOf(teacher.getId()) });
    }

    @Override
    public void delete(Teacher teacher) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + teacher.getId());

        try {
            db.execSQL(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher select(long id) {
        Teacher teacher = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            teacher = getCursorValues(cursor);
        }
        cursor.close();

        return teacher;
    }

    @Override
    public List<Teacher> select(Map<String, String[]> parameters) {
        List<Teacher> list = new ArrayList<>();

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, TeacherDAO.class));
            builder.append(" AND ");
            builder.append(COLUMN_DELETED + " = ?");

            Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (ConditionException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Teacher> selectAll() {
        List<Teacher> list = new ArrayList<>();

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
    public void persist(Teacher teacher) {
        if (select(teacher.getId()) == null) {
            insert(teacher);
        } else {
            update(teacher);
        }
    }
}
