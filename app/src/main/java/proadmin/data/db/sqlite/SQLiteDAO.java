package proadmin.data.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import proadmin.beans.Team;
import proadmin.beans.Project;
import proadmin.beans.Report;
import proadmin.beans.Student;
import proadmin.beans.Teacher;
import proadmin.content.Year;
import proadmin.data.dao.accessor.DataAccessor;

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
    public String getType() {
        return "SQLITE";
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        if (!teacherDAO.contains(teacher.getId()) && !teacherDAO.contains(teacher.getEmail())) {
            teacherDAO.insert(teacher);
        }
    }

    @Override
	public void updateTeacher(Teacher teacher) {
        Teacher teacher2 = selectTeacher(teacher.getEmail());
        if (teacher2.getId().equals(teacher.getId())) {
            teacherDAO.update(teacher);
        }
	}

    @Override
	public void deleteTeacher(String teacherId) {
        ListSquads listSquads = selectSquadsOfTeacher(teacherId);
        for (Object squad : listSquads) {
            deleteSquad(((Team) squad).getId());
        }

		teacherDAO.delete(teacherId);
	}

    @Override
	public Teacher selectTeacher(String teacherIdOrEmail) {
		return teacherDAO.select(teacherIdOrEmail);
	}

    @Override
    public void deleteYear(Year year) {
        ListSquads listSquads = selectSquadsOfYear(year);
        for (Object squad : listSquads) {
            deleteSquad(((Team) squad).getId());
        }

        ListProjects listProjects = selectProjectsOfYear(year);
        for (Object project : listProjects) {
            deleteProjectFromYear(((Project) project).getId(), year);
        }

        yearDAO.delete(year);
    }

    @Override
    public ListYears selectYears() {
        return yearDAO.selectAll();
    }

    @Override
    public ListYears selectYearsOfProjectByDesc(String projectId) {
        return projectHasYearDAO.selectAllOfProjectByDesc(projectId);
    }

    @Override
    public Year selectYearCreationOfProject(String projectId) {
        ListYears listYears = projectHasYearDAO.selectAllOfProjectByDesc(projectId);

        return listYears.get(listYears.size() - 1);
    }

    @Override
	public void insertProject(Project project, Year year) {
        if (!yearDAO.contains(year)) {
            yearDAO.insert(year);
        }

        if (!projectDAO.contains(project.getId())) {
            projectDAO.insert(project);
        }

        if (!projectHasYearDAO.contains(project.getId(), year)) {
            projectHasYearDAO.insert(project.getId(), year);
        }
	}

    @Override
	public void updateProject(Project project) {
		projectDAO.update(project);
	}

    @Override
	public void deleteProject(String projectId) {
		ListSquads listSquads = selectSquadsOfProject(projectId);
        for (Object squad : listSquads) {
            deleteSquad(((Team) squad).getId());
        }

		projectHasYearDAO.deleteAllOfProject(projectId);
		projectDAO.delete(projectId);
	}

    @Override
	public void deleteProjectFromYear(String projectId, Year year) {
        ListSquads listSquads = selectSquadsOfYearAndProject(year, projectId);
        for (Object squad : listSquads) {
            deleteSquad(((Team) squad).getId());
        }

		projectHasYearDAO.delete(projectId, year);
	}

    @Override
	public Project selectProject(String projectId) {
		return projectDAO.select(projectId);
	}

    @Override
    public ListProjects selectProjectsOfYear(Year year) {
        ListProjects listProjects = new ListProjects();

        ListIds listProjectsIds = projectHasYearDAO.selectAllOfYear(year);
        for (Object projectId : listProjectsIds) {
            listProjects.add(projectDAO.select((String) projectId));
        }

        return listProjects;
    }

    @Override
	public void insertSquad(Team team) {
        if (!squadDAO.contains(team.getId())) {
            long rowId = squadDAO.insert(team);

            if (rowId > 0) {
                for (Object student : team.getListStudents()) {
                    insertStudent((Student) student, team.getId());
                }

                for (Object report : team.getListReports()) {
                    insertReport((Report) report);
                }
            }
        }
	}

    @Override
	public void updateSquad(Team team) {
		squadDAO.update(team);

        for (Object student : team.getListStudents()) {
            updateStudent((Student) student);
        }

        for (Object report : team.getListReports()) {
            updateReport((Report) report);
        }
	}

    @Override
	public void deleteSquad(String squadId) {
        ListReports listReports = selectReportsOfSquad(squadId);
        for (Object report : listReports) {
            deleteReport(((Report) report).getId());
        }

        ListStudents listStudents = selectStudentsOfSquad(squadId);
        for (Object student : listStudents) {
            deleteStudentFromSquad(((Student) student).getId(), squadId);
        }

		squadDAO.delete(squadId);
	}

    @Override
	public Team selectSquad(String squadId) {
		return squadDAO.select(squadId);
	}

    @Override
    public ListSquads selectSquadsOfTeacher(String teacherId) {
        return squadDAO.selectAllOfTeacher(teacherId);
    }

    @Override
    public ListSquads selectSquadsOfYear(Year year) {
        return squadDAO.selectAllOfYear(year);
    }

    @Override
    public ListSquads selectSquadsOfProject(String projectId) {
        return squadDAO.selectAllOfProject(projectId);
    }

    @Override
    public ListSquads selectSquadsOfTeacherAndYear(String teacherId, Year year) {
        return squadDAO.selectAllOfTeacherAndYear(teacherId, year);
    }

    @Override
    public ListSquads selectSquadsOfTeacherAndProject(String teacherId, String projectId) {
        return squadDAO.selectAllOfTeacherAndProject(teacherId, projectId);
    }

	@Override
	public ListSquads selectSquadsOfYearAndProject(Year year, String projectId) {
		return squadDAO.selectAllOfYearAndProject(year, projectId);
	}

    @Override
    public ListSquads selectSquadsOfTeacherAndYearAndProjectAndYear(String teacherId, Year year, String projectId) {
        return squadDAO.selectAllOfTeacherAndYearAndProject(teacherId, year, projectId);
    }

    @Override
	public void insertStudent(Student student, String squadId) {
        if (!studentDAO.contains(student.getId()) && !studentDAO.contains(student.getEmail())) {
            studentDAO.insert(student);
        }

        if (!studentHasSquadDAO.contains(student.getId(), squadId)) {
            studentHasSquadDAO.insert(student.getId(), squadId);
        }
	}

    @Override
	public void updateStudent(Student student) {
        Student student2 = selectStudent(student.getEmail());
        if (student2.getId().equals(student.getId())) {
            studentDAO.update(student);
        }
	}

    @Override
	public void deleteStudentFromSquad(String studentId, String squadId) {
        ListReports listReports = reportDAO.selectAllOfSquadAndStudent(squadId, studentId);

        //TODO

		studentHasSquadDAO.delete(studentId, squadId);
	}

    @Override
	public Student selectStudent(String studentIdOrEmail) {
		return studentDAO.select(studentIdOrEmail);
	}

    @Override
	public ListStudents selectStudentsOfSquad(String squadId) {
        ListStudents listStudents = new ListStudents();

        ListIds listIds = studentHasSquadDAO.selectAllOfSquad(squadId);
        for (Object id : listIds) {
            listStudents.add(studentDAO.select((String) id));
        }

        return listStudents;
	}

    @Override
	public void insertReport(Report report) {
        if (!reportDAO.contains(report.getId())) {
            reportDAO.insert(report);
        }
	}

    @Override
	public void updateReport(Report report) {
		reportDAO.update(report);
	}

    @Override
	public void deleteReport(String reportId) {
        noteDAO.deleteAllOfReport(reportId);
		reportDAO.delete(reportId);
	}

    @Override
	public Report selectReport(String reportId) {
		return reportDAO.select(reportId);
	}

	@Override
	public ListReports selectReportsOfSquad(String squadId) {
        return reportDAO.selectAllOfSquad(squadId);
	}

    @Override
    public void insertNotes(MapNotes mapNotes, String reportId) {
        String[] tabStudentsIds = mapNotes.getKeys();
        for (String studentId : tabStudentsIds) {
            if (!noteDAO.contains(reportId, studentId)) {
                noteDAO.insert(mapNotes.get(studentId), reportId, studentId);
            }
        }
    }

    @Override
    public void updateNotes(MapNotes mapNotes, String reportId) {
        String[] tabStudentsIds = mapNotes.getKeys();
        for (String studentId : tabStudentsIds) {
            noteDAO.update(mapNotes.get(studentId), reportId, studentId);
        }
    }

    @Override
	public MapNotes selectNotesOfReport(String reportId) {
        return noteDAO.selectAllOfReport(reportId);
	}
}
