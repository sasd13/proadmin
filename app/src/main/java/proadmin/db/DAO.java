package proadmin.db;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import proadmin.content.Id;
import proadmin.content.ListIds;
import proadmin.content.ListProjects;
import proadmin.content.ListYears;
import proadmin.content.Squad;
import proadmin.content.MapNotes;
import proadmin.content.Project;
import proadmin.content.Report;
import proadmin.content.Student;
import proadmin.content.Teacher;
import proadmin.content.Year;

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
    private static YearDAO yearDAO;
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
	public static SQLiteDatabase open() {
		mDb = mHandler.getWritableDatabase();

		teacherDAO = new TeacherDAO(mDb);
        yearDAO = new YearDAO(mDb);
		projectDAO = new ProjectDAO(mDb);
		projectHasYearDAO = new ProjectHasYearDAO(mDb);
		squadDAO = new SquadDAO(mDb);
		studentDAO = new StudentDAO(mDb);
		studentHasSquadDAO = new StudentHasSquadDAO(mDb);
		reportDAO = new ReportDAO(mDb);
		noteDAO = new NoteDAO(mDb);

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

        return rowId > 0;
    }

	public static void updateTeacher(Teacher teacher) {
		teacherDAO.update(teacher);
	}

	public static void deleteTeacher(Id teacherId) {
		squadDAO.deleteAllOfTeacher(teacherId);
		teacherDAO.delete(teacherId);
	}

	public static Teacher selectTeacher(Id teacherId) {
		return teacherDAO.select(teacherId);
	}

	public static Teacher selectTeacher(String email, String password) {
		return teacherDAO.select(email, password);
	}

    public static void deleteYear(Year year) {
        projectHasYearDAO.deleteAllOfYear(year);
        yearDAO.delete(year);
    }

    public static ListYears selectYears() {
        return yearDAO.selectAll();
    }

	public static boolean insertProject(Project project, Year year) {
        if (!yearDAO.contains(year)) {
            yearDAO.insert(year);
        }

		if (!projectDAO.contains(project.getId())) {
			projectDAO.insert(project);
		}

		long rowId = projectHasYearDAO.insert(project.getId(), year);

        return rowId > 0;
	}

	public static void updateProject(Project project) {
		projectDAO.update(project);
	}

	public static void deleteProject(Id projectId) {
		squadDAO.deleteAllOfProject(projectId);
		projectHasYearDAO.deleteAllOfProject(projectId);
		projectDAO.delete(projectId);
	}

	public static void deleteProjectFromYear(Id projectId, Year year) {
		squadDAO.deleteAllOfYearAndProject(year, projectId);
		projectHasYearDAO.deleteAllOfProject(projectId);
	}

	public static Project selectProject(Id projectId) {
		return projectDAO.select(projectId);
	}

    public static ListProjects selectProjectsOfYear(Year year) {
        ListProjects listProjects = new ListProjects();

        ListIds listProjectsIds = projectHasYearDAO.selectAllOfYear(year);
        for (Object projectId : listProjectsIds) {
            listProjects.add(projectDAO.select((Id) projectId));
        }

        return listProjects;
    }

	public static boolean insertSquad(Squad squad) {
		long rowId = squadDAO.insert(squad);

		if (rowId > 0) {
            for (Object student : squad.getListStudents()) {
                insertStudent((Student) student, squad.getId());
            }

            for (Object report : squad.getListReports()) {
                insertReport((Report) report, squad.getId());
            }
		}

        return rowId > 0;
	}

	public static void updateSquad(Squad squad) {
		squadDAO.update(squad);
	}

	public static void deleteSquad(Id squadId) {
		reportDAO.deleteAllOfSquad(squadId);
		studentHasSquadDAO.deleteAllOfSquad(squadId);
		squadDAO.delete(squadId);
	}

	public static Squad selectSquad(Id squadId) {
		return squadDAO.select(squadId);
	}

	public static boolean insertStudent(Student student, Id squadId) {
		if (!studentDAO.contains(student.getId())) {
			studentDAO.insert(student);
		}

		long rowId = studentHasSquadDAO.insert(student.getId(), squadId);

        return rowId > 0;
	}

	public static void updateStudent(Student student) {
		studentDAO.update(student);
	}

	public static void deleteStudent(Id studentId) {
		studentHasSquadDAO.deleteAllOfStudent(studentId);
		studentDAO.delete(studentId);
	}

	public static void deleteStudentFromSquad(Id studentId, Id squadId) {
		studentHasSquadDAO.delete(studentId, squadId);
	}

	public static Student selectStudent(Id studentId) {
		return studentDAO.select(studentId);
	}

	public static boolean insertReport(Report report, Id squadId) {
		long rowId = reportDAO.insert(report, squadId);

        return rowId > 0;
	}

	public static void updateReport(Report report, Id squadId) {
		reportDAO.update(report, squadId);
	}

	public static void deleteReport(Id reportId) {
		reportDAO.delete(reportId);
	}

	public static Report selectReport(Id reportId) {
		return reportDAO.select(reportId);
	}

	public static void insertNotes(MapNotes mapNotes, Id reportId) {
		noteDAO.insert(mapNotes, reportId);
	}

	public static void updateNotes(MapNotes mapNotes, Id reportId) {
		noteDAO.update(mapNotes, reportId);
	}

	public static void deleteNotes(Id studentId, Id reportId) {
		noteDAO.delete(studentId, reportId);
	}

	public static MapNotes selectNotes(Id reportId) {
		return noteDAO.selectAllOfReport(reportId);
	}
}
