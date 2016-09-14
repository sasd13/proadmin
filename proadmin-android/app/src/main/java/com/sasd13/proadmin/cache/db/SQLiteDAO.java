package com.sasd13.proadmin.cache.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.sasd13.proadmin.dao.DAO;

public class SQLiteDAO extends DAO {

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
        return new SQLiteDAO(context);
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