package com.sasd13.proadmin.cache.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.androidex.db.ISQLiteDAO;
import com.sasd13.proadmin.core.db.DAO;

public class SQLiteDAO extends DAO implements ISQLiteDAO {

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    private SQLiteDAO() {
        super(
                new SQLiteTeacherDAO(),
                new SQLiteProjectDAO(),
                new SQLiteRunningDAO(),
                new SQLiteTeamDAO(),
                new SQLiteStudentDAO(),
                new SQLiteStudentTeamDAO(),
                new SQLiteReportDAO(),
                new SQLiteLeadEvaluationDAO(),
                new SQLiteIndividualEvaluationDAO()
        );
    }

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, SQLiteDBInformation.DB, null, SQLiteDBInformation.VERSION);
    }

    @Override
    public void open() {
        db = dbHandler.getWritableDatabase();

        ((SQLiteEntityDAO) teacherDAO).setDB(db);
        ((SQLiteEntityDAO) projectDAO).setDB(db);
        ((SQLiteEntityDAO) runningDAO).setDB(db);
        ((SQLiteEntityDAO) teamDAO).setDB(db);
        ((SQLiteEntityDAO) studentDAO).setDB(db);
        ((SQLiteEntityDAO) studentTeamDAO).setDB(db);
        ((SQLiteEntityDAO) reportDAO).setDB(db);
        ((SQLiteEntityDAO) leadEvaluationDAO).setDB(db);
        ((SQLiteEntityDAO) individualEvaluationDAO).setDB(db);
    }

    @Override
    public void close() {
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}