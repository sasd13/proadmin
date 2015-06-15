package proadmin.data.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import proadmin.content.ListYears;
import proadmin.content.Year;

/**
 * Created by Samir on 02/04/2015.
 */
class YearDAO extends AbstractTableDAO {

    public static final String YEAR_TABLE_NAME = "years";

    public static final String YEAR_YEAR = "school_year";

    public long insert(Year year) {
        return db.insert(YEAR_TABLE_NAME, null, getContentValues(year));
    }

    private ContentValues getContentValues(Year year) {
        ContentValues values = new ContentValues();

        values.put(YEAR_YEAR, year.getValue());

        return values;
    }

    public long delete(Year year) {
        return db.delete(YEAR_TABLE_NAME, YEAR_YEAR + " = ?", new String[]{year.toString()});
    }

    public ListYears selectAll() {
        ListYears listYears = new ListYears();

        Cursor cursor = db.rawQuery(
                "select " + YEAR_YEAR
                        + " from " + YEAR_TABLE_NAME + " order by " + YEAR_YEAR + " desc", null);

        if (cursor.moveToNext()) {
            listYears.add(new Year(cursor.getLong(0)));
        }
        cursor.close();

        return listYears;
    }
}
