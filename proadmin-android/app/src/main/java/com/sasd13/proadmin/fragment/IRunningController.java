package com.sasd13.proadmin.fragment;

import com.sasd13.proadmin.bean.running.Running;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningController extends IController {

    void createRunning(Running running);

    void deleteRunning(Running running);
}
