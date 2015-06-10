package proadmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 02/04/2015.
 */
class ProjectHasYearDAO extends AbstractDAO {

    public static final String PROJECT_HAS_YEAR_TABLE_NAME = "projects_have_years";

    public static final String PROJECT_HAS_YEAR_PROJECT_ID = "project_id";
    public static final String PROJECT_HAS_YEAR_YEAR_YEAR = "school_year";

    public ProjectHasYearDAO(Context context, SQLiteDatabase mDb) {
        super(context, mDb);
    }

    public long insert(String projectId, long year) {
        return mDb.insert(PROJECT_HAS_YEAR_TABLE_NAME, null, getContentValues(projectId, year));
    }

    private ContentValues getContentValues(String projectId, long year) {
        ContentValues values = new ContentValues();

        values.put(PROJECT_HAS_YEAR_PROJECT_ID, projectId);
        values.put(PROJECT_HAS_YEAR_YEAR_YEAR, year);

        return values;
    }

    public void deleteAllOfYear(long year) {
        mDb.delete(PROJECT_HAS_YEAR_TABLE_NAME, PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{String.valueOf(year)});
    }

    public void deleteAllOfProject(String projectId) {
        mDb.delete(PROJECT_HAS_YEAR_TABLE_NAME, PROJECT_HAS_YEAR_PROJECT_ID + " = ?", new String[]{projectId});
    }

    public void delete(String projectId, long year) {
        mDb.delete(PROJECT_HAS_YEAR_TABLE_NAME, PROJECT_HAS_YEAR_PROJECT_ID + " = ? and " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{projectId, String.valueOf(year)});
    }

    public List<String> selectAllOfYear(long year) {
        List<String> listIds = new ArrayList<>();

        Cursor cursor = mDb.rawQuery(
                "select " + PROJECT_HAS_YEAR_PROJECT_ID
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{String.valueOf(year)});

        if (cursor.moveToNext()) {
            listIds.add(cursor.getString(0));
        }
        cursor.close();

        return listIds;
    }

    public List<Long> selectAllOfProject(String projectId) {
        List<Long> listIds = new ArrayList<>();

        Cursor cursor = mDb.rawQuery(
                "select " + PROJECT_HAS_YEAR_YEAR_YEAR
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_PROJECT_ID + " = ?", new String[]{projectId});

        if (cursor.moveToNext()) {
            listIds.add(cursor.getLong(0));
        }
        cursor.close();

        return listIds;
    }

    public boolean contains(String projectId, long year) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + PROJECT_HAS_YEAR_PROJECT_ID + ", " + PROJECT_HAS_YEAR_YEAR_YEAR
                        + " from " + PROJECT_HAS_YEAR_TABLE_NAME
                        + " where " + PROJECT_HAS_YEAR_PROJECT_ID + " = ? and " + PROJECT_HAS_YEAR_YEAR_YEAR + " = ?", new String[]{projectId, String.valueOf(year)});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
