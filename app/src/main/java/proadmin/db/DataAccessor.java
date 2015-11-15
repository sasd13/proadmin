package proadmin.db;

import android.content.Context;

import java.util.List;

import proadmin.bean.AcademicLevel;
import proadmin.bean.project.Project;
import proadmin.bean.running.Report;
import proadmin.bean.member.Student;
import proadmin.bean.member.Teacher;
import proadmin.bean.running.Team;

public interface DataAccessor {

    void init(Context context);

    void insertTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    Teacher selectTeacher(long id);

    Teacher selectTeacherByEmail(String email);

    void insertProject(Project project);

    void updateProject(Project project);

    void deleteProject(long id);

    Project selectProject(long id);

    List<Project> selectProjectsByCode(String code);

    List<Project> selectProjectsByAcademicLevel(AcademicLevel academicLevel);

    void insertTeam(Team team);

    void updateTeam(Team team);

    void deleteTeam(long id);

    Team selectTeam(long id);

    List<Team> selectTeamsByRunningYear(long runningYear);

    void insertStudent(Student student, long teamId);

    void updateStudent(Student student);

    void deleteStudentFromTeam(long studentId, long teamId);

    Student selectStudent(long id);

    Student selectStudentByNumber(String number);

    List<Student> selectStudentsByTeam(long teamId);

    void insertReport(Report report);

    void updateReport(Report report);

    void deleteReport(long id);

    Report selectReport(long id);

    List<Report> selectReportsByTeam(long teamId);
}