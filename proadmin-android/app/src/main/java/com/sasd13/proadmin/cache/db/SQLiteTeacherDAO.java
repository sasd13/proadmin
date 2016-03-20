package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.dao.util.WhereClauseException;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

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
        db.update(TABLE, getContentValues(teacher), COLUMN_ID + " = ?", new String[]{String.valueOf(teacher.getId())});
    }

    @Override
    public void delete(long id) {
        String query = "UPDATE " + TABLE
                + " SET "
                + COLUMN_DELETED + " = 1"
                + " WHERE "
                + COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher select(long id) {
        Teacher teacher = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                + COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                teacher = getCursorValues(cursor);
            }
        }
        cursor.close();

        return teacher;
    }

    @Override
    public List<Teacher> select(Map<String, String[]> parameters) {
        List<Teacher> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                        + WhereClauseParser.parse(TeacherDAO.class, parameters);

            Cursor cursor = db.rawQuery(query, null);
            fillListWithCursor(list, cursor);
            cursor.close();
        } catch (WhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void fillListWithCursor(List<Teacher> list, Cursor cursor) {
        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                list.add(getCursorValues(cursor));
            }
        }
    }

    @Override
    public List<Teacher> selectAll() {
        List<Teacher> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);
        fillListWithCursor(list, cursor);
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
