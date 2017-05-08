package com.sasd13.proadmin.android.provider;

import android.app.Activity;

import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.activity.SplashScreenActivity;
import com.sasd13.proadmin.android.controller.authentication.LogInController;
import com.sasd13.proadmin.android.controller.authentication.LogOutController;
import com.sasd13.proadmin.android.controller.project.ProjectController;
import com.sasd13.proadmin.android.controller.report.ReportController;
import com.sasd13.proadmin.android.controller.running.RunningController;
import com.sasd13.proadmin.android.controller.runningteam.RunningTeamController;
import com.sasd13.proadmin.android.controller.setting.SettingController;
import com.sasd13.proadmin.android.controller.splashscreen.SplashScreenController;
import com.sasd13.proadmin.android.controller.student.StudentController;
import com.sasd13.proadmin.android.controller.team.TeamController;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ILeadEvaluationService;
import com.sasd13.proadmin.android.service.IProjectService;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.IRunningService;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ITeacherService;
import com.sasd13.proadmin.android.service.ITeamService;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.view.IController;
import com.sasd13.proadmin.android.view.ILogInController;
import com.sasd13.proadmin.android.view.ILogOutController;
import com.sasd13.proadmin.android.view.IProjectController;
import com.sasd13.proadmin.android.view.IReportController;
import com.sasd13.proadmin.android.view.IRunningController;
import com.sasd13.proadmin.android.view.IRunningTeamController;
import com.sasd13.proadmin.android.view.ISettingController;
import com.sasd13.proadmin.android.view.ISplashScreenController;
import com.sasd13.proadmin.android.view.IStudentController;
import com.sasd13.proadmin.android.view.ITeamController;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ControllerFactory {

    public static IController make(Class<? extends IController> mClass, Activity activity) {
        if (ISplashScreenController.class.equals(mClass)) {
            return new SplashScreenController(
                    (SplashScreenActivity) activity,
                    (IUserService) ServiceProvider.provide(IUserService.class)
            );
        } else if (ILogInController.class.equals(mClass)) {
            return new LogInController(
                    (IdentityActivity) activity,
                    (IAuthenticationService) ServiceProvider.provide(IAuthenticationService.class)
            );
        } else if (ISettingController.class.equals(mClass)) {
            return new SettingController(
                    (MainActivity) activity,
                    (IUserService) ServiceProvider.provide(IUserService.class)
            );
        } else if (IProjectController.class.equals(mClass)) {
            return new ProjectController(
                    (MainActivity) activity,
                    (IProjectService) ServiceProvider.provide(IProjectService.class),
                    (IRunningService) ServiceProvider.provide(IRunningService.class),
                    (ITeacherService) ServiceProvider.provide(ITeacherService.class)
            );
        } else if (ITeamController.class.equals(mClass)) {
            return new TeamController(
                    (MainActivity) activity,
                    (ITeamService) ServiceProvider.provide(ITeamService.class),
                    (IStudentTeamService) ServiceProvider.provide(IStudentTeamService.class)
            );
        } else if (IStudentController.class.equals(mClass)) {
            return new StudentController(
                    (MainActivity) activity,
                    (IStudentService) ServiceProvider.provide(IStudentService.class),
                    (IStudentTeamService) ServiceProvider.provide(IStudentTeamService.class)
            );
        } else if (IRunningController.class.equals(mClass)) {
            return new RunningController(
                    (MainActivity) activity,
                    (IRunningService) ServiceProvider.provide(IRunningService.class)
            );
        } else if (IRunningTeamController.class.equals(mClass)) {
            return new RunningTeamController(
                    (MainActivity) activity,
                    (IRunningTeamService) ServiceProvider.provide(IRunningTeamService.class),
                    (IReportService) ServiceProvider.provide(IReportService.class)
            );
        } else if (IReportController.class.equals(mClass)) {
            return new ReportController(
                    (MainActivity) activity,
                    (IReportService) ServiceProvider.provide(IReportService.class),
                    (ILeadEvaluationService) ServiceProvider.provide(ILeadEvaluationService.class),
                    (IIndividualEvaluationService) ServiceProvider.provide(IIndividualEvaluationService.class),
                    (IRunningTeamService) ServiceProvider.provide(IRunningTeamService.class)
            );
        } else if (ILogOutController.class.equals(mClass)) {
            return new LogOutController((MainActivity) activity);
        } else {
            return null;
        }
    }
}