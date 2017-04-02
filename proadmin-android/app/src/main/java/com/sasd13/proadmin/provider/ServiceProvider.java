package com.sasd13.proadmin.provider;

import com.sasd13.proadmin.service.IAuthenticationService;
import com.sasd13.proadmin.service.IIndividualEvaluationService;
import com.sasd13.proadmin.service.ILeadEvaluationService;
import com.sasd13.proadmin.service.IProjectService;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.service.impl.AuthenticationService;
import com.sasd13.proadmin.service.impl.IndividualEvaluationService;
import com.sasd13.proadmin.service.impl.LeadEvaluationService;
import com.sasd13.proadmin.service.impl.ProjectService;
import com.sasd13.proadmin.service.impl.ReportService;
import com.sasd13.proadmin.service.impl.RunningService;
import com.sasd13.proadmin.service.impl.RunningTeamService;
import com.sasd13.proadmin.service.impl.StudentService;
import com.sasd13.proadmin.service.impl.TeacherService;
import com.sasd13.proadmin.service.impl.TeamService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceProvider {

    private static Map<Class, Object> provider = new HashMap<>();

    public static Object provide(Class mClass) {
        Object service = provider.get(mClass);

        if (service == null) {
            service = make(mClass);
            provider.put(mClass, service);
        }

        return service;
    }

    private static Object make(Class mClass) {
        if (IAuthenticationService.class.equals(mClass)) {
            return new AuthenticationService();
        } else if (ITeacherService.class.equals(mClass)) {
            return new TeacherService();
        } else if (IProjectService.class.equals(mClass)) {
            return new ProjectService();
        } else if (IStudentService.class.equals(mClass)) {
            return new StudentService();
        } else if (ITeamService.class.equals(mClass)) {
            return new TeamService();
        } else if (IRunningService.class.equals(mClass)) {
            return new RunningService();
        } else if (IRunningTeamService.class.equals(mClass)) {
            return new RunningTeamService();
        } else if (IReportService.class.equals(mClass)) {
            return new ReportService();
        } else if (ILeadEvaluationService.class.equals(mClass)) {
            return new LeadEvaluationService();
        } else if (IIndividualEvaluationService.class.equals(mClass)) {
            return new IndividualEvaluationService();
        } else {
            return null;
        }
    }
}
