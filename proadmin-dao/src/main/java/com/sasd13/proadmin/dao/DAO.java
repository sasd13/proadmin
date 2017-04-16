package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IDAO;

public abstract class DAO implements IDAO {

	protected ITeacherDAO teacherDAO;
	protected IProjectDAO projectDAO;
	protected IStudentDAO studentDAO;
	protected ITeamDAO teamDAO;
	protected IStudentTeamDAO studentTeamDAO;
	protected IRunningDAO runningDAO;
	protected IAcademicLevelDAO academicLevelDAO;
	protected IRunningTeamDAO runningTeamDAO;
	protected IReportDAO reportDAO;
	protected ILeadEvaluationDAO leadEvaluationDAO;
	protected IIndividualEvaluationDAO individualEvaluationDAO;

	private StudentTeamDeepReader studentTeamDeepReader;
	private RunningDeepReader runningDeepReader;
	private RunningTeamDeepReader runningTeamDeepReader;
	private ReportDeepReader reportDeepReader;
	private LeadEvaluationDeepReader leadEvaluationDeepReader;
	private IndividualEvaluationDeepReader individualEvaluationDeepReader;

	protected DAO(
			ITeacherDAO teacherDAO, 
			IProjectDAO projectDAO, 
			IStudentDAO studentDAO, 
			ITeamDAO teamDAO, 
			IStudentTeamDAO studentTeamDAO, 
			IRunningDAO runningDAO, 
			IAcademicLevelDAO academicLevelDAO, 
			IRunningTeamDAO runningTeamDAO, 
			IReportDAO reportDAO, 
			ILeadEvaluationDAO leadEvaluationDAO, 
			IIndividualEvaluationDAO individualEvaluationDAO) {

		this.teacherDAO = teacherDAO;
		this.projectDAO = projectDAO;
		this.studentDAO = studentDAO;
		this.teamDAO = teamDAO;
		this.studentTeamDAO = studentTeamDAO;
		this.runningDAO = runningDAO;
		this.academicLevelDAO = academicLevelDAO;
		this.runningTeamDAO = runningTeamDAO;
		this.reportDAO = reportDAO;
		this.leadEvaluationDAO = leadEvaluationDAO;
		this.individualEvaluationDAO = individualEvaluationDAO;

		studentTeamDeepReader = new StudentTeamDeepReader(studentTeamDAO, studentDAO, teamDAO);
		runningDeepReader = new RunningDeepReader(runningDAO, projectDAO, teacherDAO);
		runningTeamDeepReader = new RunningTeamDeepReader(runningTeamDAO, runningDeepReader, teamDAO, academicLevelDAO);
		reportDeepReader = new ReportDeepReader(reportDAO, runningTeamDeepReader);
		leadEvaluationDeepReader = new LeadEvaluationDeepReader(leadEvaluationDAO, reportDeepReader, studentDAO);
		individualEvaluationDeepReader = new IndividualEvaluationDeepReader(individualEvaluationDAO, reportDeepReader, studentDAO);
	}

	public Object getSession(Class<?> mClass) {
		if (ITeacherDAO.class.isAssignableFrom(mClass)) {
			return teacherDAO;
		} else if (IProjectDAO.class.isAssignableFrom(mClass)) {
			return projectDAO;
		} else if (IStudentDAO.class.isAssignableFrom(mClass)) {
			return studentDAO;
		} else if (ITeamDAO.class.isAssignableFrom(mClass)) {
			return teamDAO;
		} else if (IStudentTeamDAO.class.isAssignableFrom(mClass)) {
			return studentTeamDAO;
		} else if (IRunningDAO.class.isAssignableFrom(mClass)) {
			return runningDAO;
		} else if (IAcademicLevelDAO.class.isAssignableFrom(mClass)) {
			return academicLevelDAO;
		} else if (IRunningTeamDAO.class.isAssignableFrom(mClass)) {
			return runningTeamDAO;
		} else if (IReportDAO.class.isAssignableFrom(mClass)) {
			return reportDAO;
		} else if (ILeadEvaluationDAO.class.isAssignableFrom(mClass)) {
			return leadEvaluationDAO;
		} else if (IIndividualEvaluationDAO.class.isAssignableFrom(mClass)) {
			return individualEvaluationDAO;
		} else {
			return null;
		}
	}

	public DeepReader<?> getDeepReader(Class<?> mClass) {
		if (StudentTeamDeepReader.class.isAssignableFrom(mClass)) {
			return studentTeamDeepReader;
		} else if (RunningDeepReader.class.isAssignableFrom(mClass)) {
			return runningDeepReader;
		} else if (RunningTeamDeepReader.class.isAssignableFrom(mClass)) {
			return runningTeamDeepReader;
		} else if (ReportDeepReader.class.isAssignableFrom(mClass)) {
			return reportDeepReader;
		} else if (LeadEvaluationDeepReader.class.isAssignableFrom(mClass)) {
			return leadEvaluationDeepReader;
		} else if (IndividualEvaluationDeepReader.class.isAssignableFrom(mClass)) {
			return individualEvaluationDeepReader;
		} else {
			return null;
		}
	}
}
