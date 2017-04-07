package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningController extends IController {

    void createRunning(Project project);

    void actionCreateRunning(Running running);

    void showRunning(Running running);

    void actionDeleteRunnings(Running[] runnings);
}
