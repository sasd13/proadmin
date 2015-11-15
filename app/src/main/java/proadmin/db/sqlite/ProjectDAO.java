package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import proadmin.beans.AcademicLevel;
import proadmin.beans.Project;
import proadmin.db.ProjectTableAccessor;

public class ProjectDAO extends SQLiteTableDAO<Project> implements ProjectTableAccessor {

    @Override
    protected ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();

        //values.put(PROJECT_ID, project.getId()); //autoincrement
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
        ContentValues values = getContentValues(project);
        values.put(PROJECT_DELETED, false);

        return getDB().insert(PROJECT_TABLE_NAME, null, values);
    }

    @Override
    public void update(Project project) {
        getDB().update(PROJECT_TABLE_NAME, getContentValues(project), PROJECT_ID + " = ?", new String[]{String.valueOf(project.getId())});
    }

    @Override
    public void delete(long id) {
        getDB().delete(PROJECT_TABLE_NAME, PROJECT_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Project select(long id) {
        Project project = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + PROJECT_TABLE_NAME
                        + " where " + PROJECT_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            project = getCursorValues(cursor);
        }
        cursor.close();

        return project;
    }

    @Override
    public List<Project> selectByCode(String code) {
        List<Project> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + PROJECT_TABLE_NAME
                        + " where " + PROJECT_CODE + " = ?", new String[]{String.valueOf(code)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public List<Project> selectByAcademicLevel(String academicLevel) {
        List<Project> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + PROJECT_TABLE_NAME
                        + " where " + PROJECT_ACADEMICLEVEL + " = ?", new String[]{String.valueOf(academicLevel)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
