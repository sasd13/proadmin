package com.sasd13.proadmin.util.validator;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
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
import com.sasd13.proadmin.util.validator.member.StudentTeamUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.member.StudentUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.member.TeacherUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.member.TeamUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.project.ProjectUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.running.IndividualEvaluationUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.running.LeadEvaluationUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.running.ReportUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.running.RunningTeamUpdateWrapperValidator;
import com.sasd13.proadmin.util.validator.running.RunningUpdateWrapperValidator;

public class UpdateWrapperValidatorFactory {

	public static IValidator<?> make(Class<?> mClass) throws ValidatorException {
		if (Project.class.isAssignableFrom(mClass)) {
			return new ProjectUpdateWrapperValidator();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return new TeacherUpdateWrapperValidator();
		} else if (Student.class.isAssignableFrom(mClass)) {
			return new StudentUpdateWrapperValidator();
		} else if (Team.class.isAssignableFrom(mClass)) {
			return new TeamUpdateWrapperValidator();
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return new StudentTeamUpdateWrapperValidator();
		} else if (Running.class.isAssignableFrom(mClass)) {
			return new RunningUpdateWrapperValidator();
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return new AcademicLevelUpdateWrapperValidator();
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return new RunningTeamUpdateWrapperValidator();
		} else if (Report.class.isAssignableFrom(mClass)) {
			return new ReportUpdateWrapperValidator();
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return new LeadEvaluationUpdateWrapperValidator();
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return new IndividualEvaluationUpdateWrapperValidator();
		} else {
			throw new ValidatorException("Entity " + mClass.getSimpleName() + " has no wrapper validator");
		}
	}
}
