package com.sasd13.proadmin.cache;

import android.content.Context;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.IEntityDAO;
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

    public static <T> void keep(Context context, T t) {
        ILayeredDAO dao = SQLiteDAO.create(context);

        try {
            dao.open();

            performKeep(t, dao);
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

    private static <T> void performKeep(T t, ILayeredDAO dao) throws DAOException {
        ((IPersistable<T>) dao.getEntityDAO(t.getClass())).persist(t);
    }

    public static <T> void keepAll(Context context, List<T> ts) {
        ILayeredDAO dao = SQLiteDAO.create(context);

        try {
            dao.open();

            for (T t : ts) {
                performKeep(t, dao);
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
        return load(context, id, mClass, false);
    }

    public static <T> T load(Context context, long id, Class<T> mClass, boolean deepRead) {
        T t = null;

        ILayeredDAO dao = SQLiteDAO.create(context);

        try {
            dao.open();

            t = deepRead ? dao.getDeepReader(mClass).select(id) : dao.getEntityDAO(mClass).select(id);
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
        return load(context, parameters, mClass, false);
    }

    public static <T> List<T> load(Context context, Map<String, String[]> parameters, Class<T> mClass, boolean deepRead) {
        List<T> ts = new ArrayList<>();

        ILayeredDAO dao = SQLiteDAO.create(context);

        try {
            dao.open();

            ts = deepRead ? dao.getDeepReader(mClass).select(parameters) : dao.getEntityDAO(mClass).select(parameters);
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
        return loadAll(context, mClass, false);
    }

    public static <T> List<T> loadAll(Context context, Class<T> mClass, boolean deepRead) {
        List<T> ts = new ArrayList<>();

        ILayeredDAO dao = SQLiteDAO.create(context);

        try {
            dao.open();

            ts = deepRead ? dao.getDeepReader(mClass).selectAll() : dao.getEntityDAO(mClass).selectAll();
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

    public static <T> void delete(Context context, T t) {
        ILayeredDAO dao = SQLiteDAO.create(context);

        try {
            dao.open();

            ((IEntityDAO<T>) dao.getEntityDAO(t.getClass())).delete(t);
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
}
