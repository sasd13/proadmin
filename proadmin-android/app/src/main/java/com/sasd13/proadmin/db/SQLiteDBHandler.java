package com.sasd13.proadmin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.sasd13.proadmin.core.db.IndividualEvaluationDAO;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;
import com.sasd13.proadmin.core.db.ProjectDAO;
import com.sasd13.proadmin.core.db.ReportDAO;
import com.sasd13.proadmin.core.db.RunningDAO;
import com.sasd13.proadmin.core.db.StudentDAO;
import com.sasd13.proadmin.core.db.StudentTeamDAO;
import com.sasd13.proadmin.core.db.TeacherDAO;
import com.sasd13.proadmin.core.db.TeamDAO;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table teachers
     */
    public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS " + TeacherDAO.TABLE + ";";
    public static final String TEACHER_TABLE_CREATE = "CREATE TABLE " + TeacherDAO.TABLE
            + " ("
                + TeacherDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + TeacherDAO.COLUMN_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, "
                + TeacherDAO.COLUMN_FIRSTNAME + " VARCHAR(255) NOT NULL, "
                + TeacherDAO.COLUMN_LASTNAME + " VARCHAR(255) NOT NULL, "
                + TeacherDAO.COLUMN_EMAIL + " VARCHAR(255) NOT NULL, "
                + TeacherDAO.COLUMN_PASSWORD + " VARCHAR(255) NOT NULL, "
                + TeacherDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + TeacherDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table projects
     */
    public static final String PROJECT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectDAO.TABLE + ";";
    public static final String PROJECT_TABLE_CREATE = "CREATE TABLE " + ProjectDAO.TABLE
            + " ("
                + ProjectDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + ProjectDAO.COLUMN_CODE + " VARCHAR(255) NOT NULL, "
                + ProjectDAO.COLUMN_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, "
                + ProjectDAO.COLUMN_TITLE + " VARCHAR(255) NOT NULL, "
                + ProjectDAO.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + ProjectDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + ProjectDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table runnings
     */
    public static final String RUNNING_TABLE_DROP = "DROP TABLE IF EXISTS " + RunningDAO.TABLE + ";";
    public static final String RUNNING_TABLE_CREATE = "CREATE TABLE " + RunningDAO.TABLE
            + " ("
                + RunningDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + RunningDAO.COLUMN_YEAR + " INT NOT NULL, "
                + RunningDAO.COLUMN_TEACHER_ID + " INTEGER NOT NULL, "
                + RunningDAO.COLUMN_PROJECT_ID + " INTEGER NOT NULL, "
                + RunningDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + RunningDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + RunningDAO.COLUMN_TEACHER_ID + ") REFERENCES " + TeacherDAO.TABLE + "("+ TeacherDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + RunningDAO.COLUMN_PROJECT_ID + ") REFERENCES " + ProjectDAO.TABLE + "("+ ProjectDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table teams
     */
    public static final String TEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + TeamDAO.TABLE + ";";
    public static final String TEAM_TABLE_CREATE = "CREATE TABLE " + TeamDAO.TABLE
            + " ("
                + TeamDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + TeamDAO.COLUMN_CODE + " VARCHAR(255) NOT NULL, "
                + TeamDAO.COLUMN_RUNNING_ID + " INTEGER NOT NULL, "
                + TeamDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + TeamDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + TeamDAO.COLUMN_RUNNING_ID + ") REFERENCES " + RunningDAO.TABLE + "("+ RunningDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table students
     */
    public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentDAO.TABLE + ";";
    public static final String STUDENT_TABLE_CREATE = "CREATE TABLE " + StudentDAO.TABLE
            + " ("
                + StudentDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + StudentDAO.COLUMN_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, "
                + StudentDAO.COLUMN_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, "
                + StudentDAO.COLUMN_FIRSTNAME + " VARCHAR(255) NOT NULL, "
                + StudentDAO.COLUMN_LASTNAME + " VARCHAR(255) NOT NULL, "
                + StudentDAO.COLUMN_EMAIL + " VARCHAR(255) NOT NULL, "
                + StudentDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + StudentDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table studentteams
     */
    public static final String STUDENTTEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentTeamDAO.TABLE + ";";
    public static final String STUDENTTEAM_TABLE_CREATE = "CREATE TABLE " + StudentTeamDAO.TABLE
            + " ("
                + StudentTeamDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + StudentTeamDAO.COLUMN_TEAM_ID + " INTEGER NOT NULL, "
                + StudentTeamDAO.COLUMN_STUDENT_ID + " INTEGER NOT NULL, "
                + "PRIMARY KEY (" + StudentTeamDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + StudentTeamDAO.COLUMN_TEAM_ID + ") REFERENCES " + TeamDAO.TABLE + "("+ TeamDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + StudentTeamDAO.COLUMN_STUDENT_ID + ") REFERENCES " + StudentDAO.TABLE + "("+ StudentDAO.COLUMN_ID + ")"
                + ", CONSTRAINT UNIQUE_CONSTRAINT UNIQUE (" + StudentTeamDAO.COLUMN_TEAM_ID + ", " + StudentTeamDAO.COLUMN_STUDENT_ID + ")"
            + ");";

    /**
     * Table reports
     */
    public static final String REPORT_TABLE_DROP = "DROP TABLE IF EXISTS " + ReportDAO.TABLE + ";";
    public static final String REPORT_TABLE_CREATE = "CREATE TABLE " + ReportDAO.TABLE
            + " ("
                + ReportDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + ReportDAO.COLUMN_DATEMEETING + " VARCHAR(255) NOT NULL, "
                + ReportDAO.COLUMN_WEEKNUMBER + " INT NOT NULL, "
                + ReportDAO.COLUMN_TEAMCOMMENT + " TEXT, "
                + ReportDAO.COLUMN_TEAM_ID + " INTEGER NOT NULL, "
                + ReportDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + ReportDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + ReportDAO.COLUMN_TEAM_ID + ") REFERENCES " + TeamDAO.TABLE + "("+ TeamDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table leadevaluations
     */
    public static final String LEADEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + LeadEvaluationDAO.TABLE + ";";
    public static final String LEADEVALUATION_TABLE_CREATE = "CREATE TABLE " + LeadEvaluationDAO.TABLE
            + " ("
                + LeadEvaluationDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + LeadEvaluationDAO.COLUMN_PLANNINGMARK + " FLOAT NOT NULL, "
                + LeadEvaluationDAO.COLUMN_PLANNINGCOMMENT + " TEXT, "
                + LeadEvaluationDAO.COLUMN_COMMUNICATIONMARK + " FLOAT NOT NULL, "
                + LeadEvaluationDAO.COLUMN_COMMUNICATIONCOMMENT + " TEXT, "
                + LeadEvaluationDAO.COLUMN_STUDENT_ID + " INTEGER NOT NULL, "
                + LeadEvaluationDAO.COLUMN_REPORT_ID + " INTEGER NOT NULL, "
                + LeadEvaluationDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + SQLiteLeadEvaluationDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + LeadEvaluationDAO.COLUMN_REPORT_ID + ") REFERENCES " + ReportDAO.TABLE + "("+ ReportDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + LeadEvaluationDAO.COLUMN_STUDENT_ID + ") REFERENCES " + StudentDAO.TABLE + "("+ StudentDAO.COLUMN_ID + ")"
            + ");";

    /**
     * Table individualevaluations
     */
    public static final String INDIVIDUALEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + IndividualEvaluationDAO.TABLE + ";";
    public static final String INDIVIDUALEVALUATION_TABLE_CREATE = "CREATE TABLE " + IndividualEvaluationDAO.TABLE
            + " ("
                + IndividualEvaluationDAO.COLUMN_ID + " INTEGER AUTOINCREMENT, "
                + IndividualEvaluationDAO.COLUMN_MARK + " FLOAT NOT NULL, "
                + IndividualEvaluationDAO.COLUMN_STUDENT_ID + " INTEGER NOT NULL, "
                + IndividualEvaluationDAO.COLUMN_REPORT_ID + " INTEGER NOT NULL, "
                + IndividualEvaluationDAO.COLUMN_DELETED + " INTEGER NOT NULL DEFAULT 0, "
                + "PRIMARY KEY (" + IndividualEvaluationDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + IndividualEvaluationDAO.COLUMN_REPORT_ID + ") REFERENCES " + ReportDAO.TABLE + "("+ ReportDAO.COLUMN_ID + ")"
                + ", FOREIGN KEY (" + IndividualEvaluationDAO.COLUMN_STUDENT_ID + ") REFERENCES " + StudentDAO.TABLE + "("+ StudentDAO.COLUMN_ID + ")"
            + ");";


    public SQLiteDBHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(TEACHER_TABLE_CREATE);
        db.execSQL(PROJECT_TABLE_CREATE);
        db.execSQL(RUNNING_TABLE_CREATE);
        db.execSQL(TEAM_TABLE_CREATE);
        db.execSQL(STUDENT_TABLE_CREATE);
        db.execSQL(STUDENTTEAM_TABLE_CREATE);
        db.execSQL(REPORT_TABLE_CREATE);
        db.execSQL(LEADEVALUATION_TABLE_CREATE);
        db.execSQL(INDIVIDUALEVALUATION_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(INDIVIDUALEVALUATION_TABLE_DROP);
        db.execSQL(LEADEVALUATION_TABLE_DROP);
        db.execSQL(REPORT_TABLE_DROP);
        db.execSQL(STUDENTTEAM_TABLE_DROP);
        db.execSQL(STUDENT_TABLE_DROP);
        db.execSQL(TEAM_TABLE_DROP);
        db.execSQL(RUNNING_TABLE_DROP);
        db.execSQL(PROJECT_TABLE_DROP);
        db.execSQL(TEACHER_TABLE_DROP);

		onCreate(db);
	}
	
	//Methode pour activer la contrainte de cle etrangere a l'ouverture de la base de donnees
	//Par defaut la contrainte n'est pas active dans SQLite
	@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);

	    if (!db.isReadOnly()) {	        
	        db.execSQL("PRAGMA foreign_keys=ON;");
	    }
	}
}
