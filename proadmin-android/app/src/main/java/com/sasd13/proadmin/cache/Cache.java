package com.sasd13.proadmin.cache;

import android.content.Context;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.ILayeredDAO;
import com.sasd13.proadmin.cache.db.SQLiteDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Samir on 27/02/2016.
 */
public class Cache {

    private static ILayeredDAO dao;

    public static <T> void keep(Context context, T t) {
        dao = SQLiteDAO.create(context);

        try {
            dao.open();

            performKeep(t);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                dao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static <T> void performKeep(T t) throws DAOException {
        ((IPersistable<T>) dao.getEntityDAO(t.getClass())).persist(t);
    }

    public static <T> void keepAll(Context context, List<T> ts) {
        dao = SQLiteDAO.create(context);

        try {
            dao.open();

            for (T t : ts) {
                performKeep(t);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                dao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T load(Context context, long id, Class<T> mClass) {
        T t = null;

        dao = SQLiteDAO.create(context);

        try {
            dao.open();

            t = dao.getEntityDAO(mClass).select(id);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                dao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return t;
    }

    public static <T> List<T> load(Context context, Map<String, String[]> parameters, Class<T> mClass) {
        List<T> ts = new ArrayList<>();

        dao = SQLiteDAO.create(context);

        try {
            dao.open();

            ts = dao.getEntityDAO(mClass).select(parameters);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                dao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ts;
    }

    public static <T> List<T> loadAll(Context context, Class<T> mClass) {
        List<T> ts = new ArrayList<>();

        dao = SQLiteDAO.create(context);

        try {
            dao.open();

            ts = dao.getEntityDAO(mClass).selectAll();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                dao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ts;
    }
}
