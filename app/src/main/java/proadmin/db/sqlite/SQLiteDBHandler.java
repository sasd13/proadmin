package proadmin.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import proadmin.db.IndividualEvaluationDAO;
import proadmin.db.LeadEvaluationDAO;
import proadmin.db.ProjectDAO;
import proadmin.db.ReportDAO;
import proadmin.db.RunningDAO;
import proadmin.db.StudentDAO;
import proadmin.db.StudentTeamDAO;
import proadmin.db.TeacherDAO;
import proadmin.db.TeamDAO;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table teachers
     */
    public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS " + TeacherDAO.TEACHER_TABLE_NAME + ";";
    public static final String TEACHER_TABLE_CREATE =
            "CREATE TABLE " + TeacherDAO.TEACHER_TABLE_NAME + " ("
                    + TeacherDAO.TEACHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TeacherDAO.TEACHER_NUMBER + " TEXT NOT NULL UNIQUE, "
                    + TeacherDAO.TEACHER_FIRSTNAME + " TEXT NOT NULL, "
                    + TeacherDAO.TEACHER_LASTNAME + " TEXT NOT NULL, "
                    + TeacherDAO.TEACHER_EMAIL + " TEXT NOT NULL, "
                    + TeacherDAO.TEACHER_PASSWORD + " TEXT NOT NULL);";

    /**
     * Table projects
     */
    public static final String PROJECT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectDAO.PROJECT_TABLE_NAME + ";";
    public static final String PROJECT_TABLE_CREATE =
            "CREATE TABLE " + ProjectDAO.PROJECT_TABLE_NAME + " ("
                    + ProjectDAO.PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ProjectDAO.PROJECT_CODE + " TEXT NOT NULL, "
                    + ProjectDAO.PROJECT_ACADEMICLEVEL + " TEXT NOT NULL, "
                    + ProjectDAO.PROJECT_TITLE + " TEXT NOT NULL, "
                    + ProjectDAO.PROJECT_DESCRIPTION + " TEXT NOT NULL);";

    /**
     * Table runnings
     */
    public static final String RUNNING_TABLE_DROP = "DROP TABLE IF EXISTS " + RunningDAO.RUNNING_TABLE_NAME + ";";
    public static final String RUNNING_TABLE_CREATE =
            "CREATE TABLE " + RunningDAO.RUNNING_TABLE_NAME + " ("
                    + RunningDAO.RUNNING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + RunningDAO.RUNNING_YEAR + " INTEGER NOT NULL, "
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
                    + TeamDAO.TEAM_CODE + " TEXT NOT NULL, "
                    + TeamDAO.RUNNINGS_RUNNING_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + TeamDAO.RUNNINGS_RUNNING_ID + ") REFERENCES " + RunningDAO.RUNNING_TABLE_NAME + "("+ RunningDAO.RUNNING_ID + "));";

    /**
     * Table students
     */
    public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentDAO.STUDENT_TABLE_NAME + ";";
    public static final String STUDENT_TABLE_CREATE =
            "CREATE TABLE " + StudentDAO.STUDENT_TABLE_NAME + " ("
                    + StudentDAO.STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + StudentDAO.STUDENT_NUMBER + " TEXT NOT NULL UNIQUE, "
                    + StudentDAO.STUDENT_ACADEMICLEVEL + " INTEGER NOT NULL, "
                    + StudentDAO.STUDENT_FIRSTNAME + " TEXT NOT NULL, "
                    + StudentDAO.STUDENT_LASTNAME + " TEXT NOT NULL, "
                    + StudentDAO.STUDENT_EMAIL + " TEXT NOT NULL);";

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
                    + ReportDAO.REPORT_DATEMEETING + " TEXT NOT NULL, "
                    + ReportDAO.REPORT_WEEKNUMBER + " INTEGER NOT NULL, "
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
                    + LeadEvaluationDAO.LEADEVALUATION_PLANNINGMARK + " REAL NOT NULL, "
                    + LeadEvaluationDAO.LEADEVALUATION_PLANNINGCOMMENT + " TEXT, "
                    + LeadEvaluationDAO.LEADEVALUATION_COMMUNICATIONMARK + " REAL NOT NULL, "
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
                    + IndividualEvaluationDAO.INDIVIDUALEVALUATION_MARK + " REAL NOT NULL, "
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
