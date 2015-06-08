package proadmin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Table teacher
     */
    public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS" + TeacherDAO.TEACHER_TABLE_NAME + ";";
    public static final String TEACHER_TABLE_CREATE =
            "CREATE TABLE " + TeacherDAO.TEACHER_TABLE_NAME + " ("
                    + TeacherDAO.TEACHER_ID + " TEXT PRIMARY KEY, "
                    + TeacherDAO.TEACHER_FIRSTNAME + " TEXT NOT NULL, "
                    + TeacherDAO.TEACHER_LASTNAME + " TEXT NOT NULL, "
                    + TeacherDAO.TEACHER_EMAIL + " TEXT NOT NULL, "
                    + TeacherDAO.TEACHER_PASSWORD + " TEXT NOT NULL;";

    /**
     * Table project
     */
    public static final String PROJECT_TABLE_DROP = "DROP PROJECT IF EXISTS" + ProjectDAO.PROJECT_TABLE_NAME + ";";
    public static final String PROJECT_TABLE_CREATE =
            "CREATE TABLE " + ProjectDAO.PROJECT_TABLE_NAME + " ("
                    + ProjectDAO.PROJECT_ID + " TEXT PRIMARY KEY, "
                    + ProjectDAO.PROJECT_TITLE + " TEXT NOT NULL, "
                    + ProjectDAO.PROJECT_LEVEL + " TEXT NOT NULL, "
                    + ProjectDAO.PROJECT_DESCRIPTION + " TEXT NOT NULL)";

    /**
     * Table group
     */
    public static final String GROUP_TABLE_DROP = "DROP TABLE IF EXISTS" + GroupDAO.GROUP_TABLE_NAME + ";";
    public static final String GROUP_TABLE_CREATE =
            "CREATE TABLE " + GroupDAO.GROUP_TABLE_NAME + " ("
                    + GroupDAO.GROUP_ID + " TEXT PRIMARY KEY, "
                    + GroupDAO.GROUP_YEAR +" INTEGER NOT NULL, "
                    + GroupDAO.GROUP_PROJECT_ID +" TEXT NOT NULL, "
                    + GroupDAO.GROUP_TEACHER_ID +" TEXT NOT NULL, "
                    + "FOREIGN KEY (" + GroupDAO.GROUP_PROJECT_ID + ") REFERENCES " + ProjectDAO.PROJECT_TABLE_NAME + "( "+ ProjectDAO.PROJECT_ID + "));";

    /**
     * Table student
     */
    public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS" + StudentDAO.STUDENT_TABLE_NAME + ";";
    public static final String STUDENT_TABLE_CREATE =
            "CREATE TABLE " + StudentDAO.STUDENT_TABLE_NAME + " ("
                    + StudentDAO.STUDENT_ID + " TEXT PRIMARY KEY, "
                    + StudentDAO.STUDENT_FIRSTNAME + " TEXT NOT NULL, "
                    + StudentDAO.STUDENT_LASTNAME + " TEXT NOT NULL, "
                    + StudentDAO.STUDENT_EMAIL + " TEXT NOT NULL);";

    /**
     * Table student_has_group
     */
    public static final String STUDENT_HAS_GROUP_TABLE_DROP = "DROP TABLE IF EXISTS" + StudentHasGroupDAO.STUDENT_HAS_GROUP_TABLE_NAME + ";";
    public static final String STUDENT_HAS_GROUP_TABLE_CREATE =
            "CREATE TABLE " + StudentHasGroupDAO.STUDENT_HAS_GROUP_TABLE_NAME + " ("
                    + StudentHasGroupDAO.STUDENT_HAS_GROUP_STUDENT_ID + " TEXT NOT NULL, "
                    + StudentHasGroupDAO.STUDENT_HAS_GROUP_GROUP_ID +" TEXT NOT NULL, "
                    + "FOREIGN KEY (" + StudentHasGroupDAO.STUDENT_HAS_GROUP_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "( "+ StudentDAO.STUDENT_ID + "), "
                    + "FOREIGN KEY (" + StudentHasGroupDAO.STUDENT_HAS_GROUP_GROUP_ID + ") REFERENCES " + GroupDAO.GROUP_TABLE_NAME + "( "+ GroupDAO.GROUP_ID + "));";

    /**
     * Table report
     */
    public static final String REPORT_TABLE_DROP = "DROP TABLE IF EXISTS" + ReportDAO.REPORT_TABLE_NAME + ";";
    public static final String REPORT_TABLE_CREATE =
            "CREATE TABLE " + ReportDAO.REPORT_TABLE_NAME + " ("
                    + ReportDAO.REPORT_ID + " TEXT PRIMARY KEY, "
                    + ReportDAO.REPORT_NUMBER_WEEK + " INTEGER NOT NULL, "
                    + ReportDAO.REPORT_PLANNING_NOTE + " INTEGER NOT NULL, "
                    + ReportDAO.REPORT_PLANNING_COMMENT + " TEXT, "
                    + ReportDAO.REPORT_COMMUNICATION_NOTE + " INTEGER NOT NULL, "
                    + ReportDAO.REPORT_COMMUNICATION_COMMENT + " TEXT, "
                    + ReportDAO.REPORT_COMMENT + " TEXT, "
                    + ReportDAO.REPORT_GROUP_ID + " TEXT NOT NULL, "
                    + ReportDAO.REPORT_STUDENT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + ReportDAO.REPORT_GROUP_ID + ") REFERENCES " + GroupDAO.GROUP_TABLE_NAME + "( "+ GroupDAO.GROUP_ID + "), "
                    + "FOREIGN KEY (" + ReportDAO.REPORT_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "( "+ StudentDAO.STUDENT_ID + "));";

    /**
     * Table note
     */
    public static final String NOTE_TABLE_DROP = "DROP TABLE IF EXISTS" + NoteDAO.NOTE_TABLE_NAME + ";";
    public static final String NOTE_TABLE_CREATE =
            "CREATE TABLE " + NoteDAO.NOTE_TABLE_NAME + " ("
                    + NoteDAO.NOTE_NOTE + " INTEGER NOT NULL, "
                    + NoteDAO.NOTE_STUDENT_ID + " TEXT NOT NULL, "
                    + NoteDAO.NOTE_REPORT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + NoteDAO.NOTE_STUDENT_ID + ") REFERENCES " + StudentDAO.STUDENT_TABLE_NAME + "( "+ StudentDAO.STUDENT_ID + "), "
                    + "FOREIGN KEY (" + NoteDAO.NOTE_REPORT_ID + ") REFERENCES " + ReportDAO.REPORT_TABLE_NAME + "( "+ ReportDAO.REPORT_ID + "));";

    /**
     * Constructor
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(TEACHER_TABLE_CREATE);
        db.execSQL(PROJECT_TABLE_CREATE);
		db.execSQL(GROUP_TABLE_CREATE);
		db.execSQL(STUDENT_TABLE_CREATE);
		db.execSQL(STUDENT_HAS_GROUP_TABLE_CREATE);
		db.execSQL(REPORT_TABLE_CREATE);
		db.execSQL(NOTE_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NOTE_TABLE_DROP);
        db.execSQL(REPORT_TABLE_DROP);
        db.execSQL(STUDENT_HAS_GROUP_TABLE_DROP);
        db.execSQL(STUDENT_TABLE_DROP);
        db.execSQL(GROUP_TABLE_DROP);
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
