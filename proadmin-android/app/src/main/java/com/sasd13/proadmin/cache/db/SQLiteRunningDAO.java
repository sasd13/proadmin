package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.RunningDAO;
import com.sasd13.proadmin.dao.util.SQLWhereClauseException;
import com.sasd13.proadmin.dao.util.SQLWhereClauseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteRunningDAO extends SQLiteEntityDAO<Running> implements RunningDAO {

    @Override
    protected ContentValues getContentValues(Running running) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, running.getId());
        values.put(COLUMN_YEAR, running.getYear());
        values.put(COLUMN_TEACHER_ID, running.getTeacher().getId());
        values.put(COLUMN_PROJECT_ID, running.getProject().getId());

        return values;
    }

    @Override
    protected Running getCursorValues(Cursor cursor) {
        Running running = new Running();

        running.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        running.setYear(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)));

        Teacher teacher = new Teacher();
        teacher.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_TEACHER_ID)));
        running.setTeacher(teacher);

        Project project = new Project();
        project.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_PROJECT_ID)));
        running.setProject(project);

        return running;
    }

    @Override
    public long insert(Running running) {
        return db.insert(TABLE, null, getContentValues(running));
    }

    @Override
    public void update(Running running) {
        db.update(TABLE, getContentValues(running), COLUMN_ID + " = ?", new String[]{ String.valueOf(running.getId()) });
    }

    @Override
    public void delete(Running running) {
        String query = "UPDATE " + TABLE
                + " SET "
                    + COLUMN_DELETED + " = 1"
                + " WHERE "
                    + COLUMN_ID + " = " + running.getId();

        try {
            db.execSQL(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Running select(long id) {
        Running running = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ? AND "
                    + COLUMN_DELETED + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            running = getCursorValues(cursor);
        }
        cursor.close();

        return running;
    }

    @Override
    public List<Running> select(Map<String, String[]> parameters) {
        List<Running> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                        + SQLWhereClauseParser.parse(RunningDAO.class, parameters) + " AND "
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
    public List<Running> selectAll() {
        List<Running> list = new ArrayList<>();

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
    public void persist(Running running) {
        if (select(running.getId()) == null) {
            insert(running);
        } else {
            update(running);
        }
    }
}
