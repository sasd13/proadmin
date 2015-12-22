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

        values.put(RUNNING_YEAR, running.getYear());
        values.put(TEACHERS_TEACHER_ID, running.getTeacher().getId());
        values.put(PROJECTS_PROJECT_ID, running.getProject().getId());

        return values;
    }

    @Override
    protected Running getCursorValues(Cursor cursor) {
        Running running = new Running();

        running.setId(cursor.getLong(cursor.getColumnIndex(RUNNING_ID)));

        Teacher teacher = new Teacher();
        teacher.setId(cursor.getLong(cursor.getColumnIndex(TEACHERS_TEACHER_ID)));
        running.setTeacher(teacher);

        Project project = new Project();
        project.setId(cursor.getLong(cursor.getColumnIndex(PROJECTS_PROJECT_ID)));
        running.setProject(project);

        return running;
    }

    @Override
    public long insert(Running running) {
        long id = executeInsert(RUNNING_TABLE_NAME, running);

        running.setId(id);

        return id;
    }

    @Override
    public void update(Running running) {
        executeUpdate(RUNNING_TABLE_NAME, running, RUNNING_ID, running.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(RUNNING_TABLE_NAME, RUNNING_ID, id);
    }

    @Override
    public Running select(long id) {
        String query = "SELECT * FROM " + RUNNING_TABLE_NAME
                + " WHERE "
                    + RUNNING_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Running> selectAll() {
        String query = "SELECT * FROM " + RUNNING_TABLE_NAME;

        return executeSelectAll(query);
    }

    @Override
    public List<Running> selectByYear(int year) {
        String query = "SELECT * FROM " + RUNNING_TABLE_NAME
                + " WHERE "
                    + RUNNING_YEAR + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(year)});

        return executeSelectMultiResult(cursor);
    }

    @Override
    public List<Running> selectByTeacher(long teacherId) {
        String query = "SELECT * FROM " + RUNNING_TABLE_NAME
                + " WHERE "
                    + TEACHERS_TEACHER_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(teacherId)});

        return executeSelectMultiResult(cursor);
    }

    @Override
    public List<Running> selectByProject(long projectId) {
        String query = "SELECT * FROM " + RUNNING_TABLE_NAME
                + " WHERE "
                    + PROJECTS_PROJECT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(projectId)});

        return executeSelectMultiResult(cursor);
    }
}
