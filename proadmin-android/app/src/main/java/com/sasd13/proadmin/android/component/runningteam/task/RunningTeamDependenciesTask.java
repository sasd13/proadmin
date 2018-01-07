package com.sasd13.proadmin.android.component.runningteam.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.runningteam.controller.RunningTeamController;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamDependenciesTask extends RequestorTask {

    private RunningTeamController controller;
    private IRunningTeamService service;

    public RunningTeamDependenciesTask(RunningTeamController controller, IRunningTeamService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        ServiceResult<Map<String, Object>> out = service.retrieve((Map<String, Map<String, Object>>) in);

        return out;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<Map<String, Object>> result = (ServiceResult<Map<String, Object>>) out;

        if (result.isSuccess()) {
            controller.onRetrieved(result.getData());
        } else {
            controller.onFail(result.getHttpStatus(), result.getErrors());
        }
    }
}
