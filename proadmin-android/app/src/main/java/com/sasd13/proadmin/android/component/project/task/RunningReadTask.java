package com.sasd13.proadmin.android.component.project.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.project.controller.ProjectController;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.service.IRunningService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningReadTask extends RequestorTask {

    private ProjectController controller;
    private IRunningService service;

    public RunningReadTask(ProjectController controller, IRunningService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.read((Map<String, Object>) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<List<Running>> result = (ServiceResult<List<Running>>) out;

        if (result.isSuccess()) {
            controller.onReadRunnings(result.getData());
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
