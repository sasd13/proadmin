package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.service.IReadServiceCaller;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.ws.WSConstants;
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
            readTask.putParameter(EnumParameter.PROJECT.getName(), new String[]{ projectCode });
        }

        readTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{ teacherNumber });
        readTask.setTimeout(WSConstants.DEFAULT_TIMEOUT);
        readTask.execute();
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        if (!readTask.getResponseErrors().isEmpty()) {
            handleErrors(readTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onReadSucceeded(readTask.getResults());
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    private void handleErrors(List<String> errors) {
        EnumError error = EnumError.find(Integer.parseInt(errors.get(0)));

        serviceCaller.onError(EnumErrorRes.find(error).getStringRes());
    }

    @Override
    public void onFail(int i) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}