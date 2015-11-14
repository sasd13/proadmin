package proadmin.db;

import android.content.Context;

import java.util.List;

import proadmin.beans.Project;
import proadmin.beans.Report;
import proadmin.beans.Student;
import proadmin.beans.Teacher;
import proadmin.beans.Team;

public interface DataAccessor {

    String getDBType();

    void init(Context context);

    void insertTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    Teacher selectTeacher(String id);

    Teacher selectTeacherByEmail(String email);

    void insertProject(Project project);

    void updateProject(Project project);

    void deleteProject(Project project);

    Project selectProject(long id);

    List<Project> selectProjectsByCode(String code);

    List<Project> selectProjectsByAcademicLevel(String academicLevel);

    void insertTeam(Team team);

    void updateTeam(Team team);

    void deleteTeam(Team team);

    Team selectTeam(long id);

    List<Team> selectTeamsByRunningYear(long runningYear);

    void insertStudent(Student student, Team team);

    void updateStudent(Student student);

    void deleteStudentFromTeam(Student student, Team team);

    Student selectStudent(String id);

    List<Student> selectStudentsByTeam(Team team);

    void insertReport(Report report, Teacher teacher, Project project, Team team);

    void updateReport(Report report);

    void deleteReport(Report report);

    Report selectReport(long id);

    List<Report> selectReportsByTeam(Team team);
}