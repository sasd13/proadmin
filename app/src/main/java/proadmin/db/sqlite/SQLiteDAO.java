package proadmin.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

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
import proadmin.content.id.ProjectId;
import proadmin.content.id.ReportId;
import proadmin.content.id.SquadId;
import proadmin.content.id.StudentId;
import proadmin.content.id.TeacherId;
import proadmin.pattern.dao.accessor.DataAccessor;
import proadmin.pattern.dao.accessor.DataAccessorType;

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

    public void insertTeacher(Teacher teacher) {
        teacherDAO.insert(teacher);
    }

	public void updateTeacher(Teacher teacher) {
		teacherDAO.update(teacher);
	}

	public void deleteTeacher(TeacherId teacherId) {
		squadDAO.deleteAllOfTeacher(teacherId);
		teacherDAO.delete(teacherId);
	}

	public Teacher selectTeacher(TeacherId teacherId) {
		return teacherDAO.select(teacherId);
	}

	public Teacher selectTeacher(String email) {
		return teacherDAO.select(email);
	}

    public void deleteYear(Year year) {
        projectHasYearDAO.deleteAllOfYear(year);
        yearDAO.delete(year);
    }

    public ListYears selectYears() {
        return yearDAO.selectAll();
    }

	public void insertProject(Project project, Year year) {
        yearDAO.insert(year);
        projectDAO.insert(project);
        projectHasYearDAO.insert(project.getId(), year);
	}

	public void updateProject(Project project) {
		projectDAO.update(project);
	}

	public void deleteProject(ProjectId projectId) {
		squadDAO.deleteAllOfProject(projectId);
		projectHasYearDAO.deleteAllOfProject(projectId);
		projectDAO.delete(projectId);
	}

	public void deleteProjectFromYear(ProjectId projectId, Year year) {
		squadDAO.deleteAllOfYearAndProject(year, projectId);
		projectHasYearDAO.deleteAllOfProject(projectId);
	}

	public Project selectProject(ProjectId projectId) {
		return projectDAO.select(projectId);
	}

    public ListProjects selectProjectsOfYear(Year year) {
        ListProjects listProjects = new ListProjects();

        ListIds listProjectsIds = projectHasYearDAO.selectAllOfYear(year);
        for (Object projectId : listProjectsIds) {
            listProjects.add(projectDAO.select((ProjectId) projectId));
        }

        return listProjects;
    }

	public void insertSquad(Squad squad) {
		long rowId = squadDAO.insert(squad);

		if (rowId > 0) {
            for (Object student : squad.getListStudents()) {
                insertStudent((Student) student, squad.getId());
            }

            for (Object report : squad.getListReports()) {
                insertReport((Report) report);
            }
		}
	}

	public void updateSquad(Squad squad) {
		squadDAO.update(squad);

        for (Object student : squad.getListStudents()) {
            updateStudent((Student) student);
        }

        for (Object report : squad.getListReports()) {
            updateReport((Report) report);
        }
	}

	public void deleteSquad(SquadId squadId) {
		reportDAO.deleteAllOfSquad(squadId);
		studentHasSquadDAO.deleteAllOfSquad(squadId);
		squadDAO.delete(squadId);
	}

	public Squad selectSquad(SquadId squadId) {
		return squadDAO.select(squadId);
	}

	public void insertStudent(Student student, SquadId squadId) {
		studentDAO.insert(student);
		studentHasSquadDAO.insert(student.getId(), squadId);
	}

	public void updateStudent(Student student) {
		studentDAO.update(student);
	}

	public void deleteStudent(StudentId studentId) {
		studentHasSquadDAO.deleteAllOfStudent(studentId);
		studentDAO.delete(studentId);
	}

	public void deleteStudentFromSquad(StudentId studentId, SquadId squadId) {
		studentHasSquadDAO.delete(studentId, squadId);
	}

	public Student selectStudent(StudentId studentId) {
		return studentDAO.select(studentId);
	}

    public ListStudents selectStudents(SquadId squadId) {
        ListStudents listStudents = new ListStudents();

        ListIds listIds = studentHasSquadDAO.selectAllOfSquad(squadId);
        for (Object id : listIds) {
            listStudents.add(studentDAO.select((StudentId) id));
        }

        return listStudents;
    }

	public void insertReport(Report report) {
		reportDAO.insert(report);
	}

	public void updateReport(Report report) {
		reportDAO.update(report);
	}

	public void deleteReport(ReportId reportId) {
		reportDAO.delete(reportId);
	}

	public Report selectReport(ReportId reportId) {
		return reportDAO.select(reportId);
	}

    public ListReports selectReports(SquadId squadId) {
        return reportDAO.selectAllOfSquad(squadId);
    }

	public void insertNotes(MapNotes mapNotes, ReportId reportId) {
		noteDAO.insert(mapNotes, reportId);
	}

	public void updateNotes(MapNotes mapNotes, ReportId reportId) {
		noteDAO.update(mapNotes, reportId);
	}

	public void deleteNotes(StudentId studentId, ReportId reportId) {
		noteDAO.delete(studentId, reportId);
	}

	public MapNotes selectNotes(ReportId reportId) {
		return noteDAO.selectAllOfReport(reportId);
	}
}
