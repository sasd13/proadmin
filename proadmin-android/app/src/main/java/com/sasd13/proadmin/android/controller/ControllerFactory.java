package com.sasd13.proadmin.android.controller;

import android.app.Activity;

import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.controller.authentication.LogInController;
import com.sasd13.proadmin.android.controller.authentication.LogOutController;
import com.sasd13.proadmin.android.controller.project.ProjectController;
import com.sasd13.proadmin.android.controller.report.ReportController;
import com.sasd13.proadmin.android.controller.running.RunningController;
import com.sasd13.proadmin.android.controller.runningteam.RunningTeamController;
import com.sasd13.proadmin.android.controller.setting.SettingController;
import com.sasd13.proadmin.android.controller.student.StudentController;
import com.sasd13.proadmin.android.controller.team.TeamController;
import com.sasd13.proadmin.android.service.v1.IAuthenticationService;
import com.sasd13.proadmin.android.service.v1.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.v1.ILeadEvaluationService;
import com.sasd13.proadmin.android.service.v1.IProjectService;
import com.sasd13.proadmin.android.service.v1.IReportService;
import com.sasd13.proadmin.android.service.v1.IRunningService;
import com.sasd13.proadmin.android.service.v1.IRunningTeamService;
import com.sasd13.proadmin.android.service.v1.IStudentService;
import com.sasd13.proadmin.android.service.v1.IStudentTeamService;
import com.sasd13.proadmin.android.service.v1.ITeamService;
import com.sasd13.proadmin.android.service.v1.IUserService;
import com.sasd13.proadmin.android.service.v1.ServiceFactory;
import com.sasd13.proadmin.android.view.IController;
import com.sasd13.proadmin.android.view.ILogInController;
import com.sasd13.proadmin.android.view.ILogOutController;
import com.sasd13.proadmin.android.view.IProjectController;
import com.sasd13.proadmin.android.view.IReportController;
import com.sasd13.proadmin.android.view.IRunningController;
import com.sasd13.proadmin.android.view.IRunningTeamController;
import com.sasd13.proadmin.android.view.ISettingController;
import com.sasd13.proadmin.android.view.IStudentController;
import com.sasd13.proadmin.android.view.ITeamController;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ControllerFactory {

    public static IController make(Class<? extends IController> mClass, Activity activity) {
        if (ILogInController.class.equals(mClass)) {
            return new LogInController(
                    (IdentityActivity) activity,
                    (IAuthenticationService) ServiceFactory.make(IAuthenticationService.class)
            );
        } else if (ISettingController.class.equals(mClass)) {
            return new SettingController(
                    (MainActivity) activity,
                    (IUserService) ServiceFactory.make(IUserService.class)
            );
        } else if (IProjectController.class.equals(mClass)) {
            return new ProjectController(
                    (MainActivity) activity,
                    (IProjectService) ServiceFactory.make(IProjectService.class),
                    (IRunningService) ServiceFactory.make(IRunningService.class)
            );
        } else if (ITeamController.class.equals(mClass)) {
            return new TeamController(
                    (MainActivity) activity,
                    (ITeamService) ServiceFactory.make(ITeamService.class),
                    (IStudentTeamService) ServiceFactory.make(IStudentTeamService.class)
            );
        } else if (IStudentController.class.equals(mClass)) {
            return new StudentController(
                    (MainActivity) activity,
                    (IStudentService) ServiceFactory.make(IStudentService.class),
                    (IStudentTeamService) ServiceFactory.make(IStudentTeamService.class)
            );
        } else if (IRunningController.class.equals(mClass)) {
            return new RunningController(
                    (MainActivity) activity,
                    (IRunningService) ServiceFactory.make(IRunningService.class)
            );
        } else if (IRunningTeamController.class.equals(mClass)) {
            return new RunningTeamController(
                    (MainActivity) activity,
                    (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class),
                    (IReportService) ServiceFactory.make(IReportService.class)
            );
        } else if (IReportController.class.equals(mClass)) {
            return new ReportController(
                    (MainActivity) activity,
                    (IReportService) ServiceFactory.make(IReportService.class),
                    (ILeadEvaluationService) ServiceFactory.make(ILeadEvaluationService.class),
                    (IIndividualEvaluationService) ServiceFactory.make(IIndividualEvaluationService.class),
                    (IRunningTeamService) ServiceFactory.make(IRunningTeamService.class)
            );
        } else if (ILogOutController.class.equals(mClass)) {
            return new LogOutController((MainActivity) activity);
        } else {
            return null;
        }
    }
}
