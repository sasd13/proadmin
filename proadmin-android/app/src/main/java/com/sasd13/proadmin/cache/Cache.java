package com.sasd13.proadmin.cache;

import android.content.Context;

import com.sasd13.proadmin.cache.db.SQLiteDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 23/01/2016.
 */
public class Cache {

    private static SQLiteDAO dao = SQLiteDAO.getInstance();

    public static void init(Context context) {
        dao.init(context);
    }

    public static void keep(Object object) {
        try {
            dao.open();

            performKeep(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    private static void performKeep(Object object) {
        ((IPersistable) dao.getEntityDAO(object.getClass())).persist(object);
    }

    public static void keepAll(List list) {
        try {
            dao.open();

            for (Object object : list) {
                performKeep(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    public static Object load(long id, Class mClass) {
        Object object = null;

        try {
            dao.open();

            object = dao.getEntityDAO(mClass).select(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return object;
    }

    public static List loadAll(Class mClass) {
        List list = new ArrayList();

        try {
            dao.open();

            list = dao.getEntityDAO(mClass).selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return list;
    }

    public static void clear() {
        try {
            dao.open();
            dao.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }
}
