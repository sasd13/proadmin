package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.db.RunningDAO;

import java.util.List;

public class SQLiteRunningDAO extends SQLiteEntityDAO<Running> implements RunningDAO {

    @Override
    protected ContentValues getContentValues(Running running) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_YEAR, running.getYear());
        values.put(COLUMN_TEACHER_ID, running.getTeacher().getId());
        values.put(COLUMN_PROJECT_ID, running.getProject().getId());

        return values;
    }

    @Override
    protected Running getCursorValues(Cursor cursor) {
        Running running = new Running();

        running.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));

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
        long id = executeInsert(TABLE, running);

        running.setId(id);

        return id;
    }

    @Override
    public void update(Running running) {
        executeUpdate(TABLE, running, COLUMN_ID, running.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(TABLE, COLUMN_ID, id);
    }

    @Override
    public Running select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Running> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }
}
