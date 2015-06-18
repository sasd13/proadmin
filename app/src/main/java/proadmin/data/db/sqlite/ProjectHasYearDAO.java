package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.id.ListIds;
import proadmin.content.ListYears;
import proadmin.content.Year;
import proadmin.content.id.ProjectId;

/**
 * Created by Samir on 02/04/2015.
 */
class ProjectHasYearDAO extends AbstractTableDAO {

    public static final String PROJECT_HAS_YEAR_TABLE_NAME = "projects_have_years";

    public static final String PROJECT_HAS_YEAR_PROJECT_ID = ProjectDAO.PROJECT_ID;
    public static final String PROJECT_HAS_YEAR_YEAR_YEAR = YearDAO.YEAR_YEAR;

    public long insert(ProjectId projectId, Year year) {
        return db.insert(PROJECT_HAS_YEAR_TABLE_NAME, null, getContentValues(projectId, year));
    }

    private ContentValues getContentValues(ProjectId projectId, Year year) {
        ContentValues values = new ContentValues();

        values.put(PROJECT_HAS_YEAR_PROJECT_ID, projectId.toString());
        values.put(PROJECT_HAS_YEAR_YEAR_YEAR, year.getValue());

        return values;
    }

    public long deleteAllOfProject(ProjectId projectId) {
        return db.delete(PROJECT_HAS_YEAR_TABLE_NAME, PROJECT_HAS_YEAR_PROJECT_ID + " = ?", new String[]{projectId.toString()});
    }

    public long delete(ProjectId projectId, Year year) {
        return db.delete(PROJECT_HAS_YEAR_TABLE_NAME, PROJECT_HAS_YEAR_PROJECT_ID + " = ? and " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{projectId.toString(), year.toString()});
    }

    public ListIds selectAllOfYear(Year year) {
        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + PROJECT_HAS_YEAR_PROJECT_ID
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{year.toString()});

        while (cursor.moveToNext()) {
            listIds.add(new ProjectId(cursor.getString(0)));
        }
        cursor.close();

        return listIds;
    }

    public ListYears selectAllOfProjectByDesc(ProjectId projectId) {
        ListYears listYears = new ListYears();

        Cursor cursor = db.rawQuery(
                "select " + PROJECT_HAS_YEAR_YEAR_YEAR
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_PROJECT_ID + " = ? order by " + PROJECT_HAS_YEAR_YEAR_YEAR + " desc", new String[]{projectId.toString()});

        while (cursor.moveToNext()) {
            listYears.add(new Year(cursor.getLong(0)));
        }
        cursor.close();

        return listYears;
    }

    public boolean contains(ProjectId projectId, Year year) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + PROJECT_HAS_YEAR_PROJECT_ID + ", " + PROJECT_HAS_YEAR_YEAR_YEAR
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_PROJECT_ID + " = ? and " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?",
                new String[]{projectId.toString(), year.toString()});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
