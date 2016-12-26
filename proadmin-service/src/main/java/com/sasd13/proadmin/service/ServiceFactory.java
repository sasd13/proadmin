package com.sasd13.proadmin.service;

import com.sasd13.javaex.service.ServiceException;
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
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.service.member.StudentService;
import com.sasd13.proadmin.ws.service.member.StudentTeamService;
import com.sasd13.proadmin.ws.service.member.TeacherService;
import com.sasd13.proadmin.ws.service.member.TeamService;
import com.sasd13.proadmin.ws.service.project.ProjectService;
import com.sasd13.proadmin.ws.service.running.IndividualEvaluationService;
import com.sasd13.proadmin.ws.service.running.LeadEvaluationService;
import com.sasd13.proadmin.ws.service.running.ReportService;
import com.sasd13.proadmin.ws.service.running.RunningService;
import com.sasd13.proadmin.ws.service.running.RunningTeamService;

public class ServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> Service<T> make(Class<T> mClass, DAO dao) {
		if (Project.class.isAssignableFrom(mClass)) {
			return (Service<T>) new ProjectService(dao);
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return (Service<T>) new TeacherService(dao);
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (Service<T>) new StudentService(dao);
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (Service<T>) new TeamService(dao);
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (Service<T>) new StudentTeamService(dao);
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (Service<T>) new RunningService(dao);
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (Service<T>) new AcademicLevelService(dao);
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (Service<T>) new RunningTeamService(dao);
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (Service<T>) new ReportService(dao);
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (Service<T>) new LeadEvaluationService(dao);
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (Service<T>) new IndividualEvaluationService(dao);
		} else {
			throw new ServiceException("Entity " + mClass.getSimpleName() + " has no service");
		}
	}
}
