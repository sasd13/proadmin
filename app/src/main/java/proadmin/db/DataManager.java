package proadmin.db;

import android.content.Context;

import proadmin.db.accessor.DataAccessor;
import proadmin.db.accessor.DataAccessorException;
import proadmin.db.accessor.DataAccessorFactory;
import proadmin.db.accessor.DataAccessorType;
import proadmin.db.sqlite.SQLiteDAO;

/**
 * Created by Samir on 11/06/2015.
 */
public class DataManager {

    private static DataAccessor dao;

    public static void start(Context context, DataAccessorType type) {
        try {
            dao = DataAccessorFactory.get(type);

            if (type == DataAccessorType.SQLITE) {
                ((SQLiteDAO) dao).create(context);
            }
        } catch (DataAccessorException e) {
            e.printStackTrace();
        }
    }

    public static DataAccessor getDao() {
        return dao;
    }

    public static void setDao(DataAccessor mDao) {
        dao = mDao;
    }
}
