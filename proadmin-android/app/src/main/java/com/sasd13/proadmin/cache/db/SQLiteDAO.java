package com.sasd13.proadmin.cache.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.sasd13.androidex.db.ISQLiteDAO;
import com.sasd13.proadmin.dao.DAO;

public class SQLiteDAO extends DAO implements ISQLiteDAO {

    private static class SQLiteDAOHolder {
        private static final SQLiteDAO INSTANCE = new SQLiteDAO();
    }

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    private SQLiteDAO() {
        super(
                new SQLiteTeacherDAO(),
                new SQLiteProjectDAO(),
                new SQLiteStudentDAO(),
                new SQLiteTeamDAO(),
                new SQLiteStudentTeamDAO(),
                new SQLiteRunningDAO(),
                new SQLiteRunningTeamDAO(),
                new SQLiteReportDAO(),
                new SQLiteLeadEvaluationDAO(),
                new SQLiteIndividualEvaluationDAO()
        );
    }

    public static SQLiteDAO getInstance() {
        return SQLiteDAOHolder.INSTANCE;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, SQLiteDBInformation.DB, null, SQLiteDBInformation.VERSION);
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
            ((SQLiteEntityDAO) leadEvaluationDAO).setDB(db);
            ((SQLiteEntityDAO) individualEvaluationDAO).setDB(db);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
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