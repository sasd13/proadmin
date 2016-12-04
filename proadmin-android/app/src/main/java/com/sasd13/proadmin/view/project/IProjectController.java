package com.sasd13.proadmin.view.project;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IProjectController extends IController {

    void listProjects();

    void showProject(Project project);

    void newRunning(Project project);

    void showRunning(Running running);
}
