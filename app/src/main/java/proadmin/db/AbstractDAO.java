package proadmin.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samir on 05/06/2015.
 */
abstract class AbstractDAO {

    protected SQLiteDatabase db;

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
}
