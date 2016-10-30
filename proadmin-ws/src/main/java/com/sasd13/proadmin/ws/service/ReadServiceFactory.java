package com.sasd13.proadmin.ws.service;

import com.sasd13.javaex.service.IReadService;
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
import com.sasd13.proadmin.ws.service.member.StudentReadService;
import com.sasd13.proadmin.ws.service.member.StudentTeamReadService;
import com.sasd13.proadmin.ws.service.member.TeacherReadService;
import com.sasd13.proadmin.ws.service.member.TeamReadService;
import com.sasd13.proadmin.ws.service.project.ProjectReadService;
import com.sasd13.proadmin.ws.service.running.IndividualEvaluationReadService;
import com.sasd13.proadmin.ws.service.running.LeadEvaluationReadService;
import com.sasd13.proadmin.ws.service.running.ReportReadService;
import com.sasd13.proadmin.ws.service.running.RunningReadService;
import com.sasd13.proadmin.ws.service.running.RunningTeamReadService;

public class ReadServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> IReadService<T> make(Class<T> mClass) throws WSException {
		if (Project.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new ProjectReadService();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new TeacherReadService();
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new StudentReadService();
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new TeamReadService();
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new StudentTeamReadService();
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new RunningReadService();
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new AcademicLevelReadService();
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new RunningTeamReadService();
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new ReportReadService();
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new LeadEvaluationReadService();
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IReadService<T>) new IndividualEvaluationReadService();
		} else {
			throw new WSException("Class '" + mClass.getName() + "' has not manageService");
		}
	}
}
