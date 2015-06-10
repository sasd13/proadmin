package proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.Id;
import proadmin.content.ListIds;
import proadmin.content.ListYears;
import proadmin.content.Year;

/**
 * Created by Samir on 02/04/2015.
 */
class ProjectHasYearDAO extends AbstractDAO {

    public static final String PROJECT_HAS_YEAR_TABLE_NAME = "projects_have_years";

    public static final String PROJECT_HAS_YEAR_PROJECT_ID = "project_id";
    public static final String PROJECT_HAS_YEAR_YEAR_YEAR = "school_year";

    public long insert(Id projectId, Year year) {
        return db.insert(PROJECT_HAS_YEAR_TABLE_NAME, null, getContentValues(projectId, year));
    }

    private ContentValues getContentValues(Id projectId, Year year) {
        ContentValues values = new ContentValues();

        values.put(PROJECT_HAS_YEAR_PROJECT_ID, projectId.toString());
        values.put(PROJECT_HAS_YEAR_YEAR_YEAR, year.getValue());

        return values;
    }

    public void deleteAllOfYear(Year year) {
        db.delete(PROJECT_HAS_YEAR_TABLE_NAME, PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{year.toString()});
    }

    public void deleteAllOfProject(Id projectId) {
        db.delete(PROJECT_HAS_YEAR_TABLE_NAME, PROJECT_HAS_YEAR_PROJECT_ID + " = ?", new String[]{projectId.toString()});
    }

    public void delete(Id projectId, Year year) {
        db.delete(PROJECT_HAS_YEAR_TABLE_NAME, PROJECT_HAS_YEAR_PROJECT_ID + " = ? and " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{projectId.toString(), year.toString()});
    }

    public ListIds selectAllOfYear(Year year) {
        ListIds listIds = new ListIds();

        Cursor cursor = db.rawQuery(
                "select " + PROJECT_HAS_YEAR_PROJECT_ID
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{year.toString()});

        if (cursor.moveToNext()) {
            listIds.add(new Id(cursor.getString(0)));
        }
        cursor.close();

        return listIds;
    }

    public ListYears selectAllOfProject(Id projectId) {
        ListYears listYears = new ListYears();

        Cursor cursor = db.rawQuery(
                "select " + PROJECT_HAS_YEAR_YEAR_YEAR
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_PROJECT_ID + " = ?", new String[]{projectId.toString()});

        if (cursor.moveToNext()) {
            listYears.add(new Year(cursor.getLong(0)));
        }
        cursor.close();

        return listYears;
    }

    public boolean contains(Id projectId, Year year) {
        boolean contains = false;

        Cursor cursor = db.rawQuery(
                "select " + PROJECT_HAS_YEAR_PROJECT_ID + ", " + PROJECT_HAS_YEAR_YEAR_YEAR
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_PROJECT_ID + " = ? and " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{projectId.toString(), year.toString()});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
