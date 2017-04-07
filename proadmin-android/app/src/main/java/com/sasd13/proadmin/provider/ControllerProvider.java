package com.sasd13.proadmin.provider;

import android.app.Activity;

import com.sasd13.proadmin.activity.IdentityActivity;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.controller.IController;
import com.sasd13.proadmin.controller.ILogInController;
import com.sasd13.proadmin.controller.ILogOutController;
import com.sasd13.proadmin.controller.IProjectController;
import com.sasd13.proadmin.controller.IReportController;
import com.sasd13.proadmin.controller.IRunningController;
import com.sasd13.proadmin.controller.IRunningTeamController;
import com.sasd13.proadmin.controller.ISettingController;
import com.sasd13.proadmin.controller.IStudentController;
import com.sasd13.proadmin.controller.ITeamController;
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

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ControllerProvider {

    public static IController provide(Class<? extends IController> mClass, Activity activity) {
        if (ILogInController.class.isAssignableFrom(mClass)) {
            return new LogInController(
                    (IdentityActivity) activity,
                    (IAuthenticationService) ServiceProvider.provide(IAuthenticationService.class),
                    (ITeacherService) ServiceProvider.provide(ITeacherService.class)
            );
        } else if (ISettingController.class.isAssignableFrom(mClass)) {
            return new SettingController(
                    (MainActivity) activity,
                    (ITeacherService) ServiceProvider.provide(ITeacherService.class)
            );
        } else if (IProjectController.class.isAssignableFrom(mClass)) {
            return new ProjectController(
                    (MainActivity) activity,
                    (IProjectService) ServiceProvider.provide(IProjectService.class),
                    (IRunningService) ServiceProvider.provide(IRunningService.class)
            );
        } else if (ITeamController.class.isAssignableFrom(mClass) || IStudentController.class.isAssignableFrom(mClass)) {
            return new TeamController(
                    (MainActivity) activity,
                    (ITeamService) ServiceProvider.provide(ITeamService.class),
                    (IStudentService) ServiceProvider.provide(IStudentService.class)
            );
        } else if (IStudentController.class.isAssignableFrom(mClass)) {
            return new StudentController(
                    (MainActivity) activity,
                    (IStudentService) ServiceProvider.provide(IStudentService.class)
            );
        } else if (IRunningController.class.isAssignableFrom(mClass)) {
            return new RunningController(
                    (MainActivity) activity,
                    (IRunningService) ServiceProvider.provide(IRunningService.class)
            );
        } else if (IRunningTeamController.class.isAssignableFrom(mClass)) {
            return new RunningTeamController(
                    (MainActivity) activity,
                    (IRunningTeamService) ServiceProvider.provide(IRunningTeamService.class),
                    (IReportService) ServiceProvider.provide(IReportService.class)
            );
        } else if (IReportController.class.isAssignableFrom(mClass)) {
            return new ReportController(
                    (MainActivity) activity,
                    (IReportService) ServiceProvider.provide(IReportService.class),
                    (ILeadEvaluationService) ServiceProvider.provide(ILeadEvaluationService.class),
                    (IIndividualEvaluationService) ServiceProvider.provide(IIndividualEvaluationService.class),
                    (IRunningTeamService) ServiceProvider.provide(IRunningTeamService.class)
            );
        } else if (ILogOutController.class.isAssignableFrom(mClass)) {
            return new LogOutController((MainActivity) activity);
        } else {
            return null;
        }
    }
}
