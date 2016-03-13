package com.sasd13.proadmin.cache.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.ProjectDAO;
import com.sasd13.proadmin.dao.ReportDAO;
import com.sasd13.proadmin.dao.RunningDAO;
import com.sasd13.proadmin.dao.RunningTeamDAO;
import com.sasd13.proadmin.dao.StudentDAO;
import com.sasd13.proadmin.dao.StudentTeamDAO;
import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.dao.TeamDAO;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table teachers
     */
    public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS " + TeacherDAO.TABLE + ";";
    public static final String TEACHER_TABLE_CREATE = "CREATE TABLE " + TeacherDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TeacherDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + TeacherDAO.COLUMN_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, "
                + TeacherDAO.COLUMN_FIRSTNAME + " VARCHAR(255) NOT NULL, "
                + TeacherDAO.COLUMN_LASTNAME + " VARCHAR(255) NOT NULL, "
                + TeacherDAO.COLUMN_EMAIL + " VARCHAR(255) NOT NULL, "
                + TeacherDAO.COLUMN_PASSWORD + " VARCHAR(255) NOT NULL, "
                + TeacherDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0"
            + ");";

    /**
     * Table projects
     */
    public static final String PROJECT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectDAO.TABLE + ";";
    public static final String PROJECT_TABLE_CREATE = "CREATE TABLE " + ProjectDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProjectDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + ProjectDAO.COLUMN_CODE + " VARCHAR(255) NOT NULL, "
                + ProjectDAO.COLUMN_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, "
                + ProjectDAO.COLUMN_TITLE + " VARCHAR(255) NOT NULL, "
                + ProjectDAO.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + ProjectDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0"
            + ");";

    /**
     * Table students
     */
    public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentDAO.TABLE + ";";
    public static final String STUDENT_TABLE_CREATE = "CREATE TABLE " + StudentDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StudentDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + StudentDAO.COLUMN_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, "
                + StudentDAO.COLUMN_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, "
                + StudentDAO.COLUMN_FIRSTNAME + " VARCHAR(255) NOT NULL, "
                + StudentDAO.COLUMN_LASTNAME + " VARCHAR(255) NOT NULL, "
                + StudentDAO.COLUMN_EMAIL + " VARCHAR(255) NOT NULL, "
                + StudentDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0"
            + ");";

    /**
     * Table teams
     */
    public static final String TEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + TeamDAO.TABLE + ";";
    public static final String TEAM_TABLE_CREATE = "CREATE TABLE " + TeamDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TeamDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + TeamDAO.COLUMN_CODE + " VARCHAR(255) NOT NULL, "
                + TeamDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0"
            + ");";

    /**
     * Table studentteams
     */
    public static final String STUDENTTEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentTeamDAO.TABLE + ";";
    public static final String STUDENTTEAM_TABLE_CREATE = "CREATE TABLE " + StudentTeamDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StudentTeamDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + StudentTeamDAO.COLUMN_STUDENT_ID + " INTEGER NOT NULL, "
                + StudentTeamDAO.COLUMN_TEAM_ID + " INTEGER NOT NULL, "
                + "CONSTRAINT UNIQUE_CONSTRAINT UNIQUE (" + StudentTeamDAO.COLUMN_STUDENT_ID + ", " + StudentTeamDAO.COLUMN_TEAM_ID + ")"
            + ");";

    /**
     * Table runnings
     */
    public static final String RUNNING_TABLE_DROP = "DROP TABLE IF EXISTS " + RunningDAO.TABLE + ";";
    public static final String RUNNING_TABLE_CREATE = "CREATE TABLE " + RunningDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RunningDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + RunningDAO.COLUMN_YEAR + " INT NOT NULL, "
                + RunningDAO.COLUMN_TEACHER_ID + " INTEGER NOT NULL, "
                + RunningDAO.COLUMN_PROJECT_ID + " INTEGER NOT NULL, "
                + RunningDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0"
            + ");";

    /**
     * Table studentteams
     */
    public static final String RUNNINGTEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + RunningTeamDAO.TABLE + ";";
    public static final String RUNNINGTEAM_TABLE_CREATE = "CREATE TABLE " + RunningTeamDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RunningTeamDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + RunningTeamDAO.COLUMN_RUNNING_ID + " INTEGER NOT NULL, "
                + RunningTeamDAO.COLUMN_TEAM_ID + " INTEGER NOT NULL"
            + ");";

    /**
     * Table reports
     */
    public static final String REPORT_TABLE_DROP = "DROP TABLE IF EXISTS " + ReportDAO.TABLE + ";";
    public static final String REPORT_TABLE_CREATE = "CREATE TABLE " + ReportDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ReportDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + ReportDAO.COLUMN_DATEMEETING + " VARCHAR(255) NOT NULL, "
                + ReportDAO.COLUMN_WEEK + " INT NOT NULL, "
                + ReportDAO.COLUMN_TEAMCOMMENT + " TEXT, "
                + ReportDAO.COLUMN_RUNNINGTEAM + " INTEGER NOT NULL, "
                + ReportDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0"
            + ");";

    /**
     * Table leadevaluations
     */
    public static final String LEADEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + LeadEvaluationDAO.TABLE + ";";
    public static final String LEADEVALUATION_TABLE_CREATE = "CREATE TABLE " + LeadEvaluationDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LeadEvaluationDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + LeadEvaluationDAO.COLUMN_PLANNINGMARK + " FLOAT NOT NULL, "
                + LeadEvaluationDAO.COLUMN_PLANNINGCOMMENT + " TEXT, "
                + LeadEvaluationDAO.COLUMN_COMMUNICATIONMARK + " FLOAT NOT NULL, "
                + LeadEvaluationDAO.COLUMN_COMMUNICATIONCOMMENT + " TEXT, "
                + LeadEvaluationDAO.COLUMN_STUDENT_ID + " INTEGER NOT NULL, "
                + LeadEvaluationDAO.COLUMN_REPORT_ID + " INTEGER NOT NULL, "
                + LeadEvaluationDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0"
            + ");";

    /**
     * Table individualevaluations
     */
    public static final String INDIVIDUALEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + IndividualEvaluationDAO.TABLE + ";";
    public static final String INDIVIDUALEVALUATION_TABLE_CREATE = "CREATE TABLE " + IndividualEvaluationDAO.TABLE
            + " ("
                + "column_pk INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IndividualEvaluationDAO.COLUMN_ID + " INTEGER NOT NULL UNIQUE, "
                + IndividualEvaluationDAO.COLUMN_MARK + " FLOAT NOT NULL, "
                + IndividualEvaluationDAO.COLUMN_STUDENT_ID + " INTEGER NOT NULL, "
                + IndividualEvaluationDAO.COLUMN_REPORT_ID + " INTEGER NOT NULL, "
                + IndividualEvaluationDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0"
            + ");";


    public SQLiteDBHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(TEACHER_TABLE_CREATE);
        db.execSQL(PROJECT_TABLE_CREATE);
        db.execSQL(STUDENT_TABLE_CREATE);
        db.execSQL(TEAM_TABLE_CREATE);
        db.execSQL(STUDENTTEAM_TABLE_CREATE);
        db.execSQL(RUNNING_TABLE_CREATE);
        db.execSQL(RUNNINGTEAM_TABLE_CREATE);
        db.execSQL(REPORT_TABLE_CREATE);
        db.execSQL(LEADEVALUATION_TABLE_CREATE);
        db.execSQL(INDIVIDUALEVALUATION_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(INDIVIDUALEVALUATION_TABLE_DROP);
        db.execSQL(LEADEVALUATION_TABLE_DROP);
        db.execSQL(REPORT_TABLE_DROP);
        db.execSQL(RUNNINGTEAM_TABLE_DROP);
        db.execSQL(RUNNING_TABLE_DROP);
        db.execSQL(STUDENTTEAM_TABLE_DROP);
        db.execSQL(TEAM_TABLE_DROP);
        db.execSQL(STUDENT_TABLE_DROP);
        db.execSQL(PROJECT_TABLE_DROP);
        db.execSQL(TEACHER_TABLE_DROP);

		onCreate(db);
	}
}
