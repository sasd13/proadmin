package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.ProjectDAO;

import java.util.List;

public class SQLiteProjectDAO extends SQLiteEntityDAO<Project> implements ProjectDAO {

    @Override
    protected ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();

        values.put(PROJECT_CODE, project.getCode());
        values.put(PROJECT_ACADEMICLEVEL, String.valueOf(project.getAcademicLevel()));
        values.put(PROJECT_TITLE, project.getTitle());
        values.put(PROJECT_DESCRIPTION, project.getDescription());

        return values;
    }

    @Override
    protected Project getCursorValues(Cursor cursor) {
        Project project = new Project();

        project.setId(cursor.getLong(cursor.getColumnIndex(PROJECT_ID)));
        project.setCode(cursor.getString(cursor.getColumnIndex(PROJECT_CODE)));
        project.setAcademicLevel(AcademicLevel.valueOf(cursor.getString(cursor.getColumnIndex(PROJECT_ACADEMICLEVEL))));
        project.setTitle(cursor.getString(cursor.getColumnIndex(PROJECT_TITLE)));
        project.setDescription(cursor.getString(cursor.getColumnIndex(PROJECT_DESCRIPTION)));

        return project;
    }

    @Override
    public long insert(Project project) {
        long id = executeInsert(PROJECT_TABLE_NAME, project);

        project.setId(id);

        return id;
    }

    @Override
    public void update(Project project) {
        executeUpdate(PROJECT_TABLE_NAME, project, PROJECT_ID, project.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(PROJECT_TABLE_NAME, PROJECT_ID, id);
    }

    @Override
    public Project select(long id) {
        String query = "select * from " + PROJECT_TABLE_NAME
                + " where "
                    + PROJECT_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Project> selectAll() {
        String query = "select * from " + PROJECT_TABLE_NAME;

        return executeSelectAll(query);
    }

    @Override
    public Project selectByCode(String code) {
        String query = "select * from " + PROJECT_TABLE_NAME
                + " where "
                    + PROJECT_CODE + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(code)});

        return executeSelectSingleResult(cursor);
    }

    @Override
    public List<Project> selectByAcademicLevel(AcademicLevel academicLevel) {
        String query = "select * from " + PROJECT_TABLE_NAME
                + " where "
                    + PROJECT_ACADEMICLEVEL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(academicLevel)});

        return executeSelectMultiResult(cursor);
    }
}
