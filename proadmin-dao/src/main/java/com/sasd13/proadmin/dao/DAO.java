package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.javaex.dao.ILayeredDAO;
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

	protected ITeacherDAO iTeacherDAO;
	protected IProjectDAO iProjectDAO;
	protected IStudentDAO iStudentDAO;
	protected ITeamDAO iTeamDAO;
	protected IStudentTeamDAO iStudentTeamDAO;
	protected IRunningDAO iRunningDAO;
	protected IRunningTeamDAO iRunningTeamDAO;
	protected IReportDAO iReportDAO;
	private ILeadEvaluationDAO iLeadEvaluationDAO;
	private IIndividualEvaluationDAO iIndividualEvaluationDAO;

	private StudentTeamDeepReader studentTeamDeepReader;
	private RunningDeepReader runningDeepReader;
	private RunningTeamDeepReader runningTeamDeepReader;
	private ReportDeepReader reportDeepReader;
	private LeadEvaluationDeepReader leadEvaluationDeepReader;
	private IndividualEvaluationDeepReader individualEvaluationDeepReader;

	protected DAO(ITeacherDAO iTeacherDAO, 
			IProjectDAO iProjectDAO, 
			IStudentDAO iStudentDAO, 
			ITeamDAO iTeamDAO, 
			IStudentTeamDAO iStudentTeamDAO, 
			IRunningDAO iRunningDAO, 
			IRunningTeamDAO iRunningTeamDAO,
			IReportDAO iReportDAO) {

		this.iTeacherDAO = iTeacherDAO;
		this.iProjectDAO = iProjectDAO;
		this.iStudentDAO = iStudentDAO;
		this.iTeamDAO = iTeamDAO;
		this.iStudentTeamDAO = iStudentTeamDAO;
		this.iRunningDAO = iRunningDAO;
		this.iRunningTeamDAO = iRunningTeamDAO;
		this.iReportDAO = iReportDAO;
		this.iLeadEvaluationDAO = iReportDAO.getLeadEvaluationDAO();
		this.iIndividualEvaluationDAO = iReportDAO.getIndividualEvaluationDAO();

		studentTeamDeepReader = new StudentTeamDeepReader(iStudentTeamDAO, iStudentDAO, iTeamDAO);
		runningDeepReader = new RunningDeepReader(iRunningDAO, iTeacherDAO, iProjectDAO);
		runningTeamDeepReader = new RunningTeamDeepReader(iRunningTeamDAO, iRunningDAO, iTeamDAO, iReportDAO);
		reportDeepReader = new ReportDeepReader(iReportDAO, iLeadEvaluationDAO, iIndividualEvaluationDAO);
		leadEvaluationDeepReader = new LeadEvaluationDeepReader(iLeadEvaluationDAO, iStudentDAO);
		individualEvaluationDeepReader = new IndividualEvaluationDeepReader(iIndividualEvaluationDAO, iStudentDAO);
	}

	public <T> IEntityDAO<T> getEntityDAO(Class<T> mClass) throws DAOException {
		if (Teacher.class.equals(mClass)) {
			return (IEntityDAO<T>) iTeacherDAO;
		} else if (Project.class.equals(mClass)) {
			return (IEntityDAO<T>) iProjectDAO;
		} else if (Student.class.equals(mClass)) {
			return (IEntityDAO<T>) iStudentDAO;
		} else if (Team.class.equals(mClass)) {
			return (IEntityDAO<T>) iTeamDAO;
		} else if (StudentTeam.class.equals(mClass)) {
			return (IEntityDAO<T>) iStudentTeamDAO;
		} else if (Running.class.equals(mClass)) {
			return (IEntityDAO<T>) iRunningDAO;
		} else if (RunningTeam.class.equals(mClass)) {
			return (IEntityDAO<T>) iRunningTeamDAO;
		} else if (Report.class.equals(mClass)) {
			return (IEntityDAO<T>) iReportDAO;
		} else if (LeadEvaluation.class.equals(mClass)) {
			return (IEntityDAO<T>) iLeadEvaluationDAO;
		} else if (IndividualEvaluation.class.equals(mClass)) {
			return (IEntityDAO<T>) iIndividualEvaluationDAO;
		} else {
			throw new DAOException("Class '" + mClass.getName() + "' has no entity dao");
		}
	}

	public <T> DeepReader<T> getDeepReader(Class<T> mClass) throws DAOException {
		if (StudentTeam.class.equals(mClass)) {
			return (DeepReader<T>) studentTeamDeepReader;
		} else if (Running.class.equals(mClass)) {
			return (DeepReader<T>) runningDeepReader;
		} else if (RunningTeam.class.equals(mClass)) {
			return (DeepReader<T>) runningTeamDeepReader;
		} else if (Report.class.equals(mClass)) {
			return (DeepReader<T>) reportDeepReader;
		} else if (LeadEvaluation.class.equals(mClass)) {
			return (DeepReader<T>) leadEvaluationDeepReader;
		} else if (IndividualEvaluation.class.equals(mClass)) {
			return (DeepReader<T>) individualEvaluationDeepReader;
		} else {
			throw new DAOException("Class '" + mClass.getName() + "' has no deep reader");
		}
	}
}
