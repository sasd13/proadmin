package proadmin.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import proadmin.content.Id;
import proadmin.content.ListIds;
import proadmin.content.ListProjects;
import proadmin.content.ListReports;
import proadmin.content.ListStudents;
import proadmin.content.ListYears;
import proadmin.content.Squad;
import proadmin.content.MapNotes;
import proadmin.content.Project;
import proadmin.content.Report;
import proadmin.content.Student;
import proadmin.content.Teacher;
import proadmin.content.Year;
import proadmin.db.accessor.DataAccessor;
import proadmin.db.accessor.DataAccessorType;

public class SQLiteDAO implements DataAccessor {

    private static SQLiteDAO instance = null;
	
	private static final int VERSION = 1;
	private static final String NOM = "database.db";
	private SQLiteDatabase db = null;
	private DatabaseHandler dbHandler = null;

	private TeacherDAO teacherDAO;
    private YearDAO yearDAO;
	private ProjectDAO projectDAO;
	private ProjectHasYearDAO projectHasYearDAO;
	private SquadDAO squadDAO;
	private StudentDAO studentDAO;
	private StudentHasSquadDAO studentHasSquadDAO;
	private ReportDAO reportDAO;
	private NoteDAO noteDAO;

	private SQLiteDAO() {
        teacherDAO = new TeacherDAO();
        yearDAO = new YearDAO();
        projectDAO = new ProjectDAO();
        projectHasYearDAO = new ProjectHasYearDAO();
        squadDAO = new SquadDAO();
        studentDAO = new StudentDAO();
        studentHasSquadDAO = new StudentHasSquadDAO();
        reportDAO = new ReportDAO();
        noteDAO = new NoteDAO();
    }

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    public void create(Context context) {
        dbHandler = new DatabaseHandler(context, NOM, null, VERSION);
    }

    public void open() {
		db = dbHandler.getWritableDatabase();

        teacherDAO.setDb(db);
        yearDAO.setDb(db);
        projectDAO.setDb(db);
        projectHasYearDAO.setDb(db);
        squadDAO.setDb(db);
        studentDAO.setDb(db);
        studentHasSquadDAO.setDb(db);
        reportDAO.setDb(db);
        noteDAO.setDb(db);
	}
	
	public void close() {
		db.close();
	}

    @Override
    public DataAccessorType getType() {
        return DataAccessorType.SQLITE;
    }

    public boolean insertTeacher(Teacher teacher) {
		long rowId = teacherDAO.insert(teacher);

        return rowId > 0;
    }

	public void updateTeacher(Teacher teacher) {
		teacherDAO.update(teacher);
	}

	public void deleteTeacher(Id teacherId) {
		squadDAO.deleteAllOfTeacher(teacherId);
		teacherDAO.delete(teacherId);
	}

	public Teacher selectTeacher(Id teacherId) {
		return teacherDAO.select(teacherId);
	}

	public Teacher selectTeacher(String email, String password) {
		return teacherDAO.select(email, password);
	}

    public void deleteYear(Year year) {
        projectHasYearDAO.deleteAllOfYear(year);
        yearDAO.delete(year);
    }

    public ListYears selectYears() {
        return yearDAO.selectAll();
    }

	public boolean insertProject(Project project, Year year) {
        if (!yearDAO.contains(year)) {
            yearDAO.insert(year);
        }

		if (!projectDAO.contains(project.getId())) {
			projectDAO.insert(project);
		}

		long rowId = projectHasYearDAO.insert(project.getId(), year);

        return rowId > 0;
	}

	public void updateProject(Project project) {
		projectDAO.update(project);
	}

	public void deleteProject(Id projectId) {
		squadDAO.deleteAllOfProject(projectId);
		projectHasYearDAO.deleteAllOfProject(projectId);
		projectDAO.delete(projectId);
	}

	public void deleteProjectFromYear(Id projectId, Year year) {
		squadDAO.deleteAllOfYearAndProject(year, projectId);
		projectHasYearDAO.deleteAllOfProject(projectId);
	}

	public Project selectProject(Id projectId) {
		return projectDAO.select(projectId);
	}

    public ListProjects selectProjectsOfYear(Year year) {
        ListProjects listProjects = new ListProjects();

        ListIds listProjectsIds = projectHasYearDAO.selectAllOfYear(year);
        for (Object projectId : listProjectsIds) {
            listProjects.add(projectDAO.select((Id) projectId));
        }

        return listProjects;
    }

	public boolean insertSquad(Squad squad) {
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

	public void updateSquad(Squad squad) {
		squadDAO.update(squad);
	}

	public void deleteSquad(Id squadId) {
		reportDAO.deleteAllOfSquad(squadId);
		studentHasSquadDAO.deleteAllOfSquad(squadId);
		squadDAO.delete(squadId);
	}

	public Squad selectSquad(Id squadId) {
		return squadDAO.select(squadId);
	}

	public boolean insertStudent(Student student, Id squadId) {
		if (!studentDAO.contains(student.getId())) {
			studentDAO.insert(student);
		}

		long rowId = studentHasSquadDAO.insert(student.getId(), squadId);

        return rowId > 0;
	}

	public void updateStudent(Student student) {
		studentDAO.update(student);
	}

	public void deleteStudent(Id studentId) {
		studentHasSquadDAO.deleteAllOfStudent(studentId);
		studentDAO.delete(studentId);
	}

	public void deleteStudentFromSquad(Id studentId, Id squadId) {
		studentHasSquadDAO.delete(studentId, squadId);
	}

	public Student selectStudent(Id studentId) {
		return studentDAO.select(studentId);
	}

    public ListStudents selectStudents(Id squadId) {
        ListStudents listStudents = new ListStudents();

        ListIds listIds = studentHasSquadDAO.selectAllOfSquad(squadId);

        Student student;
        for (Object id : listIds) {
            student = studentDAO.select((Id) id);
            listStudents.add(student);
        }

        return listStudents;
    }

	public boolean insertReport(Report report, Id squadId) {
		long rowId = reportDAO.insert(report, squadId);

        return rowId > 0;
	}

	public void updateReport(Report report, Id squadId) {
		reportDAO.update(report, squadId);
	}

	public void deleteReport(Id reportId) {
		reportDAO.delete(reportId);
	}

	public Report selectReport(Id reportId) {
		return reportDAO.select(reportId);
	}

    public ListReports selectReports(Id squadId) {
        return reportDAO.selectAllOfSquad(squadId);
    }

	public void insertNotes(MapNotes mapNotes, Id reportId) {
		noteDAO.insert(mapNotes, reportId);
	}

	public void updateNotes(MapNotes mapNotes, Id reportId) {
		noteDAO.update(mapNotes, reportId);
	}

	public void deleteNotes(Id studentId, Id reportId) {
		noteDAO.delete(studentId, reportId);
	}

	public MapNotes selectNotes(Id reportId) {
		return noteDAO.selectAllOfReport(reportId);
	}
}
