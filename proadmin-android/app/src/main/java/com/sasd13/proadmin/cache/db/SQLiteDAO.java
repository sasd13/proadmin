package com.sasd13.proadmin.cache.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.sasd13.proadmin.dao.DAO;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDAO extends DAO {

    private static List<SQLiteDAO> pool = new ArrayList<>();

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    private SQLiteDAO(Context context) {
        super(
                new SQLiteTeacherDAO(),
                new SQLiteProjectDAO(),
                new SQLiteStudentDAO(),
                new SQLiteTeamDAO(),
                new SQLiteStudentTeamDAO(),
                new SQLiteRunningDAO(),
                new SQLiteRunningTeamDAO(),
                new SQLiteReportDAO()
        );

        dbHandler = new SQLiteDBHandler(context, SQLiteDBInformation.DB, null, SQLiteDBInformation.VERSION);
    }

    public static SQLiteDAO create(Context context) {
        for (SQLiteDAO dao : pool) {
            if (!dao.isOpened()) {
                return dao;
            }
        }

        SQLiteDAO dao = new SQLiteDAO(context);
        pool.add(dao);

        return dao;
    }

    private boolean isOpened() {
        return db.isOpen();
    }

    @Override
    public void open() {
        try {
            db = dbHandler.getWritableDatabase();

            ((SQLiteEntityDAO) teacherDAO).setDB(db);
            ((SQLiteEntityDAO) projectDAO).setDB(db);
            ((SQLiteEntityDAO) studentDAO).setDB(db);
            ((SQLiteEntityDAO) teamDAO).setDB(db);
            ((SQLiteEntityDAO) studentTeamDAO).setDB(db);
            ((SQLiteEntityDAO) runningDAO).setDB(db);
            ((SQLiteEntityDAO) runningTeamDAO).setDB(db);
            ((SQLiteEntityDAO) reportDAO).setDB(db);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (db != null) {
            try {
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}