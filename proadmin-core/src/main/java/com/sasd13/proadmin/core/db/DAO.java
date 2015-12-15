package com.sasd13.proadmin.core.db;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;

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

    public abstract void open();

    public abstract void close();
    
    public long persistTeacher(Teacher teacher) {
        long id;
        
        if (selectTeacher(teacher.getId()) == null) {
            id = insertTeacher(teacher);
        } else {
            updateTeacher(teacher);
            
            id = teacher.getId();
        }
        
        return id;
    }

    public long insertTeacher(Teacher teacher) {
        long id = teacherDAO.insert(teacher);
        if (id > 0) {
            teacher.setId(id);
        }

        return id;
    }

    public void updateTeacher(Teacher teacher) {
        teacherDAO.update(teacher);
    }
    
    public void deleteTeacher(long id) {
        teacherDAO.delete(id);
    }

    public Teacher selectTeacher(long id) {
        return teacherDAO.select(id);
    }

    public Teacher selectTeacherByNumber(String number) {
        return teacherDAO.selectByNumber(number);
    }

    public Teacher selectTeacherByEmail(String email) {
        return teacherDAO.selectByEmail(email);
    }
    
    public List<Teacher> selectAllTeachers() {
        return teacherDAO.selectAll();
    }
    
    public long persistProject(Project project) {
        long id;
        
        if (selectProject(project.getId()) == null) {
            id = insertProject(project);
        } else {
            updateProject(project);
            
            id = project.getId();
        }
        
        return id;
    }

    public long insertProject(Project project) {
        long id = projectDAO.insert(project);
        if (id > 0) {
            project.setId(id);
        }

        return id;
    }

    public void updateProject(Project project) {
        projectDAO.update(project);
    }
    
    public void deleteProject(long id) {
        projectDAO.delete(id);
    }

    public Project selectProject(long id) {
        return projectDAO.select(id);
    }

    public List<Project> selectProjectsByAcademicLevel(AcademicLevel academicLevel) {
        return projectDAO.selectByAcademicLevel(academicLevel);
    }

    public List<Project> selectAllProjects() {
        return projectDAO.selectAll();
    }
    
    public long persistRunning(Running running) {
        long id;
        
        if (selectRunning(running.getId()) == null) {
            id = insertRunning(running);
        } else {
            updateRunning(running);
            
            id = running.getId();
        }
        
        return id;
    }

    public long insertRunning(Running running) {
        long id = runningDAO.insert(running);
        if (id > 0) {
            running.setId(id);
        }

        return id;
    }

    public void updateRunning(Running running) {
        runningDAO.update(running);
    }

    public void deleteRunning(long id) {
        runningDAO.delete(id);
    }
    
    public Running selectRunning(long id) {
        Running running = runningDAO.select(id);
        
        if (running != null) {
            running.setTeacher(selectTeacher(running.getTeacher().getId()));
            running.setProject(selectProject(running.getProject().getId()));
        }
        
        return running;
    }

    public List<Running> selectRunningsByTeacher(long teacherId) {
        return runningDAO.selectByTeacher(teacherId);
    }
    
    public List<Running> selectAllRunnings() {
        List<Running> list = runningDAO.selectAll();
        
        for (Running running : list) {
            list.set(list.indexOf(running), selectRunning(running.getId()));
        }
        
        return list;
    }
    
    public long persistTeam(Team team) {
        long id;
        
        if (selectTeam(team.getId()) == null) {
            id = insertTeam(team);
        } else {
            updateTeam(team);
            
            id = team.getId();
        }
        
        return id;
    }

    public long insertTeam(Team team) {
        long id = teamDAO.insert(team);
        if (id > 0) {
            team.setId(id);
        }

        return id;
    }

    public void updateTeam(Team team) {
        teamDAO.update(team);
    }

    public void deleteTeam(long id) {
        teamDAO.delete(id);
    }

    public Team selectTeam(long id) {
        Team team = teamDAO.select(id);
        
        if (team != null) {
            List<Student> list = selectStudentsByTeam(team.getId());
            
            team.setStudents(list.toArray(new Student[list.size()]));
        }
        
        return team;
    }

    public List<Team> selectTeamsByRunning(long runningId) {
        return teamDAO.selectByRunning(runningId);
    }
    
    public List<Team> selectAllTeams() {
        List<Team> list = teamDAO.selectAll();
        
        for (Team team : list) {
            list.set(list.indexOf(team), selectTeam(team.getId()));
        }
        
        return list;
    }
    
    public long persistStudent(Student student, long teamId) {
        long id = 0;
        
        if (selectStudent(student.getId()) == null) {
            id = insertStudent(student, teamId);
        } else {
            updateStudent(student);
            
            id = student.getId();
        }
        
        return id;
    }

    public long insertStudent(Student student, long teamId) {
        long id = studentDAO.insert(student);
        if (id > 0) {
            student.setId(id);

            studentTeamDAO.insertStudentInTeam(id, teamId);
        }

        return id;
    }

    public void updateStudent(Student student) {
        studentDAO.update(student);
    }
    
    public void deleteStudent(long id) {
        studentDAO.delete(id);
    }

    public void deleteStudentFromTeam(long studentId, long teamId) {
        studentTeamDAO.deleteStudentFromTeam(studentId, teamId);
    }

    public Student selectStudent(long id) {
        return studentDAO.select(id);
    }

    public Student selectStudentByNumber(String number) {
        return studentDAO.selectByNumber(number);
    }

    public List<Student> selectStudentsByTeam(long teamId) {
        List<Student> list = new ArrayList<>();

        List<Long> listStudentIds = studentTeamDAO.selectByTeam(teamId);
        for (long studentId : listStudentIds) {
            list.add(studentDAO.select(studentId));
        }

        return list;
    }
    
    public List<Student> selectAllStudents() {
        return studentDAO.selectAll();
    }
    
    public long persistReport(Report report) {
        long id = 0;
        
        if (selectReport(report.getId()) == null) {
            id = insertReport(report);
        } else {
            updateReport(report);
            
            id = report.getId();
        }
        
        return id;
    }

    public long insertReport(Report report) {
        long id = reportDAO.insert(report);
        if (id > 0) {
            report.setId(id);

            insertLeadEvaluation(report.getLeadEvaluation());
            insertIndividualEvaluations(report.getIndividualEvaluations());
        }

        return id;
    }

    public void updateReport(Report report) {
        reportDAO.update(report);

        updateLeadEvaluation(report.getLeadEvaluation());
        updateIndividualEvaluations(report.getIndividualEvaluations());
    }

    public void deleteReport(long id) {
        deleteLeadEvaluation(id);
        deleteIndividualEvaluations(id);

        reportDAO.delete(id);
    }

    public Report selectReport(long id) {
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

    public List<Report> selectReportsByTeam(long teamId) {
        List<Report> list = reportDAO.selectByTeam(teamId);
        
        for (Report report : list) {
            list.set(list.indexOf(report), selectReport(report.getId()));
        }

        return list;
    }
    
    public List<Report> selectAllReports() {
        List<Report> list = reportDAO.selectAll();
        
        for (Report report : list) {
            list.set(list.indexOf(report), selectReport(report.getId()));
        }
        
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
