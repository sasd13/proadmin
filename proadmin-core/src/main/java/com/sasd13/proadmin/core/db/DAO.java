package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.EntityDAO;
import com.sasd13.javaex.db.IDAO;

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
	
	@SuppressWarnings("rawtypes")
	@Override
	public EntityDAO getEntityDAO(Class mClass) {
		if ("Teacher".equals(mClass.getSimpleName())) {
			return teacherDAO;
		} else if ("Project".equals(mClass.getSimpleName())) {
			return projectDAO;
		} else if ("Running".equals(mClass.getSimpleName())) {
			return runningDAO;
		} else if ("Team".equals(mClass.getSimpleName())) {
			return teamDAO;
		} else if ("Student".equals(mClass.getSimpleName())) {
			return studentDAO;
		} else if ("StudentTeam".equals(mClass.getSimpleName())) {
			return studentTeamDAO;
		} else if ("Report".equals(mClass.getSimpleName())) {
			return reportDAO;
		} else if ("LeadEvaluation".equals(mClass.getSimpleName())) {
			return leadEvaluationDAO;
		} else if ("IndividualEvaluation".equals(mClass.getSimpleName())) {
			return individualEvaluationDAO;
		} else {
			return null;
		}
	}
}
