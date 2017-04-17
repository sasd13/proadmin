package com.sasd13.proadmin.util.wrapper.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
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
import com.sasd13.proadmin.util.wrapper.WrapperException;
import com.sasd13.proadmin.util.wrapper.update.member.StudentTeamUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

public class UpdateWrapperFactory {

	@SuppressWarnings("unchecked")
	public static <T> IUpdateWrapper<T> make(Class<T> mClass) {
		if (Project.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new ProjectUpdateWrapper();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new TeacherUpdateWrapper();
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new StudentUpdateWrapper();
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new TeamUpdateWrapper();
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new StudentTeamUpdateWrapper();
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new RunningUpdateWrapper();
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new AcademicLevelUpdateWrapper();
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new RunningTeamUpdateWrapper();
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new ReportUpdateWrapper();
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new LeadEvaluationUpdateWrapper();
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new IndividualEvaluationUpdateWrapper();
		} else {
			throw new WrapperException("No wrapper found for '" + mClass.getName() + "'");
		}
	}
}
