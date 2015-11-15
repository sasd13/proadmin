package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.beans.projects.Project;
import proadmin.beans.AcademicLevel;

/**
 * Created by Samir on 02/04/2015.
 */
class ProjectDAO extends AbstractTableDAO {

    public static final String PROJECT_TABLE_NAME = "projects";

    public static final String PROJECT_ID = "project_id";
    public static final String PROJECT_TITLE = "title";
    public static final String PROJECT_GRADE = "grade";
    public static final String PROJECT_DESCRIPTION = "description";

    public long insert(Project project) {
        return db.insert(PROJECT_TABLE_NAME, null, getContentValues(project));
    }

    private ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();

        values.put(PROJECT_ID, project.getId());
        values.put(PROJECT_TITLE, project.getTitle());
        values.put(PROJECT_GRADE, project.getAcademicLevel().toString());
        values.put(PROJECT_DESCRIPTION, project.getDescription());

        return values;
    }

    public long update(Project project) {
        return db.update(PROJECT_TABLE_NAME, getContentValues(project), PROJECT_ID + " = ?", new String[]{project.getId()});
    }

    public long delete(String projectId) {
        return db.delete(PROJECT_TABLE_NAME, PROJECT_ID + " = ?", new String[]{projectId});
    }

    public Project select(String projectId) {
        Project project = null;

        Cursor cursor = db.rawQuery(
                "select " + PROJECT_TITLE + ", " + PROJECT_GRADE + ", " + PROJECT_DESCRIPTION
                        + " from " + PROJECT_TABLE_NAME
                        + " where " + PROJECT_ID + " = ?", new String[]{projectId});

        if (cursor.moveToNext()) {
            project = new Project();
            project.setId(projectId);
            project.setTitle(cursor.getString(0));
            project.setAcademicLevel(AcademicLevel.valueOf(cursor.getString(1)));
            project.setDescription(cursor.getString(2));
        }
        cursor.close();

        return project;
    }

    public boolean contains(String projectId) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + PROJECT_ID
                        + " from " + PROJECT_TABLE_NAME
                        + " where " + PROJECT_ID + " = ?", new String[]{projectId});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}