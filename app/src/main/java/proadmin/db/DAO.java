package proadmin.db;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.Iterator;

import proadmin.content.Squad;
import proadmin.content.MapNotes;
import proadmin.content.Project;
import proadmin.content.Report;
import proadmin.content.Student;
import proadmin.content.Teacher;

public class DAO {
	
	//Constante de version de la base de donnees
	protected final static int VERSION = 1;
	
	//Constante du fichier de la base de donnees
	protected final static String NOM = "database.db";
	
	//Objet Base de donnees SQLite
	protected static SQLiteDatabase mDb = null;
	
	//Objet Base de donnees de notre base DatabaseHandler
	protected static DatabaseHandler mHandler = null;

	private static TeacherDAO teacherDAO;
	private static ProjectDAO projectDAO;
	private static ProjectHasYearDAO projectHasYearDAO;
	private static SquadDAO squadDAO;
	private static StudentDAO studentDAO;
	private static StudentHasSquadDAO studentHasSquadDAO;
	private static ReportDAO reportDAO;
	private static NoteDAO noteDAO;

	protected DAO() {}

	//Methode d'ouverture de la base de donnees
	public static void create(Context context) {
		mHandler = new DatabaseHandler(context, NOM, null, VERSION);
    }

    //Methode d'ouverture de la base de donnees
	public static SQLiteDatabase open(Context context) {
		mDb = mHandler.getWritableDatabase();

		teacherDAO = new TeacherDAO(context, mDb);
		projectDAO = new ProjectDAO(context, mDb);
		projectHasYearDAO = new ProjectHasYearDAO(context, mDb);
		squadDAO = new SquadDAO(context, mDb);
		studentDAO = new StudentDAO(context, mDb);
		studentHasSquadDAO = new StudentHasSquadDAO(context, mDb);
		reportDAO = new ReportDAO(context, mDb);
		noteDAO = new NoteDAO(context, mDb);

		return mDb;
	}
	
	//Methode de fermeture de la base de donnees
	public static void close() {
		mDb.close();
	}
	
	//Methode pour renvoyer la base de donnees
	public static SQLiteDatabase getDb() {
		return mDb;
	}

	public static boolean insertTeacher(Teacher teacher) {
		long rowId = teacherDAO.insert(teacher);

		if (rowId > 0) {
			return true;
		}

		return false;
	}

	public static void updateTeacher(Teacher teacher) {
		teacherDAO.update(teacher);
	}

	public static void deleteTeacher(String teacherId) {
		squadDAO.deleteAllOfTeacher(teacherId);
		teacherDAO.delete(teacherId);
	}

	public static Teacher selectTeacher(String teacherId) {
		return teacherDAO.select(teacherId);
	}

	public static Teacher selectTeacher(String email, String password) {
		return teacherDAO.select(email, password);
	}

	public static void insertProject(Project project, long year) {
		if (!projectDAO.contains(project.getId())) {
			projectDAO.insert(project);
		}

		projectHasYearDAO.insert(project.getId(), year);
	}

	public static void updateProject(Project project) {
		projectDAO.update(project);
	}

	public static void deleteProject(String projectId) {
		squadDAO.deleteAllOfProject(projectId);
		projectHasYearDAO.deleteYearsIds(projectId);
		projectDAO.delete(projectId);
	}

	public static void deleteProjectFromYear(String projectId, long year) {
		squadDAO.deleteAllOfYearAndProject(year, projectId);
		projectHasYearDAO.deleteYearsIds(projectId);
	}

	public static Project selectProject(String projectId) {
		return projectDAO.select(projectId);
	}

	public static void insertSquad(Squad squad) {
		long rowId = squadDAO.insert(squad);

		if (rowId > 0) {
			Iterator it = squad.getListStudents().iterator();

			Student student;
			while (it.hasNext()) {
				student = (Student) it.next();
				insertStudent(student, squad.getId());
			}

			it = squad.getListReports().iterator();

			Report report;
			while (it.hasNext()) {
				report = (Report) it.next();
				insertReport(report, squad.getId());
			}
		}
	}

	public static void updateSquad(Squad squad) {
		squadDAO.update(squad);
	}

	public static void deleteSquad(String squadId) {
		reportDAO.deleteAllOfSquad(squadId);
		studentHasSquadDAO.deleteStudentsIds(squadId);
		squadDAO.delete(squadId);
	}

	public static Squad selectSquad(String squadId) {
		return squadDAO.select(squadId);
	}

	public static void insertStudent(Student student, String squadId) {
		if (!studentDAO.contains(student.getId())) {
			studentDAO.insert(student);
		}

		studentHasSquadDAO.insert(student.getId(), squadId);
	}

	public static void updateStudent(Student student) {
		studentDAO.update(student);
	}

	public static void deleteStudent(String studentId) {
		studentHasSquadDAO.deleteSquadsIds(studentId);
		studentDAO.delete(studentId);
	}

	public static void deleteStudentFromSquad(String studentId, String squadId) {
		studentHasSquadDAO.delete(studentId, squadId);
	}

	public static Student selectStudent(String studentId) {
		return studentDAO.select(studentId);
	}

	public static void insertReport(Report report, String squadId) {
		reportDAO.insert(report, squadId);
	}

	public static void updateReport(Report report, String squadId) {
		reportDAO.update(report, squadId);
	}

	public static void deleteReport(String reportId) {
		reportDAO.delete(reportId);
	}

	public static Report selectReport(String reportId) {
		return reportDAO.select(reportId);
	}

	public static void insertNotes(MapNotes mapNotes, String reportId) {
		noteDAO.insert(mapNotes, reportId);
	}

	public static void updateNotes(MapNotes mapNotes, String reportId) {
		noteDAO.update(mapNotes, reportId);
	}

	public static void deleteNotes(String studentId, String reportId) {
		noteDAO.delete(studentId, reportId);
	}

	public static MapNotes selectNotes(String reportId) {
		return noteDAO.selectAllOfReport(reportId);
	}
}
