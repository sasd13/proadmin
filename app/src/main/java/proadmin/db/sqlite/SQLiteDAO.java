package proadmin.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proadmin.beans.AcademicLevel;
import proadmin.beans.projects.Project;
import proadmin.beans.running.IndividualEvaluation;
import proadmin.beans.running.LeadEvaluation;
import proadmin.beans.running.Report;
import proadmin.beans.members.Student;
import proadmin.beans.members.Teacher;
import proadmin.beans.running.Team;
import proadmin.db.DataAccessor;

public class SQLiteDAO implements DataAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    private TeacherDAO teacherDAO;
    private ProjectDAO projectDAO;
    private TeamDAO teamDAO;
    private StudentDAO studentDAO;
    private StudentTeamDAO studentTeamDAO;
    private ReportDAO reportDAO;
    private LeadEvaluationDAO leadEvaluationDAO;
    private IndividualEvaluationDAO individualEvaluationDAO;

    private SQLiteDAO() {}

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, NOM, null, VERSION);

        teacherDAO = new TeacherDAO();
        projectDAO = new ProjectDAO();
        teamDAO = new TeamDAO();
        studentDAO = new StudentDAO();
        studentTeamDAO = new StudentTeamDAO();
        reportDAO = new ReportDAO();
        leadEvaluationDAO = new LeadEvaluationDAO();
        individualEvaluationDAO = new IndividualEvaluationDAO();
    }

    private void open() {
        db = dbHandler.getWritableDatabase();

        teacherDAO.setDB(db);
        projectDAO.setDB(db);
        teamDAO.setDB(db);
        studentDAO.setDB(db);
        studentTeamDAO.setDB(db);
        reportDAO.setDB(db);
        leadEvaluationDAO.setDB(db);
        individualEvaluationDAO.setDB(db);
    }

    private void close() {
        db.close();
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        open();

        long id = teacherDAO.insert(teacher);
        if (id > 0) {
            teacher.setId(id);
        }

        close();
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        open();

        teacherDAO.update(teacher);

        close();
    }

    @Override
    public Teacher selectTeacher(long id) {
        Teacher teacher;

        open();

        teacher = teacherDAO.select(id);

        close();

        return teacher;
    }

    @Override
    public Teacher selectTeacherByEmail(String email) {
        Teacher teacher;

        open();

        teacher = teacherDAO.selectByEmail(email);

        close();

        return teacher;
    }

    @Override
    public void insertProject(Project project) {
        open();

        long id = projectDAO.insert(project);
        if (id > 0) {
            project.setId(id);
        }

        close();
    }

    @Override
    public void updateProject(Project project) {
        open();

        projectDAO.update(project);

        close();
    }

    @Override
    public void deleteProject(long id) {
        open();

        projectDAO.delete(id);

        close();
    }

    @Override
    public Project selectProject(long id) {
        Project project;

        open();

        project = projectDAO.select(id);

        close();

        return project;
    }

    @Override
    public List<Project> selectProjectsByCode(String code) {
        List<Project> list;

        open();

        list = projectDAO.selectByCode(code);

        close();

        return list;
    }

    @Override
    public List<Project> selectProjectsByAcademicLevel(AcademicLevel academicLevel) {
        List<Project> list;

        open();

        list = projectDAO.selectByAcademicLevel(academicLevel);

        close();

        return list;
    }

    @Override
    public void insertTeam(Team team) {
        open();

        long id = teamDAO.insert(team);
        if (id > 0) {
            team.setId(id);
        }

        close();
    }

    @Override
    public void updateTeam(Team team) {
        open();

        teamDAO.update(team);

        close();
    }

    @Override
    public void deleteTeam(long id) {
        open();

        teamDAO.delete(id);

        close();
    }

    @Override
    public Team selectTeam(long id) {
        Team team;

        open();

        team = teamDAO.select(id);

        close();

        return team;
    }

    @Override
    public List<Team> selectTeamsByRunningYear(long runningYear) {
        List<Team> list;

        open();

        list = teamDAO.selectByRunningYear(runningYear);

        close();

        return list;
    }

    @Override
    public void insertStudent(Student student, long teamId) {
        open();

        executeInsertStudent(student, teamId);

        close();
    }

    private void executeInsertStudent(Student student, long teamId) {
        long id = studentDAO.insert(student);
        if (id > 0) {
            student.setId(id);

            studentTeamDAO.insertStudentInTeam(id, teamId);
        }
    }

    @Override
    public void insertStudents(Student[] students, long teamId) {
        open();

        for (Student student : students) {
            executeInsertStudent(student, teamId);
        }

        close();
    }

    @Override
    public void updateStudent(Student student) {
        open();

        studentDAO.update(student);

        close();
    }

    @Override
    public void deleteStudentFromTeam(long studentId, long teamId) {
        open();

        studentTeamDAO.deleteStudentFromTeam(studentId, teamId);

        close();
    }

    @Override
    public void deleteStudentsFromTeam(long teamId) {
        open();

        studentTeamDAO.deleteStudentsFromTeam(teamId);

        close();
    }

    @Override
    public Student selectStudent(long id) {
        Student student;

        open();

        student = studentDAO.select(id);

        close();

        return student;
    }

    @Override
    public Student selectStudentByNumber(String number) {
        Student student;

        open();

        student = studentDAO.selectByNumber(number);

        close();

        return student;
    }

    @Override
    public List<Student> selectStudentsByTeam(long teamId) {
        List<Student> list = new ArrayList<>();

        open();

        List<Long> listStudentIds = studentTeamDAO.selectByTeam(teamId);
        for (long studentId : listStudentIds) {
            list.add(selectStudent(studentId));
        }

        return list;
    }

    @Override
    public void insertReport(Report report) {
        open();

        long id = reportDAO.insert(report);
        if (id > 0) {
            report.setId(id);
        }

        close();
    }

    @Override
    public void updateReport(Report report) {
        open();

        reportDAO.update(report);

        close();
    }

    @Override
    public void deleteReport(long id) {
        open();

        reportDAO.delete(id);

        close();
    }

    @Override
    public Report selectReport(long id) {
        Report report;

        open();

        report = reportDAO.select(id);

        close();

        return report;
    }

    @Override
    public List<Report> selectReportsByTeam(long teamId) {
        List<Report> list;

        open();

        list = reportDAO.selectByTeam(teamId);

        close();

        return list;
    }

    @Override
    public void insertLeadEvaluation(LeadEvaluation leadEvaluation) {
        open();

        long id = leadEvaluationDAO.insert(leadEvaluation);
        if (id > 0) {
            leadEvaluation.setId(id);
        }

        close();
    }

    @Override
    public void updateLeadEvaluation(LeadEvaluation leadEvaluation) {
        open();

        leadEvaluationDAO.update(leadEvaluation);

        close();
    }

    @Override
    public void deleteLeadEvaluation(long id) {
        open();

        leadEvaluationDAO.delete(id);

        close();
    }

    @Override
    public LeadEvaluation selectLeadEvaluation(long id) {
        LeadEvaluation leadEvaluation;

        open();

        leadEvaluation = leadEvaluationDAO.select(id);

        close();

        return leadEvaluation;
    }

    @Override
    public LeadEvaluation selectLeadEvaluationByReport(long reportId) {
        LeadEvaluation leadEvaluation;

        open();

        leadEvaluation = leadEvaluationDAO.selectByReport(reportId);

        close();

        return leadEvaluation;
    }

    @Override
    public void insertIndividualEvaluation(IndividualEvaluation individualEvaluation) {
        open();

        executeInsertIndividualEvaluation(individualEvaluation);

        close();
    }

    private void executeInsertIndividualEvaluation(IndividualEvaluation individualEvaluation) {
        long id = individualEvaluationDAO.insert(individualEvaluation);
        if (id > 0) {
            individualEvaluation.setId(id);
        }
    }

    @Override
    public void insertIndividualEvaluations(IndividualEvaluation[] tabIndividualEvaluations) {
        open();

        for (IndividualEvaluation individualEvaluation : tabIndividualEvaluations) {
            executeInsertIndividualEvaluation(individualEvaluation);
        }

        close();
    }

    @Override
    public void updateIndividualEvaluation(IndividualEvaluation individualEvaluation) {
        open();

        individualEvaluationDAO.update(individualEvaluation);

        close();
    }

    @Override
    public void deleteIndividualEvaluation(long id) {
        open();

        individualEvaluationDAO.delete(id);

        close();
    }

    @Override
    public IndividualEvaluation selectIndividualEvaluation(long id) {
        IndividualEvaluation individualEvaluation;

        open();

        individualEvaluation = individualEvaluationDAO.select(id);

        close();

        return individualEvaluation;
    }

    @Override
    public List<IndividualEvaluation> selectIndividualEvaluationsByReport(long reportId) {
        List<IndividualEvaluation> list;

        open();

        list = individualEvaluationDAO.selectByReport(reportId);

        close();

        return list;
    }
}