package com.sasd13.proadmin.util.wrapper.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.IStudentTeam;
import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.bean.running.IIndividualEvaluation;
import com.sasd13.proadmin.bean.running.ILeadEvaluation;
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.bean.running.IRunningTeam;
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
		if (IProject.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new ProjectUpdateWrapper();
		} else if (ITeacher.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new TeacherUpdateWrapper();
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new StudentUpdateWrapper();
		} else if (ITeam.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new TeamUpdateWrapper();
		} else if (IStudentTeam.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new StudentTeamUpdateWrapper();
		} else if (IRunning.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new RunningUpdateWrapper();
		} else if (IRunningTeam.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new RunningTeamUpdateWrapper();
		} else if (IReport.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new ReportUpdateWrapper();
		} else if (ILeadEvaluation.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new LeadEvaluationUpdateWrapper();
		} else if (IIndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IUpdateWrapper<T>) new IndividualEvaluationUpdateWrapper();
		} else {
			return null;
		}
	}
}
