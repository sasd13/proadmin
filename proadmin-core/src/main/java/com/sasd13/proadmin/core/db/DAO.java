package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.DBException;
import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.javaex.db.IDAO;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.StudentTeam;
import com.sasd13.proadmin.core.bean.running.Team;

public abstract class DAO implements IDAO {
	
	protected TeacherDAO teacherDAO;
	protected ProjectDAO projectDAO;
	protected RunningDAO runningDAO;
	protected TeamDAO teamDAO;
	protected StudentDAO studentDAO;
	protected StudentTeamDAO studentTeamDAO;
	protected ReportDAO reportDAO;
	protected LeadEvaluationDAO leadEvaluationDAO;
	protected IndividualEvaluationDAO individualEvaluationDAO;
	
	private RunningDeepReader runningDeepReader;
	private StudentTeamDeepReader studentTeamDeepReader;
	private ReportDeepReader reportDeepReader;
	private LeadEvaluationDeepReader leadEvaluationDeepReader;
	private IndividualEvaluationDeepReader individualEvaluationDeepReader;
	
	protected DAO(TeacherDAO teacherDAO, ProjectDAO projectDAO, 
			RunningDAO runningDAO, TeamDAO teamDAO, StudentDAO studentDAO, StudentTeamDAO studentTeamDAO, 
			ReportDAO reportDAO, LeadEvaluationDAO leadEvaluationDAO, IndividualEvaluationDAO individualEvaluationDAO) {
		
		this.teacherDAO = teacherDAO;
		this.projectDAO = projectDAO;
		this.runningDAO = runningDAO;
		this.teamDAO = teamDAO;
		this.studentDAO = studentDAO;
		this.studentTeamDAO = studentTeamDAO;
		this.reportDAO = reportDAO;
		this.leadEvaluationDAO = leadEvaluationDAO;
		this.individualEvaluationDAO = individualEvaluationDAO;
		
		runningDeepReader = new RunningDeepReader(runningDAO, teacherDAO, projectDAO, teamDAO);
		studentTeamDeepReader = new StudentTeamDeepReader(studentTeamDAO, studentDAO, teamDAO);
		reportDeepReader = new ReportDeepReader(reportDAO, leadEvaluationDAO, individualEvaluationDAO);
		leadEvaluationDeepReader = new LeadEvaluationDeepReader(leadEvaluationDAO, studentDAO);
		individualEvaluationDeepReader = new IndividualEvaluationDeepReader(individualEvaluationDAO, studentDAO);
	}
	
	public <T> IEntityDAO<T> getEntityDAO(Class<T> mClass) throws DBException {
		if (Teacher.class.equals(mClass)) {
			return (IEntityDAO<T>) teacherDAO;
		} else if (Project.class.equals(mClass)) {
			return (IEntityDAO<T>) projectDAO;
		} else if (Running.class.equals(mClass)) {
			return (IEntityDAO<T>) runningDAO;
		} else if (Team.class.equals(mClass)) {
			return (IEntityDAO<T>) teamDAO;
		} else if (Student.class.equals(mClass)) {
			return (IEntityDAO<T>) studentDAO;
		} else if (StudentTeam.class.equals(mClass)) {
			return (IEntityDAO<T>) studentTeamDAO;
		} else if (Report.class.equals(mClass)) {
			return (IEntityDAO<T>) reportDAO;
		} else if (LeadEvaluation.class.equals(mClass)) {
			return (IEntityDAO<T>) leadEvaluationDAO;
		} else if (IndividualEvaluation.class.equals(mClass)) {
			return (IEntityDAO<T>) individualEvaluationDAO;
		} else {
			throw new DBException("Class '" + mClass.getName() + "' has no entity dao");
		}
	}
	
	public <T> DeepReader<T> getDeepReader(Class<T> mClass) throws DBException {
		if (Running.class.equals(mClass)) {
			return (DeepReader<T>) runningDeepReader;
		} else if (StudentTeam.class.equals(mClass)) {
			return (DeepReader<T>) studentTeamDeepReader;
		} else if (Report.class.equals(mClass)) {
			return (DeepReader<T>) reportDeepReader;
		} else if (LeadEvaluation.class.equals(mClass)) {
			return (DeepReader<T>) leadEvaluationDeepReader;
		} else if (IndividualEvaluation.class.equals(mClass)) {
			return (DeepReader<T>) individualEvaluationDeepReader;
		} else {
			throw new DBException("Class '" + mClass.getName() + "' has no deep reader");
		}
	}
}
