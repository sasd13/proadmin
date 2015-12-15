package com.sasd13.proadmin.db.sqlite;

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
    public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS " + TeacherDAO.TEACHER_TABLE_NAME + ";";
    public static final String TEACHER_TABLE_CREATE =
            "CREATE TABLE " + TeacherDAO.TEACHER_TABLE_NAME + " ("
                    + TeacherDAO.TEACHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TeacherDAO.TEACHER_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, "
                    + TeacherDAO.TEACHER_FIRSTNAME + " VARCHAR(255) NOT NULL, "
                    + TeacherDAO.TEACHER_LASTNAME + " VARCHAR(255) NOT NULL, "
                    + TeacherDAO.TEACHER_EMAIL + " VARCHAR(255) NOT NULL, "
                    + TeacherDAO.TEACHER_PASSWORD + " VARCHAR(255) NOT NULL);";

    /**
     * Table projects
     */
    public static final String PROJECT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectDAO.PROJECT_TABLE_NAME + ";";
    public static final String PROJECT_TABLE_CREATE =
            "CREATE TABLE " + ProjectDAO.PROJECT_TABLE_NAME + " ("
                    + ProjectDAO.PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ProjectDAO.PROJECT_CODE + " VARCHAR(255) NOT NULL, "
                    + ProjectDAO.PROJECT_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, "
                    + ProjectDAO.PROJECT_TITLE + " VARCHAR(255) NOT NULL, "
                    + ProjectDAO.PROJECT_DESCRIPTION + " TEXT NOT NULL);";

    /**
     * Table runnings
     */
    public static final String RUNNING_TABLE_DROP = "DROP TABLE IF EXISTS " + RunningDAO.RUNNING_TABLE_NAME + ";";
    public static final String RUNNING_TABLE_CREATE =
            "CREATE TABLE " + RunningDAO.RUNNING_TABLE_NAME + " ("
                    + RunningDAO.RUNNING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + RunningDAO.RUNNING_YEAR + " INT NOT NULL, "
                    + RunningDAO.TEACHERS_TEACHER_ID + " INTEGER NOT NULL, "
                    + RunningDAO.PROJECTS_PROJECT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + RunningDAO.TEACHERS_TEACHER_ID + ") REFERENCES " + TeacherDAO.TEACHER_TABLE_NAME + "("+ TeacherDAO.TEACHER_ID + "), "
                    + " FOREIGN KEY (" + RunningDAO.PROJECTS_PROJECT_ID + ") REFERENCES " + ProjectDAO.PROJECT_TABLE_NAME + "("+ ProjectDAO.PROJECT_ID + "));";

    /**
     * Table teams
     */
    public static final String TEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + TeamDAO.TEAM_TABLE_NAME + ";";
    public static final String TEAM_TABLE_CREATE =
            "CREATE TABLE " + TeamDAO.TEAM_TABLE_NAME + " ("
                    + TeamDAO.TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TeamDAO.TEAM_CODE + " VARCHAR(255) NOT NULL, "
                    + TeamDAO.RUNNINGS_RUNNING_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + TeamDAO.RUNNINGS_RUNNING_ID + ") REFERENCES " + RunningDAO.RUNNING_TABLE_NAME + "("+ RunningDAO.RUNNING_ID + "));";

    /**
     * Table students
     */
    public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentDAO.STUDENT_TABLE_NAME + ";";
    public static final String STUDENT_TABLE_CREATE =
            "CREATE TABLE " + StudentDAO.STUDENT_TABLE_NAME + " ("
                    + StudentDAO.STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + StudentDAO.STUDENT_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, "
                    + StudentDAO.STUDENT_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, "
                    + StudentDAO.STUDENT_FIRSTNAME + " VARCHAR(255) NOT NULL, "
                    + StudentDAO.STUDENT_LASTNAME + " VARCHAR(255) NOT NULL, "
                    + StudentDAO.STUDENT_EMAIL + " VARCHAR(255) NOT NULL);";

    /**
     * Table studentteams
     */
    public static final String STUDENTTEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentTeamDAO.STUDENTTEAM_TABLE_NAME + ";";
    public static final String STUDENTTEAM_TABLE_CREATE =
            "CREATE TABLE " + StudentTeamDAO.STUDENTTEAM_TABLE_NAME + " ("
                    + StudentTeamDAO.TEAMS_TEAM_ID + " INTEGER NOT NULL, "
                    + StudentTeamDAO.STUDENTS_STUDENT_ID + " INTEGER NOT NULL, "
                    + " PRIMARY KEY (" + StudentTeamDAO.TEAMS_TEAM_ID + ", " + StudentTeamDAO.STUDENTS_STUDENT_ID + "), "
                    + " FOREIGN KEY (" + StudentTeamDAO.TEAMS_TEAM_ID + ") REFERENCES " + TeamDAO.TEAM_TABLE_NAME + "("+ TeamDAO.TEAM_ID + "), "
                    + " FOREIGN KEY (" + StudentTeamDAO.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "("+ StudentDAO.STUDENT_ID + "));";

    /**
     * Table reports
     */
    public static final String REPORT_TABLE_DROP = "DROP TABLE IF EXISTS " + ReportDAO.REPORT_TABLE_NAME + ";";
    public static final String REPORT_TABLE_CREATE =
            "CREATE TABLE " + ReportDAO.REPORT_TABLE_NAME + " ("
                    + ReportDAO.REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ReportDAO.REPORT_DATEMEETING + " VARCHAR(255) NOT NULL, "
                    + ReportDAO.REPORT_WEEKNUMBER + " INT NOT NULL, "
                    + ReportDAO.REPORT_TEAMCOMMENT + " TEXT, "
                    + ReportDAO.TEAMS_TEAM_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + ReportDAO.TEAMS_TEAM_ID + ") REFERENCES " + TeamDAO.TEAM_TABLE_NAME + "("+ TeamDAO.TEAM_ID + "));";

    /**
     * Table leadevaluations
     */
    public static final String LEADEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + LeadEvaluationDAO.LEADEVALUATION_TABLE_NAME + ";";
    public static final String LEADEVALUATION_TABLE_CREATE =
            "CREATE TABLE " + LeadEvaluationDAO.LEADEVALUATION_TABLE_NAME + " ("
                    + LeadEvaluationDAO.LEADEVALUATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LeadEvaluationDAO.LEADEVALUATION_PLANNINGMARK + " FLOAT NOT NULL, "
                    + LeadEvaluationDAO.LEADEVALUATION_PLANNINGCOMMENT + " TEXT, "
                    + LeadEvaluationDAO.LEADEVALUATION_COMMUNICATIONMARK + " FLOAT NOT NULL, "
                    + LeadEvaluationDAO.LEADEVALUATION_COMMUNICATIONCOMMENT + " TEXT, "
                    + LeadEvaluationDAO.STUDENTS_STUDENT_ID + " INTEGER NOT NULL, "
                    + LeadEvaluationDAO.REPORTS_REPORT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + LeadEvaluationDAO.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "("+ StudentDAO.STUDENT_ID + "), "
                    + " FOREIGN KEY (" + LeadEvaluationDAO.REPORTS_REPORT_ID + ") REFERENCES " + ReportDAO.REPORT_TABLE_NAME + "("+ ReportDAO.REPORT_ID + "));";

    /**
     * Table individualevaluations
     */
    public static final String INDIVIDUALEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + IndividualEvaluationDAO.INDIVIDUALEVALUATION_TABLE_NAME + ";";
    public static final String INDIVIDUALEVALUATION_TABLE_CREATE =
            "CREATE TABLE " + IndividualEvaluationDAO.INDIVIDUALEVALUATION_TABLE_NAME + " ("
                    + IndividualEvaluationDAO.INDIVIDUALEVALUATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + IndividualEvaluationDAO.INDIVIDUALEVALUATION_MARK + " FLOAT NOT NULL, "
                    + LeadEvaluationDAO.STUDENTS_STUDENT_ID + " INTEGER NOT NULL, "
                    + LeadEvaluationDAO.REPORTS_REPORT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + LeadEvaluationDAO.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "("+ StudentDAO.STUDENT_ID + "), "
                    + " FOREIGN KEY (" + LeadEvaluationDAO.REPORTS_REPORT_ID + ") REFERENCES " + ReportDAO.REPORT_TABLE_NAME + "("+ ReportDAO.REPORT_ID + "));";


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
