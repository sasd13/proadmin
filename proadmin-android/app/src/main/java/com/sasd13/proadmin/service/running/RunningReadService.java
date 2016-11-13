package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;
import com.sasd13.proadmin.util.wrapper.read.running.RunningReadWrapper;

public class RunningReadService implements IHttpCallback {

    private IReadServiceCaller<IReadWrapper<Running>> serviceCaller;
    private ReadTask<Running> readTask;

    public RunningReadService(IReadServiceCaller<IReadWrapper<Running>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void read(String teacherNumber) {
        read(teacherNumber, null);
    }

    public void read(String teacherNumber, Project project) {
        readTask = new ReadTask<>(WSResources.URL_WS_RUNNINGS, this, Running.class);

        if (project != null) {
            readTask.putParameter(EnumParameter.PROJECT.getName(), new String[]{project.getCode()});
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
                serviceCaller.onReadSucceeded(new RunningReadWrapper(readTask.getResults()));
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