package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningController extends IController {

    void listRunnings(Project project);

    void newRunning(Project project);

    void showRunning(Running running);

    void createRunning(Running running);

    void deleteRunnings(Running[] runnings);
}
