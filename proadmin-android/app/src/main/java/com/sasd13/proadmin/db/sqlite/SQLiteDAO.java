package com.sasd13.proadmin.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.androidex.db.LocalSQLiteDB;
import com.sasd13.proadmin.core.db.DAO;

public class SQLiteDAO extends DAO implements LocalSQLiteDB {

    private static final String DB = "database.db";
    private static final int VERSION = 1;

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    private SQLiteDAO() {
        teacherDAO = new SQLiteTeacherDAO();
        projectDAO = new SQLiteProjectDAO();
        runningDAO = new SQLiteRunningDAO();
        teamDAO = new SQLiteTeamDAO();
        studentDAO = new SQLiteStudentDAO();
        studentTeamDAO = new SQLiteStudentTeamDAO();
        reportDAO = new SQLiteReportDAO();
        leadEvaluationDAO = new SQLiteLeadEvaluationDAO();
        individualEvaluationDAO = new SQLiteIndividualEvaluationDAO();
    }

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, DB, null, VERSION);
    }

    @Override
    public void open() {
        db = dbHandler.getWritableDatabase();

        ((SQLiteTeacherDAO) teacherDAO).setDB(db);
        ((SQLiteProjectDAO) projectDAO).setDB(db);
        ((SQLiteRunningDAO) runningDAO).setDB(db);
        ((SQLiteTeamDAO) teamDAO).setDB(db);
        ((SQLiteStudentDAO) studentDAO).setDB(db);
        ((SQLiteStudentTeamDAO) studentTeamDAO).setDB(db);
        ((SQLiteReportDAO) reportDAO).setDB(db);
        ((SQLiteLeadEvaluationDAO) leadEvaluationDAO).setDB(db);
        ((SQLiteIndividualEvaluationDAO) individualEvaluationDAO).setDB(db);
    }

    @Override
    public void close() {
        db.close();
    }
}