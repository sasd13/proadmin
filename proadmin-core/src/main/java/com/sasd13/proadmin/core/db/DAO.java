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
		long id = 0;
		
		try {
			if (selectTeacher(teacher.getId()) == null) {
				id = insertTeacher(teacher);
			} else {
				id = teacher.getId();
				
				updateTeacher(teacher);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public long insertTeacher(Teacher teacher) {
		long id = 0;
		
		try {
			id = teacherDAO.insert(teacher);
			if (id > 0) {
				teacher.setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void updateTeacher(Teacher teacher) {
		try {
			teacherDAO.update(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public List<Teacher> selectTeacherByEmail(String email) {
		return teacherDAO.selectByEmail(email);
	}
	
	public List<Teacher> selectAllTeachers() {
		return teacherDAO.selectAll();
	}
	
	public long persistProject(Project project) {
		long id = 0;
		
		try {
			if (selectProject(project.getId()) == null) {
				id = insertProject(project);
			} else {
				id = project.getId();
				
				updateProject(project);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public long insertProject(Project project) {
		long id = 0;
		
		try {
			id = projectDAO.insert(project);
			if (id > 0) {
				project.setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void updateProject(Project project) {
		try {
			projectDAO.update(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProject(long id) {
		projectDAO.delete(id);
	}
	
	public Project selectProject(long id) {
		return projectDAO.select(id);
	}
	
	public Project selectProjectByCode(String code) {
		return projectDAO.selectByCode(code);
	}
	
	public List<Project> selectProjectsByAcademicLevel(AcademicLevel academicLevel) {
		return projectDAO.selectByAcademicLevel(academicLevel);
	}
	
	public List<Project> selectAllProjects() {
		return projectDAO.selectAll();
	}
	
	public long persistRunning(Running running) {
		long id = 0;
		
		try {
			if (selectRunning(running.getId()) == null) {
				id = insertRunning(running);
			} else {
				id = running.getId();
				
				updateRunning(running);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public long insertRunning(Running running) {
		long id = 0;
		
		try {
			id = runningDAO.insert(running);
			if (id > 0) {
				running.setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void updateRunning(Running running) {
		try {
			runningDAO.update(running);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRunning(long id) {
		runningDAO.delete(id);
	}
	
	public Running selectRunning(long id) {
		Running running = runningDAO.select(id);
		
		try {
			running.setTeacher(selectTeacher(running.getTeacher().getId()));
			running.setProject(selectProject(running.getProject().getId()));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return running;
	}
	
	public List<Running> selectRunningsByYear(int year) {
		return runningDAO.selectByYear(year);
	}
	
	public List<Running> selectRunningsByTeacher(long teacherId) {
		return runningDAO.selectByTeacher(teacherId);
	}
	
	public List<Running> selectRunningsByProject(long projectId) {
		return runningDAO.selectByProject(projectId);
	}
	
	public List<Running> selectAllRunnings() {
		List<Running> runnings = runningDAO.selectAll();
		
		for (Running running : runnings) {
			runnings.set(runnings.indexOf(running), selectRunning(running.getId()));
		}
		
		return runnings;
	}
	
	public long persistTeam(Team team) {
		long id = 0;
		
		try {
			if (selectTeam(team.getId()) == null) {
				id = insertTeam(team);
			} else {
				id = team.getId();
				
				updateTeam(team);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public long insertTeam(Team team) {
		long id = 0;
		
		try {
			id = teamDAO.insert(team);
			if (id > 0) {
				team.setId(id);
				
				insertStudentsInTeam(team);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	private void insertStudentsInTeam(Team team) {
		try {
			for (Student student : team.getStudents()) {
				studentTeamDAO.insertStudentInTeam(student.getId(), team.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateTeam(Team team) {
		try {
			teamDAO.update(team);
			
			studentTeamDAO.deleteByTeam(team.getId());
			
			insertStudentsInTeam(team);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTeam(long id) {
		teamDAO.delete(id);
	}
	
	public Team selectTeam(long id) {
		Team team = teamDAO.select(id);
		
		try {
			List<Student> students = selectStudentsByTeam(team.getId());
			
			team.setStudents(students.toArray(new Student[students.size()]));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return team;
	}
	
	public Team selectTeamsByCode(String code) {
		return teamDAO.selectByCode(code);
	}
	
	public List<Team> selectTeamsByRunning(long runningId) {
		return teamDAO.selectByRunning(runningId);
	}
	
	public List<Team> selectTeamsByStudent(long studentId) {
		List<Team> teams = new ArrayList<>();
		
		List<Long> teamIds = studentTeamDAO.selectByStudent(studentId);
		for (long teamId : teamIds) {
			teams.add(selectTeam(teamId));
		}
		
		return teams;
	}
	
	public List<Team> selectAllTeams() {
		List<Team> teams = teamDAO.selectAll();
		
		for (Team team : teams) {
			teams.set(teams.indexOf(team), selectTeam(team.getId()));
		}
		
		return teams;
	}
	
	public long persistStudent(Student student) {
		long id = 0;
		
		try {
			if (selectStudent(student.getId()) == null) {
				id = insertStudent(student);
			} else {
				id = student.getId();
				
				updateStudent(student);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public long insertStudent(Student student) {
		long id = 0;
		
		try {
			id = studentDAO.insert(student);
			if (id > 0) {
				student.setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void updateStudent(Student student) {
		try {
			studentDAO.update(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStudent(long id) {
		studentDAO.delete(id);
	}
	
	public Student selectStudent(long id) {
		return studentDAO.select(id);
	}
	
	public Student selectStudentByNumber(String number) {
		return studentDAO.selectByNumber(number);
	}
	
	public List<Student> selectStudentsByAcademicLevel(AcademicLevel academicLevel) {
		return studentDAO.selectByAcademicLevel(academicLevel);
	}
	
	public List<Student> selectStudentsByEmail(String email) {
		return studentDAO.selectByEmail(email);
	}
	
	public List<Student> selectStudentsByTeam(long teamId) {
		List<Student> students = new ArrayList<>();
		
		List<Long> studentIds = studentTeamDAO.selectByTeam(teamId);
		for (long studentId : studentIds) {
			students.add(selectStudent(studentId));
		}
		
		return students;
	}
	
	public List<Student> selectAllStudents() {
		return studentDAO.selectAll();
	}
	
	public long persistReport(Report report) {
		long id = 0;
		
		try {
			if (selectReport(report.getId()) == null) {
				id = insertReport(report);
			} else {
				id = report.getId();
				
				updateReport(report);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public long insertReport(Report report) {
		long id = 0;
		
		try {
			id = reportDAO.insert(report);
			if (id > 0) {
				report.setId(id);
				
				insertLeadEvaluation(report.getLeadEvaluation());
				insertIndividualEvaluations(report.getIndividualEvaluations());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void updateReport(Report report) {
		try {
			reportDAO.update(report);
			
			updateLeadEvaluation(report.getLeadEvaluation());
			updateIndividualEvaluations(report.getIndividualEvaluations());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteReport(long id) {
		reportDAO.delete(id);
	}
	
	public Report selectReport(long id) {
		Report report = reportDAO.select(id);
		
		try {
			report.setTeam(selectTeam(report.getTeam().getId()));
			
			bindLeadEvaluationForReport(report);
			addIndividualEvaluationsForReport(report);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return report;
	}
	
	public List<Report> selectReportsByTeam(long teamId) {
		List<Report> reports = reportDAO.selectByTeam(teamId);
		
		for (Report report : reports) {
			reports.set(reports.indexOf(report), selectReport(report.getId()));
		}
		
		return reports;
	}
	
	public List<Report> selectAllReports() {
		List<Report> reports = reportDAO.selectAll();
		
		for (Report report : reports) {
			reports.set(reports.indexOf(report), selectReport(report.getId()));
		}
		
		return reports;
	}
	
	private void insertLeadEvaluation(LeadEvaluation leadEvaluation) {
		try {
			long id = leadEvaluationDAO.insert(leadEvaluation);
			if (id > 0) {
				leadEvaluation.setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateLeadEvaluation(LeadEvaluation leadEvaluation) {
		try {
			leadEvaluationDAO.update(leadEvaluation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertIndividualEvaluations(IndividualEvaluation[] individualEvaluations) {
		try {
			long id;
			for (IndividualEvaluation individualEvaluation : individualEvaluations) {
				id = individualEvaluationDAO.insert(individualEvaluation);
				if (id > 0) {
					individualEvaluation.setId(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateIndividualEvaluations(IndividualEvaluation[] individualEvaluations) {
		try {
			for (IndividualEvaluation individualEvaluation : individualEvaluations) {
				individualEvaluationDAO.update(individualEvaluation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void bindLeadEvaluationForReport(Report report) {
		LeadEvaluation leadEvaluationToBind = leadEvaluationDAO.selectByReport(report.getId());
		
		LeadEvaluation leadEvaluation = report.getLeadEvaluation();
		
		leadEvaluation.setId(leadEvaluationToBind.getId());
		leadEvaluation.setPlanningMark(leadEvaluationToBind.getPlanningMark());
		leadEvaluation.setPlanningComment(leadEvaluationToBind.getPlanningComment());
		leadEvaluation.setCommunicationMark(leadEvaluationToBind.getCommunicationMark());
		leadEvaluation.setCommunicationComment(leadEvaluationToBind.getCommunicationComment());
		leadEvaluation.setStudent(selectStudent(leadEvaluationToBind.getStudent().getId()));
	}
	
	private void addIndividualEvaluationsForReport(Report report) {
		List<IndividualEvaluation> individualEvaluations = individualEvaluationDAO.selectByReport(report.getId());
		
		Student student;
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			student = individualEvaluation.getStudent();
			individualEvaluation.setStudent(selectStudent(student.getId()));
			
			report.addIndividualEvaluation(individualEvaluation);
		}
	}
}
