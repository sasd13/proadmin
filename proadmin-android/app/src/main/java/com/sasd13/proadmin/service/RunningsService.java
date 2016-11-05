package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSConstants;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

public class RunningsService implements IHttpCallback {

    private IReadServiceCaller<Running> readServiceCaller;
    private ReadTask<Running> readTask;

    public RunningsService(IReadServiceCaller<Running> readServiceCaller) {
        this.readServiceCaller = readServiceCaller;
    }

    public void readRunnings(String projectCode, String teacherNumber) {
        readTask = new ReadTask<>(WSResources.URL_WS_RUNNINGS, this, Running.class);

        readTask.putParameter(EnumParameter.PROJECT.getName(), new String[]{ projectCode });
        readTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{ teacherNumber });
        readTask.setTimeout(WSConstants.DEFAULT_TIMEOUT);
        readTask.execute();
    }

    @Override
    public void onLoad() {
        readServiceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        try {
            readServiceCaller.onReadSucceeded(readTask.getResults());
        } catch (IndexOutOfBoundsException e) {
            readServiceCaller.onError(R.string.error_ws_retrieve_data);
        }
    }

    @Override
    public void onFail(int i) {
        readServiceCaller.onError(R.string.error_ws_server_connection);
    }
}