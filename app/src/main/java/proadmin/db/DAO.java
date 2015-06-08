package proadmin.db;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.Iterator;

import proadmin.content.Group;
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
	private static GroupDAO groupDAO;
	private static StudentDAO studentDAO;
	private static StudentHasGroupDAO studentHasGroupDAO;
	private static ReportDAO reportDAO;
	private static NoteDAO noteDAO;

	protected DAO() {}

    //Methode d'ouverture de la base de donnees
	public static SQLiteDatabase open(Context context) {
		mHandler = new DatabaseHandler(context, NOM, null, VERSION);
		mDb = mHandler.getWritableDatabase();

		teacherDAO = new TeacherDAO(context, mDb);
		projectDAO = new ProjectDAO(context, mDb);
		groupDAO = new GroupDAO(context, mDb);
		studentDAO = new StudentDAO(context, mDb);
		studentHasGroupDAO = new StudentHasGroupDAO(context, mDb);
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
		groupDAO.deleteAllOfTeacher(teacherId);
		teacherDAO.delete(teacherId);
	}

	public static Teacher selectTeacher(String teacherId) {
		return teacherDAO.select(teacherId);
	}

	public static Teacher selectTeacher(String email, String password) {
		return teacherDAO.select(email, password);
	}

	public static void insertProject(Project project) {
		projectDAO.insert(project);
	}

	public static void updateProject(Project project) {
		projectDAO.update(project);
	}

	public static void deleteProject(String projectId) {
		groupDAO.deleteAllOfProject(projectId);
		projectDAO.delete(projectId);
	}

	public static Project selectProject(String projectId) {
		return projectDAO.select(projectId);
	}

	public static void insertGroup(Group group) {
		long rowId = groupDAO.insert(group);

		if (rowId > 0) {
			Iterator it = group.getListStudents().iterator();

			Student student;
			while (it.hasNext()) {
				student = (Student) it.next();
				insertStudent(student, group.getId());
			}

			it = group.getListReports().iterator();

			Report report;
			while (it.hasNext()) {
				report = (Report) it.next();
				insertReport(report, group.getId());
			}
		}
	}

	public static void updateGroup(Group group) {
		groupDAO.update(group);
	}

	public static void deleteGroup(String groupId) {
		reportDAO.deleteAllOfGroup(groupId);
		studentHasGroupDAO.deleteStudentsIds(groupId);
		groupDAO.delete(groupId);
	}

	public static Group selectGroup(String groupId) {
		return groupDAO.select(groupId);
	}

	public static void insertStudent(Student student, String groupId) {
		if (!studentDAO.contains(student.getId())) {
			studentDAO.insert(student);
		}

		studentHasGroupDAO.insert(student.getId(), groupId);
	}

	public static void updateStudent(Student student) {
		studentDAO.update(student);
	}

	public static Student selectStudent(String studentId) {
		return studentDAO.select(studentId);
	}

	public static void insertReport(Report report, String groupId) {
		reportDAO.insert(report, groupId);
	}

	public static void updateReport(Report report, String groupeId) {
		reportDAO.update(report, groupeId);
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
