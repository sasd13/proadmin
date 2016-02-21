package db;

import com.sasd13.proadmin.core.db.IndividualEvaluationDAO;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;
import com.sasd13.proadmin.core.db.ProjectDAO;
import com.sasd13.proadmin.core.db.ReportDAO;
import com.sasd13.proadmin.core.db.RunningDAO;
import com.sasd13.proadmin.core.db.StudentDAO;
import com.sasd13.proadmin.core.db.StudentTeamDAO;
import com.sasd13.proadmin.core.db.TeacherDAO;
import com.sasd13.proadmin.core.db.TeamDAO;

public interface DBHandler {
	
	/**
	 * Table teachers
	 */
	public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS " + TeacherDAO.TABLE + ";";
	public static final String TEACHER_TABLE_CREATE = "CREATE TABLE " + TeacherDAO.TABLE 
			+ " (" 
				+ TeacherDAO.COLUMN_ID + " SERIAL, "
				+ TeacherDAO.COLUMN_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, "
				+ TeacherDAO.COLUMN_FIRSTNAME + " VARCHAR(255) NOT NULL, " 
				+ TeacherDAO.COLUMN_LASTNAME + " VARCHAR(255) NOT NULL, "
				+ TeacherDAO.COLUMN_EMAIL + " VARCHAR(255) NOT NULL, " 
				+ TeacherDAO.COLUMN_PASSWORD + " VARCHAR(255) NOT NULL, " 
				+ TeacherDAO.COLUMN_DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, "
				+ "PRIMARY KEY (" + TeacherDAO.COLUMN_ID + ")"
			+ ");";
	
	/**
	 * Table projects
	 */
	public static final String PROJECT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectDAO.TABLE + ";";
	public static final String PROJECT_TABLE_CREATE = "CREATE TABLE " + ProjectDAO.TABLE 
			+ " (" 
				+ ProjectDAO.COLUMN_ID + " SERIAL, " 
				+ ProjectDAO.COLUMN_CODE + " VARCHAR(255) NOT NULL UNIQUE, "
				+ ProjectDAO.COLUMN_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, " 
				+ ProjectDAO.COLUMN_TITLE + " VARCHAR(255) NOT NULL, "
				+ ProjectDAO.COLUMN_DESCRIPTION + " TEXT NOT NULL, " 
				+ ProjectDAO.COLUMN_DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, "
				+ "PRIMARY KEY (" + ProjectDAO.COLUMN_ID + ")"
			+ ");";
	
	/**
	 * Table runnings
	 */
	public static final String RUNNING_TABLE_DROP = "DROP TABLE IF EXISTS " + RunningDAO.TABLE + ";";
	public static final String RUNNING_TABLE_CREATE = "CREATE TABLE " + RunningDAO.TABLE 
			+ " (" 
				+ RunningDAO.COLUMN_ID + " SERIAL, " 
				+ RunningDAO.COLUMN_YEAR + " INT NOT NULL, "
				+ RunningDAO.COLUMN_TEACHER_ID + " BIGINT UNSIGNED NOT NULL, "
				+ RunningDAO.COLUMN_PROJECT_ID + " BIGINT UNSIGNED NOT NULL, "
				+ RunningDAO.COLUMN_DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, "
				+ "PRIMARY KEY (" + RunningDAO.COLUMN_ID + ")"
				+ ", FOREIGN KEY (" + RunningDAO.COLUMN_TEACHER_ID + ") REFERENCES " + TeacherDAO.TABLE + "(" + TeacherDAO.COLUMN_ID + ")" 
				+ ", FOREIGN KEY (" + RunningDAO.COLUMN_PROJECT_ID + ") REFERENCES " + ProjectDAO.TABLE + "(" + ProjectDAO.COLUMN_ID + ")"
			+ ");";
	
	/**
	 * Table teams
	 */
	public static final String TEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + TeamDAO.TABLE + ";";
	public static final String TEAM_TABLE_CREATE = "CREATE TABLE " + TeamDAO.TABLE 
			+ " (" 
				+ TeamDAO.COLUMN_ID + " SERIAL, " 
				+ TeamDAO.COLUMN_CODE + " VARCHAR(255) NOT NULL, " 
				+ TeamDAO.COLUMN_RUNNING_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ TeamDAO.COLUMN_DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, "
				+ "PRIMARY KEY (" + TeamDAO.COLUMN_ID + ")"
				+ ", FOREIGN KEY (" + TeamDAO.COLUMN_RUNNING_ID + ") REFERENCES " + RunningDAO.TABLE + "(" + RunningDAO.COLUMN_ID + ")" 
			+ ");";
	
	/**
	 * Table students
	 */
	public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentDAO.TABLE + ";";
	public static final String STUDENT_TABLE_CREATE = "CREATE TABLE " + StudentDAO.TABLE 
			+ " (" 
				+ StudentDAO.COLUMN_ID + " SERIAL, " 
				+ StudentDAO.COLUMN_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, " 
				+ StudentDAO.COLUMN_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, " 
				+ StudentDAO.COLUMN_FIRSTNAME + " VARCHAR(255) NOT NULL, "
				+ StudentDAO.COLUMN_LASTNAME + " VARCHAR(255) NOT NULL, " 
				+ StudentDAO.COLUMN_EMAIL + " VARCHAR(255) NOT NULL, " 
				+ StudentDAO.COLUMN_DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, "
				+ "PRIMARY KEY (" + StudentDAO.COLUMN_ID + ")"
			+ ");";
	
