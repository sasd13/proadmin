package proadmin.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import proadmin.beans.Project;
import proadmin.beans.Report;
import proadmin.beans.Student;
import proadmin.beans.Teacher;
import proadmin.beans.Team;
import proadmin.db.DataAccessor;

public class SQLiteDAO implements DataAccessor {

    private static final int VERSION = 1;
    private static final String NOM = "database.db";

    private static SQLiteDAO instance = null;

    private SQLiteDBHandler dbHandler;
    private SQLiteDatabase db;

    //TODO

    private SQLiteDAO() {}

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    @Override
    public String getDBType() {
        return "SQLITE";
    }

    @Override
    public void init(Context context) {
        dbHandler = new SQLiteDBHandler(context, NOM, null, VERSION);

        //TODO
    }

    private void open() {
        db = dbHandler.getWritableDatabase();

        //TODO
    }

    private void close() {
        db.close();
    }

    @Override
    public void insertTeacher(Teacher teacher) {

    }

    @Override
    public void updateTeacher(Teacher teacher) {

    }

    @Override
    public Teacher selectTeacher(String id) {
        return null;
    }

    @Override
    public Teacher selectTeacherByEmail(String email) {
        return null;
    }

    @Override
    public void insertProject(Project project) {

    }

    @Override
    public void updateProject(Project project) {

    }

    @Override
    public void deleteProject(Project project) {

    }

    @Override
    public Project selectProject(long id) {
        return null;
    }

    @Override
    public List<Project> selectProjectsByCode(String code) {
        return null;
    }

    @Override
    public List<Project> selectProjectsByAcademicLevel(String academicLevel) {
        return null;
    }

    @Override
    public void insertTeam(Team team) {

    }

    @Override
    public void updateTeam(Team team) {

    }

    @Override
    public void deleteTeam(Team team) {

    }

    @Override
    public Team selectTeam(long id) {
        return null;
    }

    @Override
    public List<Team> selectTeamsByRunningYear(long runningYear) {
        return null;
    }

    @Override
    public void insertStudent(Student student, Team team) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudentFromTeam(Student student, Team team) {

    }

    @Override
    public Student selectStudent(String id) {
        return null;
    }

    @Override
    public List<Student> selectStudentsByTeam(Team team) {
        return null;
    }

    @Override
    public void insertReport(Report report, Teacher teacher, Project project, Team team) {

    }

    @Override
    public void updateReport(Report report) {

    }

    @Override
    public void deleteReport(Report report) {

    }

    @Override
    public Report selectReport(long id) {
        return null;
    }

    @Override
    public List<Report> selectReportsByTeam(Team team) {
        return null;
    }
}