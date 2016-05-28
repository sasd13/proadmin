package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.ProjectDAO;
import com.sasd13.proadmin.dao.ReportDAO;
import com.sasd13.proadmin.dao.RunningDAO;
import com.sasd13.proadmin.dao.RunningTeamDAO;
import com.sasd13.proadmin.dao.StudentDAO;
import com.sasd13.proadmin.dao.StudentTeamDAO;
import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.dao.TeamDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;

public class ExpressionFactory {
	
	public static IExpression make(Class<? extends IEntityDAO<?>> mClass) throws ConditionException {
		if (TeacherDAO.class.equals(mClass)) {
			return TeacherExpression.INSTANCE;
		} else if (ProjectDAO.class.equals(mClass)) {
			return ProjectExpression.INSTANCE;
		} else if (StudentDAO.class.equals(mClass)) {
			return StudentExpression.INSTANCE;
		} else if (TeamDAO.class.equals(mClass)) {
			return TeamExpression.INSTANCE;
		} else if (StudentTeamDAO.class.equals(mClass)) {
			return StudentTeamExpression.INSTANCE;
		} else if (RunningDAO.class.equals(mClass)) {
			return RunningExpression.INSTANCE;
		} else if (RunningTeamDAO.class.equals(mClass)) {
			return RunningTeamExpression.INSTANCE;
		} else if (ReportDAO.class.equals(mClass)) {
			return ReportExpression.INSTANCE;
		} else if (LeadEvaluationDAO.class.equals(mClass)) {
			return LeadEvaluationExpression.INSTANCE;
		} else if (IndividualEvaluationDAO.class.equals(mClass)) {
			return IndividualEvaluationExpression.INSTANCE;
		} else {
			throw new ConditionException("Class '" + mClass.getName() + "' has no expression parser");
		}
	}
}
