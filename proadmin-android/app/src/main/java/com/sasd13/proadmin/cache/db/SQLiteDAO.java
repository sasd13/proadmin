package com.sasd13.proadmin.cache.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.androidex.db.ISQLiteDAO;
import com.sasd13.proadmin.cache.IClearable;
import com.sasd13.proadmin.core.db.DAO;
import com.sasd13.proadmin.core.db.IndividualEvaluationDAO;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;
import com.sasd13.proadmin.core.db.ProjectDAO;
import com.sasd13.proadmin.core.db.ReportDAO;
import com.sasd13.proadmin.core.db.RunningDAO;
import com.sasd13.proadmin.core.db.StudentDAO;
import com.sasd13.proadmin.core.db.StudentTeamDAO;
import com.sasd13.proadmin.core.db.TeacherDAO;
import com.sasd13.proadmin.core.db.TeamDAO;

public class SQLiteDAO extends DAO implements ISQLiteDAO, IClearable {

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
        dbHandler = new SQLiteDBHandler(context, SQLiteDBInformation.DB, null, SQLiteDBInformation.VERSION);
    }

    @Override
    public void open() {
        try {
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
        } catch (NullPointerException e) {
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

    @Override
    public void clear() {
        try {
            db.execSQL("DELETE FROM " + IndividualEvaluationDAO.TABLE);
            db.execSQL("DELETE FROM " + LeadEvaluationDAO.TABLE);
            db.execSQL("DELETE FROM " + ReportDAO.TABLE);
            db.execSQL("DELETE FROM " + StudentTeamDAO.TABLE);
            db.execSQL("DELETE FROM " + StudentDAO.TABLE);
            db.execSQL("DELETE FROM " + TeamDAO.TABLE);
            db.execSQL("DELETE FROM " + RunningDAO.TABLE);
            db.execSQL("DELETE FROM " + ProjectDAO.TABLE);
            db.execSQL("DELETE FROM " + TeacherDAO.TABLE);
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }
}