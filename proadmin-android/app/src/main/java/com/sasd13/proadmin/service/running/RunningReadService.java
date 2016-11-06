package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

public class RunningReadService implements IHttpCallback {

    private IReadServiceCaller<List<Running>> serviceCaller;
    private ReadTask<Running> readTask;

    public RunningReadService(IReadServiceCaller<List<Running>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void readRunnings(String projectCode, String teacherNumber) {
        readTask = new ReadTask<>(WSResources.URL_WS_RUNNINGS, this, Running.class);

        if (projectCode != null) {
            readTask.putParameter(EnumParameter.PROJECT.getName(), new String[]{projectCode});
        }

        readTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readTask.execute();
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        if (!readTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, readTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onReadSucceeded(readTask.getResults());
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    @Override
    public void onFail(int i) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}