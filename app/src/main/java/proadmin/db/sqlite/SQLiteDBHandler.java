package proadmin.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import proadmin.db.IndividualEvaluationTableAccessor;
import proadmin.db.LeadEvaluationTableAccessor;
import proadmin.db.ProjectTableAccessor;
import proadmin.db.ReportTableAccessor;
import proadmin.db.StudentTableAccessor;
import proadmin.db.StudentTeamTableAccessor;
import proadmin.db.TeacherTableAccessor;
import proadmin.db.TeamTableAccessor;

public class SQLiteDBHandler extends SQLiteOpenHelper {

    /**
     * Table teachers
     */
    public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS " + TeacherTableAccessor.TEACHER_TABLE_NAME + ";";
    public static final String TEACHER_TABLE_CREATE =
            "CREATE TABLE " + TeacherTableAccessor.TEACHER_TABLE_NAME + " ("
                    + TeacherTableAccessor.TEACHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TeacherTableAccessor.TEACHER_FIRSTNAME + " TEXT NOT NULL, "
                    + TeacherTableAccessor.TEACHER_LASTNAME + " TEXT NOT NULL, "
                    + TeacherTableAccessor.TEACHER_EMAIL + " TEXT NOT NULL, "
                    + TeacherTableAccessor.TEACHER_PASSWORD + " TEXT NOT NULL, "
                    + TeacherTableAccessor.TEACHER_DELETED + " INTEGER NOT NULL);";

    /**
     * Table projects
     */
    public static final String PROJECT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectTableAccessor.PROJECT_TABLE_NAME + ";";
    public static final String PROJECT_TABLE_CREATE =
            "CREATE TABLE " + ProjectTableAccessor.PROJECT_TABLE_NAME + " ("
                    + ProjectTableAccessor.PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ProjectTableAccessor.PROJECT_CODE + " TEXT NOT NULL, "
                    + ProjectTableAccessor.PROJECT_ACADEMICLEVEL + " TEXT NOT NULL, "
                    + ProjectTableAccessor.PROJECT_TITLE + " TEXT NOT NULL, "
                    + ProjectTableAccessor.PROJECT_DESCRIPTION + " TEXT NOT NULL, "
                    + ProjectTableAccessor.PROJECT_DELETED + " INTEGER NOT NULL);";

    /**
     * Table teams
     */
    public static final String TEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + TeamTableAccessor.TEAM_TABLE_NAME + ";";
    public static final String TEAM_TABLE_CREATE =
            "CREATE TABLE " + TeamTableAccessor.TEAM_TABLE_NAME + " ("
                    + TeamTableAccessor.TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TeamTableAccessor.TEAM_RUNNINGYEAR + " INTEGER NOT NULL, "
                    + TeamTableAccessor.TEAM_CODE + " TEXT NOT NULL, "
                    + TeamTableAccessor.TEAM_DELETED + " INTEGER NOT NULL);";

    /**
     * Table students
     */
    public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentTableAccessor.STUDENT_TABLE_NAME + ";";
    public static final String STUDENT_TABLE_CREATE =
            "CREATE TABLE " + StudentTableAccessor.STUDENT_TABLE_NAME + " ("
                    + StudentTableAccessor.STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + StudentTableAccessor.STUDENT_NUMBER + " TEXT NOT NULL UNIQUE, "
                    + StudentTableAccessor.STUDENT_ACADEMICLEVEL + " INTEGER NOT NULL, "
                    + StudentTableAccessor.STUDENT_FIRSTNAME + " TEXT NOT NULL, "
                    + StudentTableAccessor.STUDENT_LASTNAME + " TEXT NOT NULL, "
                    + StudentTableAccessor.STUDENT_EMAIL + " TEXT NOT NULL UNIQUE);";

    /**
     * Table studentteams
     */
    public static final String STUDENTTEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentTeamTableAccessor.STUDENTTEAM_TABLE_NAME + ";";
    public static final String STUDENTTEAM_TABLE_CREATE =
            "CREATE TABLE " + StudentTeamTableAccessor.STUDENTTEAM_TABLE_NAME + " ("
                    + StudentTeamTableAccessor.TEAMS_TEAM_ID + " INTEGER NOT NULL, "
                    + StudentTeamTableAccessor.STUDENTS_STUDENT_ID + " INTEGER NOT NULL, "
                    + " PRIMARY KEY (" + StudentTeamTableAccessor.TEAMS_TEAM_ID + ", " + StudentTeamTableAccessor.STUDENTS_STUDENT_ID + "), "
                    + " FOREIGN KEY (" + StudentTeamTableAccessor.TEAMS_TEAM_ID + ") REFERENCES " + TeamTableAccessor.TEAM_TABLE_NAME + "("+ TeamTableAccessor.TEAM_ID + "), "
                    + " FOREIGN KEY (" + StudentTeamTableAccessor.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentTableAccessor.STUDENT_TABLE_NAME + "("+ StudentTableAccessor.STUDENT_ID + "));";

