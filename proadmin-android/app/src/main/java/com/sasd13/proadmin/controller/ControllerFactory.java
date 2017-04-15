package com.sasd13.proadmin.controller;

import android.app.Activity;

import com.sasd13.proadmin.activity.IdentityActivity;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.controller.authentication.LogInController;
import com.sasd13.proadmin.controller.authentication.LogOutController;
import com.sasd13.proadmin.controller.project.ProjectController;
import com.sasd13.proadmin.controller.report.ReportController;
import com.sasd13.proadmin.controller.running.RunningController;
import com.sasd13.proadmin.controller.runningteam.RunningTeamController;
import com.sasd13.proadmin.controller.setting.SettingController;
import com.sasd13.proadmin.controller.student.StudentController;
import com.sasd13.proadmin.controller.team.TeamController;
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
import com.sasd13.proadmin.service.ServiceFactory;
import com.sasd13.proadmin.view.IController;
import com.sasd13.proadmin.view.ILogInController;
import com.sasd13.proadmin.view.ILogOutController;
import com.sasd13.proadmin.view.IProjectController;
import com.sasd13.proadmin.view.IReportController;
import com.sasd13.proadmin.view.IRunningController;
import com.sasd13.proadmin.view.IRunningTeamController;
import com.sasd13.proadmin.view.ISettingController;
import com.sasd13.proadmin.view.IStudentController;
import com.sasd13.proadmin.view.ITeamController;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ControllerFactory {

    public static IController make(Class<? extends IController> mClass, Activity activity) {
        if (ILogInController.class.equals(mClass)) {
            return new LogInController(
                    (IdentityActivity) activity,
                    (IAuthenticationService) ServiceFactory.provide(IAuthenticationService.class),
                    (ITeacherService) ServiceFactory.provide(ITeacherService.class)
            );
        } else if (ISettingController.class.equals(mClass)) {
            return new SettingController(
                    (MainActivity) activity,
                    (ITeacherService) ServiceFactory.provide(ITeacherService.class)
            );
        } else if (IProjectController.class.equals(mClass)) {
            return new ProjectController(
                    (MainActivity) activity,
                    (IProjectService) ServiceFactory.provide(IProjectService.class),
                    (IRunningService) ServiceFactory.provide(IRunningService.class)
            );
        } else if (ITeamController.class.equals(mClass)) {
            return new TeamController(
                    (MainActivity) activity,
                    (ITeamService) ServiceFactory.provide(ITeamService.class),
                    (IStudentService) ServiceFactory.provide(IStudentService.class)
            );
        } else if (IStudentController.class.equals(mClass)) {
            return new StudentController(
                    (MainActivity) activity,
                    (IStudentService) ServiceFactory.provide(IStudentService.class)
            );
        } else if (IRunningController.class.equals(mClass)) {
            return new RunningController(
                    (MainActivity) activity,
                    (IRunningService) ServiceFactory.provide(IRunningService.class)
            );
        } else if (IRunningTeamController.class.equals(mClass)) {
            return new RunningTeamController(
                    (MainActivity) activity,
                    (IRunningTeamService) ServiceFactory.provide(IRunningTeamService.class),
                    (IReportService) ServiceFactory.provide(IReportService.class)
            );
        } else if (IReportController.class.equals(mClass)) {
            return new ReportController(
                    (MainActivity) activity,
                    (IReportService) ServiceFactory.provide(IReportService.class),
                    (ILeadEvaluationService) ServiceFactory.provide(ILeadEvaluationService.class),
                    (IIndividualEvaluationService) ServiceFactory.provide(IIndividualEvaluationService.class),
                    (IRunningTeamService) ServiceFactory.provide(IRunningTeamService.class)
            );
        } else if (ILogOutController.class.equals(mClass)) {
            return new LogOutController((MainActivity) activity);
        } else {
            return null;
        }
    }
}
