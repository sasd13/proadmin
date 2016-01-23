package com.sasd13.proadmin.cache;

import android.content.Context;

import com.sasd13.proadmin.cache.db.SQLiteDAO;

import java.util.List;

/**
 * Created by Samir on 23/01/2016.
 */
public class Cache {

    private static SQLiteDAO dao = SQLiteDAO.getInstance();

    public static void init(Context context) {
        dao.init(context);
    }

    public static void persist(Object object) {
        ((IPersistable) dao.getEntityDAO(object.getClass())).persist(object);
    }

    public static Object select(long id, Class mClass) {
        return dao.getEntityDAO(mClass).select(id);
    }

    public static List selectAll(Class mClass) {
        return dao.getEntityDAO(mClass).selectAll();
    }

    public static void clear() {
        dao.clear();
    }
}
