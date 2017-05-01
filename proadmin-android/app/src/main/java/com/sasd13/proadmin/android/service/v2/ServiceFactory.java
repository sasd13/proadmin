package com.sasd13.proadmin.android.service.v2;

import com.sasd13.proadmin.android.service.v2.impl.AuthenticationService;
import com.sasd13.proadmin.android.service.v2.impl.IndividualEvaluationService;
import com.sasd13.proadmin.android.service.v2.impl.LeadEvaluationService;
import com.sasd13.proadmin.android.service.v2.impl.ProjectService;
import com.sasd13.proadmin.android.service.v2.impl.ReportService;
import com.sasd13.proadmin.android.service.v2.impl.RunningService;
import com.sasd13.proadmin.android.service.v2.impl.RunningTeamService;
import com.sasd13.proadmin.android.service.v2.impl.StudentService;
import com.sasd13.proadmin.android.service.v2.impl.StudentTeamService;
import com.sasd13.proadmin.android.service.v2.impl.TeacherService;
import com.sasd13.proadmin.android.service.v2.impl.TeamService;
import com.sasd13.proadmin.android.service.v2.impl.UserService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceFactory {

    public static Object make(Class mClass) {
        if (IAuthenticationService.class.isAssignableFrom(mClass)) {
            return new AuthenticationService();
        } else if (IUserService.class.isAssignableFrom(mClass)) {
            return new UserService();
        } else if (IProjectService.class.isAssignableFrom(mClass)) {
            return new ProjectService();
        } else if (ITeacherService.class.isAssignableFrom(mClass)) {
            return new TeacherService();
        } else if (IStudentService.class.isAssignableFrom(mClass)) {
            return new StudentService();
        } else if (IStudentTeamService.class.isAssignableFrom(mClass)) {
            return new StudentTeamService();
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
