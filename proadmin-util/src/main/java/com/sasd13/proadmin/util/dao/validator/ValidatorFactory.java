package com.sasd13.proadmin.util.dao.validator;

import com.sasd13.javaex.dao.validator.IValidator;
import com.sasd13.javaex.dao.validator.ValidatorException;
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
import com.sasd13.proadmin.util.dao.validator.member.StudentTeamValidator;
import com.sasd13.proadmin.util.dao.validator.member.StudentValidator;
import com.sasd13.proadmin.util.dao.validator.member.TeacherValidator;
import com.sasd13.proadmin.util.dao.validator.member.TeamValidator;
import com.sasd13.proadmin.util.dao.validator.project.ProjectValidator;
import com.sasd13.proadmin.util.dao.validator.running.IndividualEvaluationValidator;
import com.sasd13.proadmin.util.dao.validator.running.LeadEvaluationValidator;
import com.sasd13.proadmin.util.dao.validator.running.ReportValidator;
import com.sasd13.proadmin.util.dao.validator.running.RunningTeamValidator;
import com.sasd13.proadmin.util.dao.validator.running.RunningValidator;

public class ValidatorFactory {

	@SuppressWarnings("unchecked")
	public static <T> IValidator<T> make(Class<T> mClass) throws ValidatorException {
		if (Project.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new ProjectValidator();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new TeacherValidator();
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new StudentValidator();
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new TeamValidator();
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new StudentTeamValidator();
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new RunningValidator();
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new AcademicLevelValidator();
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new RunningTeamValidator();
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new ReportValidator();
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new LeadEvaluationValidator();
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IValidator<T>) new IndividualEvaluationValidator();
		} else {
			throw new ValidatorException("Class '" + mClass.getName() + "' has no validator");
		}
	}
}
