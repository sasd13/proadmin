package proadmin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samir on 05/06/2015.
 */
abstract class AbstractDAO {

    protected SQLiteDatabase mDb;

    protected AbstractDAO(SQLiteDatabase mDb) {
        this.mDb = mDb;
    }
}
