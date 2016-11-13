package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.util.wrapper.read.AcademicLevelReadWrapper;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class AcademicLevelReadService implements IHttpCallback {

    private IReadServiceCaller<IReadWrapper<AcademicLevel>> serviceCaller;
    private ReadTask<AcademicLevel> readTask;

    public AcademicLevelReadService(IReadServiceCaller<IReadWrapper<AcademicLevel>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void readAcademicLevels() {
        readTask = new ReadTask<>(WSResources.URL_WS_ACADEMICLEVELS, this, AcademicLevel.class);

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
                serviceCaller.onReadSucceeded(new AcademicLevelReadWrapper(readTask.getResults()));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
