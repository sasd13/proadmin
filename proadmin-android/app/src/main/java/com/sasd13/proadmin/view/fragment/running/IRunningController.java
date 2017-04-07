package com.sasd13.proadmin.view.fragment.running;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningController extends IController {

    void actionNewRunning(Project project);

    void actionCreateRunning(Running running);

    void actionShowRunning(Running running);

    void actionRemoveRunning(Running running);
}
