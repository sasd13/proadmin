package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.ProjectDAO;
import com.sasd13.proadmin.core.db.util.WhereClauseException;
import com.sasd13.proadmin.core.db.util.WhereClauseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        long id = db.insert(TABLE, null, getContentValues(project));

        if (id < 0) id = 0;

        project.setId(id);

        return id;
    }

    @Override
    public void update(Project project) {
        db.update(TABLE, getContentValues(project), COLUMN_ID + " = ?", new String[]{String.valueOf(project.getId())});
    }

    @Override
    public void delete(long id) {
        String query = "UPDATE " + TABLE
                + " SET "
                    + COLUMN_DELETED + " = 1"
                + " WHERE "
                    + COLUMN_ID + " = " + id;

        db.execSQL(query);
    }

    @Override
    public Project select(long id) {
        Project project = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                project = getCursorValues(cursor);
            }
        }
        cursor.close();

        return project;
    }

    @Override
    public List<Project> select(Map<String, String[]> parameters) {
        List<Project> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE " + WhereClauseParser.parse(ProjectDAO.class, parameters) + ";";

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                    list.add(getCursorValues(cursor));
                }
            }
            cursor.close();
        } catch (WhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Project> selectAll() {
        List<Project> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                list.add(getCursorValues(cursor));
            }
        }
        cursor.close();

        return list;
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
