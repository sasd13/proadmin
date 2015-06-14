package proadmin.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

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
import proadmin.content.ListSquads;
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

	@Override
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

    @Override
	public void close() {
        db.close();
	}

    @Override
    public DataAccessorType getType() {
        return DataAccessorType.SQLITE;
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        teacherDAO.insert(teacher);
    }

    @Override
	public void updateTeacher(Teacher teacher) {
		teacherDAO.update(teacher);
	}

    @Override
	public void deleteTeacher(TeacherId teacherId) {
		squadDAO.deleteAllOfTeacher(teacherId);
		teacherDAO.delete(teacherId);
	}

    @Override
	public Teacher selectTeacher(TeacherId teacherId) {
		return teacherDAO.select(teacherId);
	}

    @Override
	public Teacher selectTeacher(String email) {
		return teacherDAO.select(email);
	}

    @Override
    public void deleteYear(Year year) {
        projectHasYearDAO.deleteAllOfYear(year);
        yearDAO.delete(year);
    }

    @Override
    public ListYears selectYears() {
        return yearDAO.selectAll();
    }

    @Override
	public void insertProject(Project project, Year year) {
        long rowId1 = yearDAO.insert(year);
        long rowId2 = projectDAO.insert(project);
        long rowId3 = projectHasYearDAO.insert(project.getId(), year);
	}

    @Override
	public void updateProject(Project project) {
		projectDAO.update(project);
	}

    @Override
	public void deleteProject(ProjectId projectId) {
		squadDAO.deleteAllOfProject(projectId);
		projectHasYearDAO.deleteAllOfProject(projectId);
		projectDAO.delete(projectId);
	}

    @Override
	public void deleteProjectFromYear(ProjectId projectId, Year year) {
		squadDAO.deleteAllOfYearAndProject(year, projectId);
		projectHasYearDAO.deleteAllOfProject(projectId);
	}

    @Override
	public Project selectProject(ProjectId projectId) {
		return projectDAO.select(projectId);
	}

    @Override
    public ListProjects selectProjectsOfYear(Year year) {
        ListProjects listProjects = new ListProjects();

        ListIds listProjectsIds = projectHasYearDAO.selectAllOfYear(year);
        for (Object projectId : listProjectsIds) {
            listProjects.add(projectDAO.select((ProjectId) projectId));
        }

        return listProjects;
    }

    @Override
    public Year selectYearCreationOfProject(ProjectId projectId) {
        ListYears listYears = projectHasYearDAO.selectAllOfProject(projectId);

        return listYears.get(listYears.size() - 1);
    }

    @Override
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

    @Override
	public void updateSquad(Squad squad) {
		squadDAO.update(squad);

        for (Object student : squad.getListStudents()) {
            updateStudent((Student) student);
        }

        for (Object report : squad.getListReports()) {
            updateReport((Report) report);
        }
	}

    @Override
	public void deleteSquad(SquadId squadId) {
		reportDAO.deleteAllOfSquad(squadId);
		studentHasSquadDAO.deleteAllOfSquad(squadId);
		squadDAO.delete(squadId);
	}

    @Override
	public Squad selectSquad(SquadId squadId) {
		return squadDAO.select(squadId);
	}

	@Override
	public ListSquads selectSquadsOfYear(Year year) {
		return squadDAO.selectAllOfYear(year);
	}

	@Override
	public ListSquads selectSquadsOfProject(ProjectId projectId) {
		return squadDAO.selectAllOfProject(projectId);
	}

	@Override
	public ListSquads selectSquadsOfTeacher(TeacherId teacherId) {
		return squadDAO.selectAllOfTeacher(teacherId);
	}

	@Override
	public ListSquads selectSquadsOfYearAndProject(Year year, ProjectId projectId) {
		return squadDAO.selectAllOfYearAndProject(year, projectId);
	}

	@Override
	public ListSquads selectSquadsOfYearAndTeacher(Year year, TeacherId teacherId) {
		return squadDAO.selectAllOfYearAndTeacher(year, teacherId);
	}

	@Override
	public ListSquads selectSquadsOfProjectAndTeacher(ProjectId projectId, TeacherId teacherId) {
		return squadDAO.selectAllOfProjectAndTeacher(projectId, teacherId);
	}

	@Override
	public ListSquads selectSquadsOfYearAndProjectAndTeacher(Year year, ProjectId projectId, TeacherId teacherId) {
		return squadDAO.selectAllOfYearAndProjectAndTeacher(year, projectId, teacherId);
	}

    @Override
	public void insertStudent(Student student, SquadId squadId) {
		studentDAO.insert(student);
		studentHasSquadDAO.insert(student.getId(), squadId);
	}

    @Override
	public void updateStudent(Student student) {
		studentDAO.update(student);
	}

    @Override
	public void deleteStudent(StudentId studentId) {
		studentHasSquadDAO.deleteAllOfStudent(studentId);
		studentDAO.delete(studentId);
	}

    @Override
	public void deleteStudentFromSquad(StudentId studentId, SquadId squadId) {
		studentHasSquadDAO.delete(studentId, squadId);
	}

    @Override
	public Student selectStudent(StudentId studentId) {
		return studentDAO.select(studentId);
	}

	@Override
	public ListStudents selectStudentsOfSquad(SquadId squadId) {
        ListStudents listStudents = new ListStudents();

        ListIds listIds = studentHasSquadDAO.selectAllOfSquad(squadId);
        for (Object id : listIds) {
            listStudents.add(studentDAO.select((StudentId) id));
        }

        return listStudents;
	}

    @Override
	public void insertReport(Report report) {
		reportDAO.insert(report);
	}

    @Override
	public void updateReport(Report report) {
		reportDAO.update(report);
	}

    @Override
	public void deleteReport(ReportId reportId) {
		reportDAO.delete(reportId);
	}

    @Override
	public Report selectReport(ReportId reportId) {
		return reportDAO.select(reportId);
	}

	@Override
	public ListReports selectReportsOfSquad(SquadId squadId) {
        return reportDAO.selectAllOfSquad(squadId);
	}

    @Override
	public void insertNotes(MapNotes mapNotes, ReportId reportId) {
		noteDAO.insert(mapNotes, reportId);
	}

    @Override
	public void updateNotes(MapNotes mapNotes, ReportId reportId) {
		noteDAO.update(mapNotes, reportId);
	}

    @Override
	public void deleteNotes(StudentId studentId, ReportId reportId) {
		noteDAO.delete(studentId, reportId);
	}

	@Override
	public MapNotes selectNotesOfReport(ReportId reportId) {
        return noteDAO.selectAllOfReport(reportId);
	}
}
