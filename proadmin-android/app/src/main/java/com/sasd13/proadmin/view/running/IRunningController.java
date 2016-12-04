package com.sasd13.proadmin.view.running;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningController extends IController {

    void createRunning(Running running);

    void deleteRunning(Running running);
}
