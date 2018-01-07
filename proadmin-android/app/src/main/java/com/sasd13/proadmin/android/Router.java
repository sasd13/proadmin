package com.sasd13.proadmin.android;

import android.app.Activity;

import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.activity.SplashScreenActivity;
import com.sasd13.proadmin.android.component.authentication.controller.LogInController;
import com.sasd13.proadmin.android.component.authentication.controller.LogOutController;
import com.sasd13.proadmin.android.component.project.controller.ProjectController;
import com.sasd13.proadmin.android.component.report.controller.ReportController;
import com.sasd13.proadmin.android.component.running.controller.RunningController;
import com.sasd13.proadmin.android.component.runningteam.controller.RunningTeamController;
import com.sasd13.proadmin.android.component.setting.controller.SettingController;
import com.sasd13.proadmin.android.component.splashscreen.controller.SplashScreenController;
import com.sasd13.proadmin.android.component.student.controller.StudentController;
import com.sasd13.proadmin.android.component.team.controller.TeamController;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ILeadEvaluationService;
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
import com.sasd13.proadmin.android.component.IController;
import com.sasd13.proadmin.android.component.authentication.view.ILogInController;
import com.sasd13.proadmin.android.component.authentication.view.ILogOutController;
import com.sasd13.proadmin.android.component.project.view.IProjectController;
import com.sasd13.proadmin.android.component.report.view.IReportController;
import com.sasd13.proadmin.android.component.running.view.IRunningController;
import com.sasd13.proadmin.android.component.runningteam.view.IRunningTeamController;
import com.sasd13.proadmin.android.component.setting.view.ISettingController;
import com.sasd13.proadmin.android.component.splashscreen.view.ISplashScreenController;
import com.sasd13.proadmin.android.component.student.view.IStudentController;
import com.sasd13.proadmin.android.component.team.view.ITeamController;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class Router {

    private Resolver resolver;
    private Provider provider;

    public Router(Resolver resolver, Provider provider) {
        this.resolver = resolver;
        this.provider = provider;
    }

    public IController navigate(Class mClass, Activity activity) {
        IController controller = (IController) resolver.resolve(mClass);

        if (controller == null) {
            controller = make(mClass, activity);

            resolver.register(mClass, controller);
        }

        return controller;
    }

    private IController make(Class<? extends IController> mClass, Activity activity) {
        if (ILogInController.class.equals(mClass)) {
            return new LogInController(
                    (IdentityActivity) activity,
                    (IAuthenticationService) provider.provide(IAuthenticationService.class),
                    (IUserStorageService) provider.provide(IUserStorageService.class)
            );
        } else if (ILogOutController.class.equals(mClass)) {
            return new LogOutController(
                    (MainActivity) activity,
                    (IAuthenticationService) provider.provide(IAuthenticationService.class),
                    (IUserStorageService) provider.provide(IUserStorageService.class)
            );
        } else if (IProjectController.class.equals(mClass)) {
            return new ProjectController(
                    (MainActivity) activity,
                    (ISessionStorageService) provider.provide(ISessionStorageService.class),
                    (IUserStorageService) provider.provide(IUserStorageService.class),
                    (IProjectService) provider.provide(IProjectService.class),
                    (IRunningService) provider.provide(IRunningService.class),
                    (ITeacherService) provider.provide(ITeacherService.class)
            );
        } else if (IReportController.class.equals(mClass)) {
            return new ReportController(
                    (MainActivity) activity,
                    (ISessionStorageService) provider.provide(ISessionStorageService.class),
                    (IUserStorageService) provider.provide(IUserStorageService.class),
                    (IReportService) provider.provide(IReportService.class),
                    (ILeadEvaluationService) provider.provide(ILeadEvaluationService.class),
                    (IIndividualEvaluationService) provider.provide(IIndividualEvaluationService.class),
                    (IRunningTeamService) provider.provide(IRunningTeamService.class)
            );
        } else if (IRunningController.class.equals(mClass)) {
            return new RunningController(
                    (MainActivity) activity,
                    (IRunningService) provider.provide(IRunningService.class)
            );
        } else if (IRunningTeamController.class.equals(mClass)) {
            return new RunningTeamController(
                    (MainActivity) activity,
                    (ISessionStorageService) provider.provide(ISessionStorageService.class),
                    (IRunningTeamService) provider.provide(IRunningTeamService.class),
                    (IReportService) provider.provide(IReportService.class)
            );
        } else if (ISettingController.class.equals(mClass)) {
            return new SettingController(
                    (MainActivity) activity,
                    (ISessionStorageService) provider.provide(ISessionStorageService.class),
                    (IUserStorageService) provider.provide(IUserStorageService.class),
                    (IUserService) provider.provide(IUserService.class)
            );
        } else if (ISplashScreenController.class.equals(mClass)) {
            return new SplashScreenController(
                    (SplashScreenActivity) activity,
                    (ISessionStorageService) provider.provide(ISessionStorageService.class),
                    (IAuthenticationService) provider.provide(IAuthenticationService.class),
                    (IUserService) provider.provide(IUserService.class)
            );
        } else if (IStudentController.class.equals(mClass)) {
            return new StudentController(
                    (MainActivity) activity,
                    (IStudentService) provider.provide(IStudentService.class),
                    (IStudentTeamService) provider.provide(IStudentTeamService.class)
            );
        } else if (ITeamController.class.equals(mClass)) {
            return new TeamController(
                    (MainActivity) activity,
                    (ITeamService) provider.provide(ITeamService.class),
                    (IStudentTeamService) provider.provide(IStudentTeamService.class)
            );
        } else {
            return null;
        }
    }
}
