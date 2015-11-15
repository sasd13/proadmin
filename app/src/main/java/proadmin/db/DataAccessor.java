package proadmin.db;

import android.content.Context;

import java.util.List;

import proadmin.beans.AcademicLevel;
import proadmin.beans.projects.Project;
import proadmin.beans.running.IndividualEvaluation;
import proadmin.beans.running.LeadEvaluation;
import proadmin.beans.running.Report;
import proadmin.beans.members.Student;
import proadmin.beans.members.Teacher;
import proadmin.beans.running.Team;

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

    void insertStudents(Student[] students, long teamId);

    void updateStudent(Student student);

    void deleteStudentFromTeam(long studentId, long teamId);

    void deleteStudentsFromTeam(long teamId);

    Student selectStudent(long id);

    Student selectStudentByNumber(String number);

    List<Student> selectStudentsByTeam(long teamId);

    void insertReport(Report report);

    void updateReport(Report report);

    void deleteReport(long id);

    Report selectReport(long id);

    List<Report> selectReportsByTeam(long teamId);

    void insertLeadEvaluation(LeadEvaluation leadEvaluation);

    void updateLeadEvaluation(LeadEvaluation leadEvaluation);

    void deleteLeadEvaluation(long id);

    LeadEvaluation selectLeadEvaluation(long id);

    LeadEvaluation selectLeadEvaluationByReport(long reportId);

    void insertIndividualEvaluation(IndividualEvaluation individualEvaluation);

    void insertIndividualEvaluations(IndividualEvaluation[] tabIndividualEvaluations);

    void updateIndividualEvaluation(IndividualEvaluation individualEvaluation);

    void deleteIndividualEvaluation(long id);

    IndividualEvaluation selectIndividualEvaluation(long id);

    List<IndividualEvaluation> selectIndividualEvaluationsByReport(long reportId);
}