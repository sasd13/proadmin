package com.sasd13.proadmin.android.component.runningteam.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.runningteam.controller.RunningTeamController;
import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamUpdateTask extends RequestorTask {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamUpdateTask(RunningTeamController controller, IRunningTeamService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.update((RunningTeam) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onUpdateRunningTeam();
        } else {
            controller.onFail(result.getHttpStatus(), result.getErrors());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
