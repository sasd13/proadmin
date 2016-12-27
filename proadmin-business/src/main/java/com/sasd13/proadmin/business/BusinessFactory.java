package com.sasd13.proadmin.business;

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
import com.sasd13.proadmin.util.exception.BusinessException;

public class BusinessFactory {

	@SuppressWarnings("unchecked")
	public static <T> IBusiness<T> make(Class<T> mClass) throws BusinessException {
		if (Project.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new ProjectBusiness();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new TeacherBusiness();
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new StudentBusiness();
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new TeamBusiness();
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new StudentTeamBusiness();
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new RunningBusiness();
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new AcademicLevelBusiness();
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new RunningTeamBusiness();
		} else if (Report.class.isAssignableFrom(mClass) 
				|| LeadEvaluation.class.isAssignableFrom(mClass) 
				|| IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IBusiness<T>) new EmptyBusiness();
		} else {
			throw new BusinessException("Entity " + mClass.getSimpleName() + " has no business rule");
		}
	}
}
