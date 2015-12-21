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
	public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS " + TeacherDAO.TEACHER_TABLE_NAME + ";";
	public static final String TEACHER_TABLE_CREATE = "CREATE TABLE " 
			+ TeacherDAO.TEACHER_TABLE_NAME + " (" 
				+ TeacherDAO.TEACHER_ID + " SERIAL PRIMARY KEY, "
				+ TeacherDAO.TEACHER_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, "
				+ TeacherDAO.TEACHER_FIRSTNAME + " VARCHAR(255) NOT NULL, " 
				+ TeacherDAO.TEACHER_LASTNAME + " VARCHAR(255) NOT NULL, "
				+ TeacherDAO.TEACHER_EMAIL + " VARCHAR(255) NOT NULL, " 
				+ TeacherDAO.TEACHER_PASSWORD + " VARCHAR(255) NOT NULL, " 
				+ TeacherDAO.DELETED + " BOOLEAN NOT NULL DEFAULT FALSE"
			+ ");";
	
	/**
	 * Table projects
	 */
	public static final String PROJECT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectDAO.PROJECT_TABLE_NAME + ";";
	public static final String PROJECT_TABLE_CREATE = "CREATE TABLE " 
			+ ProjectDAO.PROJECT_TABLE_NAME + " (" 
				+ ProjectDAO.PROJECT_ID + " SERIAL PRIMARY KEY, " 
				+ ProjectDAO.PROJECT_CODE + " VARCHAR(255) NOT NULL, "
				+ ProjectDAO.PROJECT_ACADEMICLEVEL + " VARCHAR(255) NOT NULL, " 
				+ ProjectDAO.PROJECT_TITLE + " VARCHAR(255) NOT NULL, "
				+ ProjectDAO.PROJECT_DESCRIPTION + " TEXT NOT NULL, " 
				+ ProjectDAO.DELETED + " BOOLEAN NOT NULL DEFAULT FALSE"
			+ ");";
	
	/**
	 * Table runnings
	 */
	public static final String RUNNING_TABLE_DROP = "DROP TABLE IF EXISTS " + RunningDAO.RUNNING_TABLE_NAME + ";";
	public static final String RUNNING_TABLE_CREATE = "CREATE TABLE " 
			+ RunningDAO.RUNNING_TABLE_NAME + " (" 
				+ RunningDAO.RUNNING_ID + " SERIAL PRIMARY KEY, " 
				+ RunningDAO.RUNNING_YEAR + " INT NOT NULL, " 
				+ RunningDAO.DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, " 
				+ RunningDAO.TEACHERS_TEACHER_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ RunningDAO.PROJECTS_PROJECT_ID + " BIGINT UNSIGNED NOT NULL, "
				+ "FOREIGN KEY (" + RunningDAO.TEACHERS_TEACHER_ID + ") REFERENCES " + TeacherDAO.TEACHER_TABLE_NAME + "(" + TeacherDAO.TEACHER_ID + ")" 
				+ ", FOREIGN KEY (" + RunningDAO.PROJECTS_PROJECT_ID + ") REFERENCES " + ProjectDAO.PROJECT_TABLE_NAME + "(" + ProjectDAO.PROJECT_ID + ")"
			+ ");";
	
	/**
	 * Table teams
	 */
	public static final String TEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + TeamDAO.TEAM_TABLE_NAME + ";";
	public static final String TEAM_TABLE_CREATE = "CREATE TABLE " 
			+ TeamDAO.TEAM_TABLE_NAME + " (" 
				+ TeamDAO.TEAM_ID + " SERIAL PRIMARY KEY, " 
				+ TeamDAO.TEAM_CODE + " VARCHAR(255) NOT NULL, " 
				+ TeamDAO.DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, " 
				+ TeamDAO.RUNNINGS_RUNNING_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ "FOREIGN KEY (" + TeamDAO.RUNNINGS_RUNNING_ID + ") REFERENCES " + RunningDAO.RUNNING_TABLE_NAME + "(" + RunningDAO.RUNNING_ID + ")" 
			+ ");";
	
	/**
	 * Table students
	 */
	public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentDAO.STUDENT_TABLE_NAME + ";";
	public static final String STUDENT_TABLE_CREATE = "CREATE TABLE " 
			+ StudentDAO.STUDENT_TABLE_NAME + " (" 
				+ StudentDAO.STUDENT_ID + " SERIAL PRIMARY KEY, " 
				+ StudentDAO.STUDENT_NUMBER + " VARCHAR(255) NOT NULL UNIQUE, " 
				+ StudentDAO.STUDENT_ACADEMICLEVEL + " INT NOT NULL, " 
				+ StudentDAO.STUDENT_FIRSTNAME + " VARCHAR(255) NOT NULL, "
				+ StudentDAO.STUDENT_LASTNAME + " VARCHAR(255) NOT NULL, " 
				+ StudentDAO.STUDENT_EMAIL + " VARCHAR(255) NOT NULL, " 
				+ StudentDAO.DELETED + " BOOLEAN NOT NULL DEFAULT FALSE"
			+ ");";
	
	/**
	 * Table studentteams
	 */
	public static final String STUDENTTEAM_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentTeamDAO.STUDENTTEAM_TABLE_NAME + ";";
	public static final String STUDENTTEAM_TABLE_CREATE = "CREATE TABLE " 
			+ StudentTeamDAO.STUDENTTEAM_TABLE_NAME + " ("
				+ StudentTeamDAO.TEAMS_TEAM_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ StudentTeamDAO.STUDENTS_STUDENT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ "PRIMARY KEY (" + StudentTeamDAO.TEAMS_TEAM_ID + ", " + StudentTeamDAO.STUDENTS_STUDENT_ID + ")" 
				+ ", FOREIGN KEY (" + StudentTeamDAO.TEAMS_TEAM_ID + ") REFERENCES " + TeamDAO.TEAM_TABLE_NAME + "(" + TeamDAO.TEAM_ID + ")" 
				+ ", FOREIGN KEY (" + StudentTeamDAO.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "(" + StudentDAO.STUDENT_ID + ")" 
			+ ");";
	
	/**
	 * Table reports
	 */
	public static final String REPORT_TABLE_DROP = "DROP TABLE IF EXISTS " + ReportDAO.REPORT_TABLE_NAME + ";";
	public static final String REPORT_TABLE_CREATE = "CREATE TABLE " 
			+ ReportDAO.REPORT_TABLE_NAME + " (" 
				+ ReportDAO.REPORT_ID + " SERIAL PRIMARY KEY, " 
				+ ReportDAO.REPORT_DATEMEETING + " VARCHAR(255) NOT NULL, "
				+ ReportDAO.REPORT_WEEKNUMBER + " INT NOT NULL, " 
				+ ReportDAO.REPORT_TEAMCOMMENT + " TEXT, " 
				+ ReportDAO.DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, " 
				+ ReportDAO.TEAMS_TEAM_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ "FOREIGN KEY (" + ReportDAO.TEAMS_TEAM_ID + ") REFERENCES " + TeamDAO.TEAM_TABLE_NAME + "(" + TeamDAO.TEAM_ID + ")" 
			+ ");";
	
	/**
	 * Table leadevaluations
	 */
	public static final String LEADEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + LeadEvaluationDAO.LEADEVALUATION_TABLE_NAME + ";";
	public static final String LEADEVALUATION_TABLE_CREATE = "CREATE TABLE " 
			+ LeadEvaluationDAO.LEADEVALUATION_TABLE_NAME + " ("
				+ LeadEvaluationDAO.LEADEVALUATION_ID + " SERIAL PRIMARY KEY, " 
				+ LeadEvaluationDAO.LEADEVALUATION_PLANNINGMARK + " DOUBLE NOT NULL, " 
				+ LeadEvaluationDAO.LEADEVALUATION_PLANNINGCOMMENT + " TEXT, "
				+ LeadEvaluationDAO.LEADEVALUATION_COMMUNICATIONMARK + " DOUBLE NOT NULL, " 
				+ LeadEvaluationDAO.LEADEVALUATION_COMMUNICATIONCOMMENT + " TEXT, " 
				+ LeadEvaluationDAO.DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, " 
				+ LeadEvaluationDAO.STUDENTS_STUDENT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ LeadEvaluationDAO.REPORTS_REPORT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ "FOREIGN KEY (" + LeadEvaluationDAO.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "(" + StudentDAO.STUDENT_ID + ")" 
				+ ", FOREIGN KEY (" + LeadEvaluationDAO.REPORTS_REPORT_ID + ") REFERENCES " + ReportDAO.REPORT_TABLE_NAME + "(" + ReportDAO.REPORT_ID + ")" 
			+ ");";
	
	/**
	 * Table individualevaluations
	 */
	public static final String INDIVIDUALEVALUATION_TABLE_DROP = "DROP TABLE IF EXISTS " + IndividualEvaluationDAO.INDIVIDUALEVALUATION_TABLE_NAME + ";";
	public static final String INDIVIDUALEVALUATION_TABLE_CREATE = "CREATE TABLE " 
			+ IndividualEvaluationDAO.INDIVIDUALEVALUATION_TABLE_NAME + " ("
				+ IndividualEvaluationDAO.INDIVIDUALEVALUATION_ID + " SERIAL PRIMARY KEY, "
				+ IndividualEvaluationDAO.INDIVIDUALEVALUATION_MARK + " DOUBLE NOT NULL, " 
				+ IndividualEvaluationDAO.DELETED + " BOOLEAN NOT NULL DEFAULT FALSE, " 
				+ IndividualEvaluationDAO.STUDENTS_STUDENT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ IndividualEvaluationDAO.REPORTS_REPORT_ID + " BIGINT UNSIGNED NOT NULL, " 
				+ "FOREIGN KEY (" + IndividualEvaluationDAO.STUDENTS_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "(" + StudentDAO.STUDENT_ID + ")" 
				+ ", FOREIGN KEY (" + IndividualEvaluationDAO.REPORTS_REPORT_ID + ") REFERENCES " + ReportDAO.REPORT_TABLE_NAME + "(" + ReportDAO.REPORT_ID + ")" 
			+ ");";
	
}
