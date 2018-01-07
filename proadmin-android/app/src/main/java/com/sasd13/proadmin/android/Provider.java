package com.sasd13.proadmin.android;

import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ILeadEvaluationService;
import com.sasd13.proadmin.android.service.ILocalStorageService;
import com.sasd13.proadmin.android.service.IProjectService;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.IRunningService;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.service.ISessionStorageService;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ITeacherService;
import com.sasd13.proadmin.android.service.ITeamService;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.service.IUserStorageService;
import com.sasd13.proadmin.android.service.impl.AuthenticationService;
import com.sasd13.proadmin.android.service.impl.IndividualEvaluationService;
import com.sasd13.proadmin.android.service.impl.LeadEvaluationService;
import com.sasd13.proadmin.android.service.impl.LocalStorageService;
import com.sasd13.proadmin.android.service.impl.ProjectService;
import com.sasd13.proadmin.android.service.impl.ReportService;
import com.sasd13.proadmin.android.service.impl.RunningService;
import com.sasd13.proadmin.android.service.impl.RunningTeamService;
import com.sasd13.proadmin.android.service.impl.SessionStorageService;
import com.sasd13.proadmin.android.service.impl.StudentService;
import com.sasd13.proadmin.android.service.impl.StudentTeamService;
import com.sasd13.proadmin.android.service.impl.TeacherService;
import com.sasd13.proadmin.android.service.impl.TeamService;
import com.sasd13.proadmin.android.service.impl.UserService;
import com.sasd13.proadmin.android.service.impl.UserStorageService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class Provider {

    private Resolver resolver;

    public Provider(Resolver resolver) {
        this.resolver = resolver;
    }

    public Object provide(Class mClass) {
        Object service = resolver.resolve(mClass);

        if (service == null) {
            service = make(mClass);

            resolver.register(mClass, service);
        }

        return service;
    }

    private Object make(Class mClass) {
        if (IAuthenticationService.class.isAssignableFrom(mClass)) {
            return new AuthenticationService(
                    (ISessionStorageService) provide(ISessionStorageService.class)
            );
        } else if (IIndividualEvaluationService.class.isAssignableFrom(mClass)) {
            return new IndividualEvaluationService();
        } else if (ILeadEvaluationService.class.isAssignableFrom(mClass)) {
            return new LeadEvaluationService();
        } else if (ILocalStorageService.class.isAssignableFrom(mClass)) {
            return new LocalStorageService();
        } else if (IProjectService.class.isAssignableFrom(mClass)) {
            return new ProjectService();
        } else if (IReportService.class.isAssignableFrom(mClass)) {
            return new ReportService();
        } else if (IRunningService.class.isAssignableFrom(mClass)) {
            return new RunningService();
        } else if (IRunningTeamService.class.isAssignableFrom(mClass)) {
            return new RunningTeamService();
        } else if (ISessionStorageService.class.isAssignableFrom(mClass)) {
            return new SessionStorageService(
                    (SessionStorage) resolver.resolve(SessionStorage.class)
            );
        } else if (IStudentService.class.isAssignableFrom(mClass)) {
            return new StudentService();
        } else if (IStudentTeamService.class.isAssignableFrom(mClass)) {
            return new StudentTeamService();
        } else if (ITeacherService.class.isAssignableFrom(mClass)) {
            return new TeacherService();
        } else if (ITeamService.class.isAssignableFrom(mClass)) {
            return new TeamService();
        } else if (IUserService.class.isAssignableFrom(mClass)) {
            return new UserService();
        } else if (IUserStorageService.class.isAssignableFrom(mClass)) {
            return new UserStorageService(
                    (ILocalStorageService) resolver.resolve(ILocalStorageService.class)
            );
        } else {
            return null;
        }
    }
}
