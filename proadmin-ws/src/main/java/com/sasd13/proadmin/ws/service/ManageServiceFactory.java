package com.sasd13.proadmin.ws.service;

import com.sasd13.javaex.service.IManageService;
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
import com.sasd13.proadmin.ws.service.member.StudentManageService;
import com.sasd13.proadmin.ws.service.member.StudentTeamManageService;
import com.sasd13.proadmin.ws.service.member.TeacherManageService;
import com.sasd13.proadmin.ws.service.member.TeamManageService;
import com.sasd13.proadmin.ws.service.project.ProjectManageService;
import com.sasd13.proadmin.ws.service.running.IndividualEvaluationManageService;
import com.sasd13.proadmin.ws.service.running.LeadEvaluationManageService;
import com.sasd13.proadmin.ws.service.running.ReportManageService;
import com.sasd13.proadmin.ws.service.running.RunningManageService;
import com.sasd13.proadmin.ws.service.running.RunningTeamManageService;

public class ManageServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> IManageService<T> make(Class<T> mClass) throws WSException {
		if (Project.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new ProjectManageService();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new TeacherManageService();
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new StudentManageService();
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new TeamManageService();
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new StudentTeamManageService();
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new RunningManageService();
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new AcademicLevelManageService();
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new RunningTeamManageService();
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new ReportManageService();
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new LeadEvaluationManageService();
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IManageService<T>) new IndividualEvaluationManageService();
		} else {
			throw new WSException("Class '" + mClass.getName() + "' has not manageService");
		}
	}
}