	/**
	 * Table studentteams
	 */
	public static final String STUDENTTEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentTeamDAO.TABLE + ";";
	public static final String STUDENTTEAM_TABLE_CREATE = "CREATE TABLE " + StudentTeamDAO.TABLE 
			+ " ("
				+ StudentTeamDAO.COLUMN_ID + " SERIAL, "
				+ StudentTeamDAO.COLUMN_TEAM_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ StudentTeamDAO.COLUMN_STUDENT_ID + " BIGINT UNSIGNED NOT NULL, "
				+ "PRIMARY KEY (" + StudentTeamDAO.COLUMN_ID + ")"
				+ ", FOREIGN KEY (" + StudentTeamDAO.COLUMN_TEAM_ID + ") REFERENCES " + TeamDAO.TABLE + "(" + TeamDAO.COLUMN_ID + ")" 
				+ ", FOREIGN KEY (" + StudentTeamDAO.COLUMN_STUDENT_ID + ") REFERENCES " + StudentDAO.TABLE + "(" + StudentDAO.COLUMN_ID + ")" 
				+ ", CONSTRAINT UNIQUE_CONSTRAINT UNIQUE (" + StudentTeamDAO.COLUMN_TEAM_ID + ", " + StudentTeamDAO.COLUMN_STUDENT_ID + ")" 
			+ ");";
	
	/**
	 * Table reports
	 */
	public static final String REPORT_TABLE_DROP = "DROP TABLE IF EXISTS " + ReportDAO.TABLE + ";";
	public static final String REPORT_TABLE_CREATE = "CREATE TABLE " + ReportDAO.TABLE 
			+ " (" 
				+ ReportDAO.COLUMN_ID + " SERIAL, " 
				+ ReportDAO.COLUMN_DATEMEETING + " VARCHAR(255) NOT NULL, "
				+ ReportDAO.COLUMN_WEEK + " INT NOT NULL, " 
				+ ReportDAO.COLUMN_TEAMCOMMENT + " TEXT, " 
				+ ReportDAO.COLUMN_TEAM_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ ReportDAO.COLUMN_DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, "
				+ "PRIMARY KEY (" + ReportDAO.COLUMN_ID + ")"
				+ ", FOREIGN KEY (" + ReportDAO.COLUMN_TEAM_ID + ") REFERENCES " + TeamDAO.TABLE + "(" + TeamDAO.COLUMN_ID + ")" 
			+ ");";
	
	/**
	 * Table leadevaluations
	 */
	public static final String LEADEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + LeadEvaluationDAO.TABLE + ";";
	public static final String LEADEVALUATION_TABLE_CREATE = "CREATE TABLE " + LeadEvaluationDAO.TABLE 
			+ " ("
				+ LeadEvaluationDAO.COLUMN_ID + " SERIAL, "  
				+ LeadEvaluationDAO.COLUMN_PLANNINGMARK + " DOUBLE NOT NULL, " 
				+ LeadEvaluationDAO.COLUMN_PLANNINGCOMMENT + " TEXT, "
				+ LeadEvaluationDAO.COLUMN_COMMUNICATIONMARK + " DOUBLE NOT NULL, " 
				+ LeadEvaluationDAO.COLUMN_COMMUNICATIONCOMMENT + " TEXT, " 
				+ LeadEvaluationDAO.COLUMN_REPORT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ LeadEvaluationDAO.COLUMN_STUDENT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ LeadEvaluationDAO.COLUMN_DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, " 
				+ "PRIMARY KEY (" + LeadEvaluationDAO.COLUMN_ID + ")"
				+ ", FOREIGN KEY (" + LeadEvaluationDAO.COLUMN_REPORT_ID + ") REFERENCES " + ReportDAO.TABLE + "(" + ReportDAO.COLUMN_ID + ")" 
				+ ", FOREIGN KEY (" + LeadEvaluationDAO.COLUMN_STUDENT_ID + ") REFERENCES " + StudentDAO.TABLE + "(" + StudentDAO.COLUMN_ID + ")" 
			+ ");";
	
	/**
	 * Table individualevaluations
	 */
	public static final String INDIVIDUALEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + IndividualEvaluationDAO.TABLE + ";";
	public static final String INDIVIDUALEVALUATION_TABLE_CREATE = "CREATE TABLE " + IndividualEvaluationDAO.TABLE 
			+ " ("
				+ IndividualEvaluationDAO.COLUMN_ID + " SERIAL, "
				+ IndividualEvaluationDAO.COLUMN_MARK + " DOUBLE NOT NULL, " 
				+ IndividualEvaluationDAO.COLUMN_REPORT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ IndividualEvaluationDAO.COLUMN_STUDENT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ IndividualEvaluationDAO.COLUMN_DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, "
				+ "PRIMARY KEY (" + IndividualEvaluationDAO.COLUMN_ID + ")"
				+ ", FOREIGN KEY (" + IndividualEvaluationDAO.COLUMN_REPORT_ID + ") REFERENCES " + ReportDAO.TABLE + "(" + ReportDAO.COLUMN_ID + ")" 
				+ ", FOREIGN KEY (" + IndividualEvaluationDAO.COLUMN_STUDENT_ID + ") REFERENCES " + StudentDAO.TABLE + "(" + StudentDAO.COLUMN_ID + ")" 
			+ ");";
	
}
