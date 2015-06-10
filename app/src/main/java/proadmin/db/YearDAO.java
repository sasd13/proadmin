package proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import proadmin.content.ListYears;

/**
 * Created by Samir on 02/04/2015.
 */
class YearDAO extends AbstractDAO {

    public static final String YEAR_TABLE_NAME = "years";

    public static final String YEAR_YEAR = "school_year";

    public YearDAO(SQLiteDatabase mDb) {
        super(mDb);
    }

    public void insert(long year) {
        mDb.insert(YEAR_TABLE_NAME, null, getContentValues(year));
    }

    private ContentValues getContentValues(long year) {
        ContentValues values = new ContentValues();

        values.put(YEAR_YEAR, year);

        return values;
    }

    public void delete(long year) {
        mDb.delete(YEAR_TABLE_NAME, YEAR_YEAR + " = ?", new String[]{String.valueOf(year)});
    }

    public ListYears selectAll() {
        ListYears listYears = new ListYears();

        Cursor cursor = mDb.rawQuery(
                "select " + YEAR_YEAR
                        + " from " + YEAR_TABLE_NAME + " order by desc", null);

        if (cursor.moveToNext()) {
            listYears.add(cursor.getLong(0));
        }
        cursor.close();

        return listYears;
    }

    public boolean contains(long year) {
        boolean contains = false;

        Cursor cursor = mDb.rawQuery(
                "select " + YEAR_YEAR
                        + " from " + YEAR_TABLE_NAME
                        + " where " + YEAR_YEAR + " = ?", new String[]{String.valueOf(year)});

        if (cursor.moveToNext()) {
            contains = true;
        }
        cursor.close();

        return contains;
    }
}
