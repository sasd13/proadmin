package com.sasd13.proadmin.cache;

import android.content.Context;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.ILayeredDAO;
import com.sasd13.proadmin.cache.db.SQLiteDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Samir on 27/02/2016.
 */
public class Cache {

    private static ILayeredDAO dao;

    public static void init(Context context) {
        dao = SQLiteDAO.create(context);
    }

    public static <T> void keep(T t) {
        try {
            dao.open();

            performKeep(t);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    private static <T> void performKeep(T t) throws DAOException {
        ((IPersistable<T>) dao.getEntityDAO(t.getClass())).persist(t);
    }

    public static <T> void keepAll(List<T> ts) {
        try {
            dao.open();

            for (T t : ts) {
                performKeep(t);
            }
        } catch (DAOException e) {
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
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return t;
    }

    public static <T> List<T> load(Map<String, String[]> parameters, Class<T> mClass) {
        List<T> ts = new ArrayList<>();

        try {
            dao.open();

            ts = dao.getEntityDAO(mClass).select(parameters);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return ts;
    }

    public static <T> List<T> loadAll(Class<T> mClass) {
        List<T> ts = new ArrayList<>();

        try {
            dao.open();

            ts = dao.getEntityDAO(mClass).selectAll();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return ts;
    }
}
