package com.sasd13.proadmin.ws.service;

import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.service.impl.AcademicLevelService;
import com.sasd13.proadmin.ws.service.impl.IndividualEvaluationService;
import com.sasd13.proadmin.ws.service.impl.LeadEvaluationService;
import com.sasd13.proadmin.ws.service.impl.ProjectService;
import com.sasd13.proadmin.ws.service.impl.ReportService;
import com.sasd13.proadmin.ws.service.impl.RunningService;
import com.sasd13.proadmin.ws.service.impl.RunningTeamService;
import com.sasd13.proadmin.ws.service.impl.StudentService;
import com.sasd13.proadmin.ws.service.impl.StudentTeamService;
import com.sasd13.proadmin.ws.service.impl.TeacherService;
import com.sasd13.proadmin.ws.service.impl.TeamService;

public class ServiceFactory {

	public static Object make(Class<?> mClass, DAO dao) {
		if (IProjectService.class.isAssignableFrom(mClass)) {
			return new ProjectService(dao);
		} else if (ITeacherService.class.isAssignableFrom(mClass)) {
			return new TeacherService(dao);
		} else if (IStudentService.class.isAssignableFrom(mClass)) {
			return new StudentService(dao);
		} else if (ITeamService.class.isAssignableFrom(mClass)) {
			return new TeamService(dao);
		} else if (IStudentTeamService.class.isAssignableFrom(mClass)) {
			return new StudentTeamService(dao);
		} else if (IRunningService.class.isAssignableFrom(mClass)) {
			return new RunningService(dao);
		} else if (IAcademicLevelService.class.isAssignableFrom(mClass)) {
			return new AcademicLevelService(dao);
		} else if (IRunningTeamService.class.isAssignableFrom(mClass)) {
			return new RunningTeamService(dao);
		} else if (IReportService.class.isAssignableFrom(mClass)) {
			return new ReportService(dao);
		} else if (ILeadEvaluationService.class.isAssignableFrom(mClass)) {
			return new LeadEvaluationService(dao);
		} else if (IIndividualEvaluationService.class.isAssignableFrom(mClass)) {
			return new IndividualEvaluationService(dao);
		} else {
			return null;
		}
	}
}
