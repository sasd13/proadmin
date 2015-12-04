package com.sasd13.proadmin.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.proadmin.db.DAO;

public class SQLiteDAO extends DAO {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    private SQLiteDAO() { super(); }

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, NOM, null, VERSION);

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

    @Override
    protected void open() {
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
    protected void close() {
        db.close();
    }
}