package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.DBException;
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
}
