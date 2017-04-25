package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.service.impl.AuthenticationService;
import com.sasd13.proadmin.android.service.impl.IndividualEvaluationService;
import com.sasd13.proadmin.android.service.impl.LeadEvaluationService;
import com.sasd13.proadmin.android.service.impl.ProjectService;
import com.sasd13.proadmin.android.service.impl.ReportService;
import com.sasd13.proadmin.android.service.impl.RunningService;
import com.sasd13.proadmin.android.service.impl.RunningTeamService;
import com.sasd13.proadmin.android.service.impl.StudentService;
import com.sasd13.proadmin.android.service.impl.TeacherService;
import com.sasd13.proadmin.android.service.impl.TeamService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceFactory {

    public static Object make(Class mClass) {
        if (IAuthenticationService.class.isAssignableFrom(mClass)) {
            return new AuthenticationService();
        } else if (ITeacherService.class.isAssignableFrom(mClass)) {
            return new TeacherService();
        } else if (IProjectService.class.isAssignableFrom(mClass)) {
            return new ProjectService();
        } else if (IStudentService.class.isAssignableFrom(mClass)) {
            return new StudentService();
        } else if (ITeamService.class.isAssignableFrom(mClass)) {
            return new TeamService();
        } else if (IRunningService.class.isAssignableFrom(mClass)) {
            return new RunningService();
        } else if (IRunningTeamService.class.isAssignableFrom(mClass)) {
            return new RunningTeamService();
        } else if (IReportService.class.isAssignableFrom(mClass)) {
            return new ReportService();
        } else if (ILeadEvaluationService.class.isAssignableFrom(mClass)) {
            return new LeadEvaluationService();
        } else if (IIndividualEvaluationService.class.isAssignableFrom(mClass)) {
            return new IndividualEvaluationService();
        } else {
            return null;
        }
    }
}
