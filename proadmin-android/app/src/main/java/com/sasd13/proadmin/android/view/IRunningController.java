package com.sasd13.proadmin.android.view;

import com.sasd13.proadmin.android.model.Project;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.model.Teacher;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningController extends IController {

    void actionNewRunning(Project project, Teacher teacher);

    void actionCreateRunning(Running running);

    void actionShowRunning(Running running);

    void actionRemoveRunning(Running running);
}