    /**
     * Table reports
     */
    public static final String REPORT_TABLE_DROP = "DROP TABLE IF EXISTS " + ReportTableAccessor.REPORT_TABLE_NAME + ";";
    public static final String REPORT_TABLE_CREATE =
            "CREATE TABLE " + ReportTableAccessor.REPORT_TABLE_NAME + " ("
                    + ReportTableAccessor.REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ReportTableAccessor.REPORT_DATEMEETING + " TEXT NOT NULL, "
                    + ReportTableAccessor.REPORT_WEEKNUMBER + " INTEGER NOT NULL, "
                    + ReportTableAccessor.REPORT_TEAMCOMMENT + " TEXT, "
                    + ReportTableAccessor.REPORT_DELETED + " INTEGER NOT NULL, "
                    + ReportTableAccessor.TEACHERS_TEACHER_ID + " INTEGER NOT NULL, "
                    + ReportTableAccessor.PROJECTS_PROJECT_ID + " INTEGER NOT NULL, "
                    + ReportTableAccessor.TEAMS_TEAM_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + ReportTableAccessor.TEACHERS_TEACHER_ID + ") REFERENCES " + TeacherTableAccessor.TEACHER_TABLE_NAME + "("+ TeacherTableAccessor.TEACHER_ID + "), "
                    + " FOREIGN KEY (" + ReportTableAccessor.PROJECTS_PROJECT_ID + ") REFERENCES " + ProjectTableAccessor.PROJECT_TABLE_NAME + "("+ ProjectTableAccessor.PROJECT_ID + "), "
                    + " FOREIGN KEY (" + ReportTableAccessor.TEAMS_TEAM_ID + ") REFERENCES " + TeamTableAccessor.TEAM_TABLE_NAME + "("+ TeamTableAccessor.TEAM_ID + "));";

    /**
     * Table leadevaluations
     */
    public static final String LEADEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + LeadEvaluationTableAccessor.LEADEVALUATION_TABLE_NAME + ";";
    public static final String LEADEVALUATION_TABLE_CREATE =
            "CREATE TABLE " + LeadEvaluationTableAccessor.LEADEVALUATION_TABLE_NAME + " ("
                    + LeadEvaluationTableAccessor.LEADEVALUATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LeadEvaluationTableAccessor.LEADEVALUATION_PLANNINGMARK + " REAL NOT NULL, "
                    + LeadEvaluationTableAccessor.LEADEVALUATION_PLANNINGCOMMENT + " TEXT, "
                    + LeadEvaluationTableAccessor.LEADEVALUATION_COMMUNICATIONMARK + " REAL NOT NULL, "
                    + LeadEvaluationTableAccessor.LEADEVALUATION_COMMUNICATIONCOMMENT + " TEXT, "
                    + LeadEvaluationTableAccessor.LEADEVALUATION_DELETED + " INTEGER NOT NULL, "
                    + LeadEvaluationTableAccessor.STUDENTS_STUDENT_ID + " INTEGER NOT NULL, "
                    + LeadEvaluationTableAccessor.REPORTS_REPORT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + LeadEvaluationTableAccessor.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentTableAccessor.STUDENT_TABLE_NAME + "("+ StudentTableAccessor.STUDENT_ID + "), "
                    + " FOREIGN KEY (" + LeadEvaluationTableAccessor.REPORTS_REPORT_ID + ") REFERENCES " + ReportTableAccessor.REPORT_TABLE_NAME + "("+ ReportTableAccessor.REPORT_ID + "));";

    /**
     * Table individualevaluations
     */
    public static final String INDIVIDUALEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + IndividualEvaluationTableAccessor.INDIVIDUALEVALUATION_TABLE_NAME + ";";
    public static final String INDIVIDUALEVALUATION_TABLE_CREATE =
            "CREATE TABLE " + IndividualEvaluationTableAccessor.INDIVIDUALEVALUATION_TABLE_NAME + " ("
                    + IndividualEvaluationTableAccessor.INDIVIDUALEVALUATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + IndividualEvaluationTableAccessor.INDIVIDUALEVALUATION_MARK + " REAL NOT NULL, "
                    + IndividualEvaluationTableAccessor.INDIVIDUALEVALUATION_DELETED + " INTEGER NOT NULL, "
                    + LeadEvaluationTableAccessor.STUDENTS_STUDENT_ID + " INTEGER NOT NULL, "
                    + LeadEvaluationTableAccessor.REPORTS_REPORT_ID + " INTEGER NOT NULL, "
                    + " FOREIGN KEY (" + LeadEvaluationTableAccessor.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentTableAccessor.STUDENT_TABLE_NAME + "("+ StudentTableAccessor.STUDENT_ID + "), "
                    + " FOREIGN KEY (" + LeadEvaluationTableAccessor.REPORTS_REPORT_ID + ") REFERENCES " + ReportTableAccessor.REPORT_TABLE_NAME + "("+ ReportTableAccessor.REPORT_ID + "));";


    public SQLiteDBHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(TEACHER_TABLE_CREATE);
        db.execSQL(PROJECT_TABLE_CREATE);
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
        db.execSQL(STUDENTTEAM_TABLE_DROP);
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
