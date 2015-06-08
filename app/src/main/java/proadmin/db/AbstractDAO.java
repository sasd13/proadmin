package proadmin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samir on 05/06/2015.
 */
abstract class AbstractDAO {

    protected Context context;
    protected SQLiteDatabase mDb;

    protected AbstractDAO(Context context, SQLiteDatabase mDb) {
        this.context = context;
        this.mDb = mDb;
    }
}
