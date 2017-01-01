package com.sasd13.proadmin.ws2.service;

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
import com.sasd13.proadmin.ws2.service.impl.AcademicLevelService;
import com.sasd13.proadmin.ws2.service.impl.IndividualEvaluationService;
import com.sasd13.proadmin.ws2.service.impl.LeadEvaluationService;
import com.sasd13.proadmin.ws2.service.impl.ProjectService;
import com.sasd13.proadmin.ws2.service.impl.ReportService;
import com.sasd13.proadmin.ws2.service.impl.RunningService;
import com.sasd13.proadmin.ws2.service.impl.RunningTeamService;
import com.sasd13.proadmin.ws2.service.impl.StudentService;
import com.sasd13.proadmin.ws2.service.impl.StudentTeamService;
import com.sasd13.proadmin.ws2.service.impl.TeacherService;
import com.sasd13.proadmin.ws2.service.impl.TeamService;

public class ServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> IService<T> make(Class<T> mClass) {
		if (Project.class.isAssignableFrom(mClass)) {
			return (IService<T>) new ProjectService();
		} else if (Teacher.class.isAssignableFrom(mClass)) {
			return (IService<T>) new TeacherService();
		} else if (Student.class.isAssignableFrom(mClass)) {
			return (IService<T>) new StudentService();
		} else if (Team.class.isAssignableFrom(mClass)) {
			return (IService<T>) new TeamService();
		} else if (StudentTeam.class.isAssignableFrom(mClass)) {
			return (IService<T>) new StudentTeamService();
		} else if (Running.class.isAssignableFrom(mClass)) {
			return (IService<T>) new RunningService();
		} else if (AcademicLevel.class.isAssignableFrom(mClass)) {
			return (IService<T>) new AcademicLevelService();
		} else if (RunningTeam.class.isAssignableFrom(mClass)) {
			return (IService<T>) new RunningTeamService();
		} else if (Report.class.isAssignableFrom(mClass)) {
			return (IService<T>) new ReportService();
		} else if (LeadEvaluation.class.isAssignableFrom(mClass)) {
			return (IService<T>) new LeadEvaluationService();
		} else if (IndividualEvaluation.class.isAssignableFrom(mClass)) {
			return (IService<T>) new IndividualEvaluationService();
		} else {
			throw new ServiceException("Entity " + mClass.getSimpleName() + " has no service");
		}
	}
}
