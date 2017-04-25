package com.sasd13.proadmin.android.controller.running;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.service.IRunningService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningDeleteTask extends RequestorTask {

    private RunningController controller;
    private IRunningService service;

    public RunningDeleteTask(RunningController controller, IRunningService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.delete((List<Running>) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onDeleteRunnings();
        } else {
            controller.onFail(result.getHttpStatus(), result.getHeaders().get(EnumHttpHeader.RESPONSE_ERROR.getName()));
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
