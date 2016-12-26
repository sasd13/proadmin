package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.ILayeredDAO;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

@SuppressWarnings("unchecked")
public abstract class DAO implements ILayeredDAO {

	protected ITeacherDAO teacherDAO;
	protected IProjectDAO projectDAO;
	protected IStudentDAO studentDAO;
	protected ITeamDAO teamDAO;
	protected IStudentTeamDAO studentTeamDAO;
	protected IRunningDAO runningDAO;
	protected IAcademicLevelDAO academicLevelDAO;
	protected IRunningTeamDAO runningTeamDAO;
	protected IReportDAO reportDAO;
	private ILeadEvaluationDAO leadEvaluationDAO;
	private IIndividualEvaluationDAO individualEvaluationDAO;

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
			IReportDAO reportDAO) {

		this.teacherDAO = teacherDAO;
		this.projectDAO = projectDAO;
		this.studentDAO = studentDAO;
		this.teamDAO = teamDAO;
		this.studentTeamDAO = studentTeamDAO;
		this.runningDAO = runningDAO;
		this.academicLevelDAO = academicLevelDAO;
		this.runningTeamDAO = runningTeamDAO;
		this.reportDAO = reportDAO;
		this.leadEvaluationDAO = reportDAO.getLeadEvaluationDAO();
		this.individualEvaluationDAO = reportDAO.getIndividualEvaluationDAO();

		studentTeamDeepReader = new StudentTeamDeepReader(studentTeamDAO, studentDAO, teamDAO);
		runningDeepReader = new RunningDeepReader(runningDAO, teacherDAO, projectDAO);
		leadEvaluationDeepReader = new LeadEvaluationDeepReader(leadEvaluationDAO, studentDAO);
		individualEvaluationDeepReader = new IndividualEvaluationDeepReader(individualEvaluationDAO, studentDAO);
		reportDeepReader = new ReportDeepReader(reportDAO, leadEvaluationDeepReader, individualEvaluationDeepReader);
		runningTeamDeepReader = new RunningTeamDeepReader(runningTeamDAO, runningDeepReader, teamDAO, academicLevelDAO, reportDeepReader);
	}

	@Override
	public <T> ISession<T> getSession(Class<T> mClass) {
		if (Teacher.class.isAssignableFrom(mClass)) {
			return (ISession<T>) teacherDAO;
		} else if (Project.class.isAssignableFrom(mClass)) {
			return (ISession<T>) projectDAO;
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (ISession<T>) studentDAO;
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (ISession<T>) teamDAO;
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (ISession<T>) studentTeamDAO;
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (ISession<T>) runningDAO;
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (ISession<T>) academicLevelDAO;
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (ISession<T>) runningTeamDAO;
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (ISession<T>) reportDAO;
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (ISession<T>) leadEvaluationDAO;
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (ISession<T>) individualEvaluationDAO;
		} else {
			throw new DAOException("Entity " + mClass.getSimpleName() + " is unknown");
		}
	}

	@Override
	public <T> DeepReader<T> getDeepReader(Class<T> mClass) {
		if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (DeepReader<T>) studentTeamDeepReader;
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (DeepReader<T>) runningDeepReader;
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (DeepReader<T>) runningTeamDeepReader;
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (DeepReader<T>) reportDeepReader;
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (DeepReader<T>) leadEvaluationDeepReader;
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (DeepReader<T>) individualEvaluationDeepReader;
		} else {
			throw new DAOException("DeepReader " + mClass.getSimpleName() + " is unknown");
		}
	}
}
