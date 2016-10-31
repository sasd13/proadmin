package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.javaex.dao.ILayeredDAO;
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
import com.sasd13.proadmin.dao.member.IStudentDAO;
import com.sasd13.proadmin.dao.member.IStudentTeamDAO;
import com.sasd13.proadmin.dao.member.ITeacherDAO;
import com.sasd13.proadmin.dao.member.ITeamDAO;
import com.sasd13.proadmin.dao.member.StudentTeamDeepReader;
import com.sasd13.proadmin.dao.project.IProjectDAO;
import com.sasd13.proadmin.dao.running.IIndividualEvaluationDAO;
import com.sasd13.proadmin.dao.running.ILeadEvaluationDAO;
import com.sasd13.proadmin.dao.running.IReportDAO;
import com.sasd13.proadmin.dao.running.IRunningDAO;
import com.sasd13.proadmin.dao.running.IRunningTeamDAO;
import com.sasd13.proadmin.dao.running.IndividualEvaluationDeepReader;
import com.sasd13.proadmin.dao.running.LeadEvaluationDeepReader;
import com.sasd13.proadmin.dao.running.ReportDeepReader;
import com.sasd13.proadmin.dao.running.RunningDeepReader;
import com.sasd13.proadmin.dao.running.RunningTeamDeepReader;

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
		reportDeepReader = new ReportDeepReader(reportDAO);
		runningTeamDeepReader = new RunningTeamDeepReader(runningTeamDAO, runningDeepReader, teamDAO, reportDeepReader);
		leadEvaluationDeepReader = new LeadEvaluationDeepReader(leadEvaluationDAO, studentDAO);
		individualEvaluationDeepReader = new IndividualEvaluationDeepReader(individualEvaluationDAO, studentDAO);
	}

	public <T> IEntityDAO<T> getEntityDAO(Class<T> mClass) throws DAOException {
		if (Teacher.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) teacherDAO;
		} else if (Project.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) projectDAO;
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) studentDAO;
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) teamDAO;
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) studentTeamDAO;
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) runningDAO;
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) academicLevelDAO;
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) runningTeamDAO;
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) reportDAO;
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) leadEvaluationDAO;
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IEntityDAO<T>) individualEvaluationDAO;
		} else {
			throw new DAOException("Entity " + mClass.getSimpleName() + " is unknown");
		}
	}

	public <T> DeepReader<T> getDeepReader(Class<T> mClass) throws DAOException {
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
