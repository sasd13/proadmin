package com.sasd13.proadmin.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.wsprovider.proadmin.bean.AcademicLevel;
import com.sasd13.wsprovider.proadmin.bean.member.Student;
import com.sasd13.wsprovider.proadmin.bean.member.Teacher;
import com.sasd13.wsprovider.proadmin.bean.project.Project;
import com.sasd13.wsprovider.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.wsprovider.proadmin.bean.running.LeadEvaluation;
import com.sasd13.wsprovider.proadmin.bean.running.Report;
import com.sasd13.wsprovider.proadmin.bean.running.Running;
import com.sasd13.wsprovider.proadmin.bean.running.Team;

public abstract class DAO {

    protected TeacherDAO teacherDAO;
    protected ProjectDAO projectDAO;
    protected RunningDAO runningDAO;
    protected TeamDAO teamDAO;
    protected StudentDAO studentDAO;
    protected StudentTeamDAO studentTeamDAO;
    protected ReportDAO reportDAO;
    protected LeadEvaluationDAO leadEvaluationDAO;
    protected IndividualEvaluationDAO individualEvaluationDAO;

    public abstract void init(Context context);

    protected abstract void open();

    protected abstract void close();

    public long insertTeacher(Teacher teacher) {
        long id;

        open();

        id = teacherDAO.insert(teacher);
        if (id > 0) {
            teacher.setId(id);
        }

        close();

        return id;
    }

    public void updateTeacher(Teacher teacher) {
        open();

        teacherDAO.update(teacher);

        close();
    }

    public Teacher selectTeacher(long id) {
        Teacher teacher;

        open();

        teacher = teacherDAO.select(id);

        close();

        return teacher;
    }

    public Teacher selectTeacherByNumber(String number) {
        Teacher teacher;

        open();

        teacher = teacherDAO.selectByNumber(number);

        close();

        return teacher;
    }

    public Teacher selectTeacherByEmail(String email) {
        Teacher teacher;

        open();

        teacher = teacherDAO.selectByEmail(email);

        close();

        return teacher;
    }

    public boolean containsTeacherByNumber(String number) {
        boolean contains;

        open();

        contains = teacherDAO.containsByNumber(number);

        close();

        return contains;
    }

    public boolean containsTeacherByEmail(String email) {
        boolean contains;

        open();

        contains = teacherDAO.containsByEmail(email);

        close();

        return contains;
    }

    public void persistProject(Project project) {
        open();

        if (!projectDAO.contains(project.getId())) {
            insertProject(project);
        } else {
            updateProject(project);
        }

        close();
    }

    public long insertProject(Project project) {
        long id;

        open();

        id = projectDAO.insert(project);
        if (id > 0) {
            project.setId(id);
        }

        close();

        return id;
    }

    public void updateProject(Project project) {
        open();

        projectDAO.update(project);

        close();
    }

    public Project selectProject(long id) {
        Project project;

        open();

        project = projectDAO.select(id);

        close();

        return project;
    }

    public List<Project> selectProjectsByAcademicLevel(AcademicLevel academicLevel) {
        List<Project> list;

        open();

        list = projectDAO.selectByAcademicLevel(academicLevel);

        close();

        return list;
    }

    public List<Project> selectAllProjects() {
        List<Project> list;

        open();

        list = projectDAO.selectAll();

        close();

        return list;
    }

    public long insertRunning(Running running) {
        long id;

        open();

        id = runningDAO.insert(running);
        if (id > 0) {
            running.setId(id);
        }

        close();

        return id;
    }

    public void updateRunning(Running running) {
        open();

        runningDAO.update(running);

        close();
    }

    public void deleteRunning(long id) {
        open();

        runningDAO.delete(id);

        close();
    }

    public List<Running> selectRunningsByTeacher(long teacherId) {
        List<Running> list;

        open();

        list = runningDAO.selectByTeacher(teacherId);

        close();

        return list;
    }

    public long insertTeam(Team team) {
        long id;

        open();

        id = teamDAO.insert(team);
        if (id > 0) {
            team.setId(id);
        }

        close();

        return id;
    }

    public void updateTeam(Team team) {
        open();

        teamDAO.update(team);

        close();
    }

    public void deleteTeam(long id) {
        open();

        teamDAO.delete(id);

        close();
    }

    public Team selectTeam(long id) {
        Team team;

        open();

        team = teamDAO.select(id);

        close();

        return team;
    }

    public List<Team> selectTeamsByRunning(long runningId) {
        List<Team> list;

        open();

        list = teamDAO.selectByRunning(runningId);

        close();

        return list;
    }

    public long insertStudent(Student student, long teamId) {
        long id;

        open();

        id = studentDAO.insert(student);
        if (id > 0) {
            student.setId(id);

            studentTeamDAO.insertStudentInTeam(id, teamId);
        }

        close();

        return id;
    }

