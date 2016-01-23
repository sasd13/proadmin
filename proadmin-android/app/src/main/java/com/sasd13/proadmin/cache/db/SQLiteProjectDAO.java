package com.sasd13.proadmin.cache.db;

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

        values.put(COLUMN_CODE, project.getCode());
        values.put(COLUMN_ACADEMICLEVEL, String.valueOf(project.getAcademicLevel()));
        values.put(COLUMN_TITLE, project.getTitle());
        values.put(COLUMN_DESCRIPTION, project.getDescription());

        return values;
    }

    @Override
    protected Project getCursorValues(Cursor cursor) {
        Project project = new Project();

        project.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        project.setCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
        project.setAcademicLevel(AcademicLevel.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_ACADEMICLEVEL))));
        project.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        project.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));

        return project;
    }

    @Override
    public long insert(Project project) {
        long id = executeInsert(TABLE, project);

        project.setId(id);

        return id;
    }

    @Override
    public void update(Project project) {
        executeUpdate(TABLE, project, COLUMN_ID, project.getId());
    }

    @Override
    public void delete(long id) {
        //Do nothing
    }

    @Override
    public Project select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Project> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }

    @Override
    public void persist(Project project) {
        if (select(project.getId()) == null) {
            insert(project);
        } else {
            update(project);
        }
    }
}
