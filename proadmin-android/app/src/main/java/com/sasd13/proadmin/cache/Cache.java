package com.sasd13.proadmin.cache;

import android.content.Context;

import com.sasd13.androidex.db.ISQLiteDAO;
import com.sasd13.javaex.db.DBException;
import com.sasd13.javaex.db.IDAO;
import com.sasd13.proadmin.cache.db.SQLiteDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Samir on 27/02/2016.
 */
public class Cache {

    private static IDAO dao = SQLiteDAO.getInstance();

    public static void init(Context context) {
        ((ISQLiteDAO) dao).init(context);
    }

    public static <T> void keep(T t) {
        try {
            dao.open();

            performKeep(t);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    private static <T> void performKeep(T t) throws DBException {
        ((IPersistable<T>) dao.getEntityDAO(t.getClass())).persist(t);
    }

    public static <T> void keepAll(List<T> ts) {
        try {
            dao.open();

            for (T t : ts) {
                performKeep(t);
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    public static <T> T load(long id, Class<T> mClass) {
        T t = null;

        try {
            dao.open();

            t = dao.getEntityDAO(mClass).select(id);
        } catch (DBException e) {
            e.printStackTrace();
        }

        return t;
    }

    public static <T> List<T> load(Map<String, String[]> parameters, Class<T> mClass) {
        List<T> ts = new ArrayList<>();

        try {
            dao.open();

            ts = dao.getEntityDAO(mClass).select(parameters);
        } catch (DBException e) {
            e.printStackTrace();
        }

        return ts;
    }

    public static <T> List<T> loadAll(Class<T> mClass) {
        List<T> ts = new ArrayList<>();

        try {
            dao.open();

            ts = dao.getEntityDAO(mClass).selectAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        return ts;
    }
}