    public void updateStudent(Student student) {
        open();

        studentDAO.update(student);

        close();
    }

    public void deleteStudentFromTeam(long studentId, long teamId) {
        open();

        studentTeamDAO.deleteStudentFromTeam(studentId, teamId);

        close();
    }

    public Student selectStudent(long id) {
        Student student;

        open();

        student = studentDAO.select(id);

        close();

        return student;
    }

    public Student selectStudentByNumber(String number) {
        Student student;

        open();

        student = studentDAO.selectByNumber(number);

        close();

        return student;
    }

    public List<Student> selectStudentsByTeam(long teamId) {
        List<Student> list = new ArrayList<>();

        open();

        List<Long> listStudentIds = studentTeamDAO.selectByTeam(teamId);
        for (long studentId : listStudentIds) {
            list.add(studentDAO.select(studentId));
        }

        close();

        return list;
    }

    public long insertReport(Report report) {
        long id;

        open();

        id = reportDAO.insert(report);
        if (id > 0) {
            report.setId(id);

            insertLeadEvaluation(report.getLeadEvaluation());
            insertIndividualEvaluations(report.getIndividualEvaluations());
        }

        close();

        return id;
    }

    public void updateReport(Report report) {
        open();

        reportDAO.update(report);

        updateLeadEvaluation(report.getLeadEvaluation());
        updateIndividualEvaluations(report.getIndividualEvaluations());

        close();
    }

    public void deleteReport(long id) {
        open();

        deleteLeadEvaluation(id);
        deleteIndividualEvaluations(id);

        reportDAO.delete(id);

        close();
    }

    public Report selectReport(long id) {
        Report report;

        open();

        report = performSelectReportWithEvaluations(id);

        close();

        return report;
    }

    public List<Report> selectReportsByTeam(long teamId) {
        List<Report> list;

        open();

        list = reportDAO.selectByTeam(teamId);
        for (Report report : list) {
            list.set(list.indexOf(report), performSelectReportWithEvaluations(report.getId()));
        }

        close();

        return list;
    }

    private void insertLeadEvaluation(LeadEvaluation leadEvaluation) {
        long id = leadEvaluationDAO.insert(leadEvaluation);
        if (id > 0) {
            leadEvaluation.setId(id);
        }
    }

    private void updateLeadEvaluation(LeadEvaluation leadEvaluation) {
        leadEvaluationDAO.update(leadEvaluation);
    }

    private void deleteLeadEvaluation(long reportId) {
        leadEvaluationDAO.deleteByReport(reportId);
    }

    private void insertIndividualEvaluations(IndividualEvaluation[] individualEvaluations) {
        long id;
        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            id = individualEvaluationDAO.insert(individualEvaluation);
            if (id > 0) {
                individualEvaluation.setId(id);
            }
        }
    }

    private void updateIndividualEvaluations(IndividualEvaluation[] individualEvaluations) {
        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            individualEvaluationDAO.update(individualEvaluation);
        }
    }

    private void deleteIndividualEvaluations(long reportId) {
        individualEvaluationDAO.deleteByReport(reportId);
    }

    private Report performSelectReportWithEvaluations(long id) {
        Report report = reportDAO.select(id);

        try {
            setTeamForReport(report);

            bindLeadEvaluationForReport(report);
            addIndividualEvaluationsForReport(report);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return report;
    }

    private void setTeamForReport(Report report) {
        Team team = teamDAO.select(report.getTeam().getId());
        report.setTeam(team);
    }

    private void bindLeadEvaluationForReport(Report report) {
        LeadEvaluation leadEvaluationToBind = leadEvaluationDAO.selectByReport(report.getId());

        LeadEvaluation leadEvaluation = report.getLeadEvaluation();

        leadEvaluation.setId(leadEvaluationToBind.getId());
        leadEvaluation.setPlanningMark(leadEvaluationToBind.getPlanningMark());
        leadEvaluation.setPlanningComment(leadEvaluationToBind.getPlanningComment());
        leadEvaluation.setCommunicationMark(leadEvaluationToBind.getCommunicationMark());
        leadEvaluation.setCommunicationComment(leadEvaluationToBind.getCommunicationComment());
        leadEvaluation.setStudent(studentDAO.select(leadEvaluationToBind.getStudent().getId()));
    }

    private void addIndividualEvaluationsForReport(Report report) {
        List<IndividualEvaluation> list = individualEvaluationDAO.selectByReport(report.getId());

        Student student;
        for (IndividualEvaluation individualEvaluation : list) {
            student = individualEvaluation.getStudent();
            individualEvaluation.setStudent(studentDAO.select(student.getId()));

            report.addIndividualEvaluation(individualEvaluation);
        }
    }
}
