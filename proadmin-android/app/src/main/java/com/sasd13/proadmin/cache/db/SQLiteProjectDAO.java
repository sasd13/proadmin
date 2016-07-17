package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.javaex.db.condition.ConditionBuilder;
import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.ProjectDAO;
import com.sasd13.proadmin.dao.condition.ProjectConditionExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteProjectDAO extends SQLiteEntityDAO<Project> implements ProjectDAO {

    @Override
    protected ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, project.getId());
        values.put(COLUMN_ACADEMICLEVEL, project.getAcademicLevel().getName());
        values.put(COLUMN_CODE, project.getCode());
        values.put(COLUMN_TITLE, project.getTitle());
        values.put(COLUMN_DESCRIPTION, project.getDescription());

        return values;
    }

    @Override
    protected Project getCursorValues(Cursor cursor) {
        Project project = new Project();

        project.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        project.setAcademicLevel(EnumAcademicLevel.find(cursor.getString(cursor.getColumnIndex(COLUMN_ACADEMICLEVEL))));
        project.setCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
        project.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        project.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));

        return project;
    }

    @Override
    public long insert(Project project) {
        return db.insert(TABLE, null, getContentValues(project));
    }

    @Override
    public void update(Project project) {
        db.update(TABLE, getContentValues(project), COLUMN_ID + " = ?", new String[]{ String.valueOf(project.getId()) });
    }

    @Override
    public void delete(Project project) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + project.getId());

        try {
            db.execSQL(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project select(long id) {
        Project project = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            project = getCursorValues(cursor);
        }
        cursor.close();

        return project;
    }

    @Override
    public List<Project> select(Map<String, String[]> parameters) {
        List<Project> list = new ArrayList<>();

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, ProjectConditionExpression.class));
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
    public List<Project> selectAll() {
        List<Project> list = new ArrayList<>();

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
    public void persist(Project project) {
        if (select(project.getId()) == null) {
            insert(project);
        } else {
            update(project);
        }
    }
}
