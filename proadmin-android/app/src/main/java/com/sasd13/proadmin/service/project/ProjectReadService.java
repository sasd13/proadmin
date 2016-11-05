package com.sasd13.proadmin.service.project;

import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.service.IReadServiceCaller;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.ws.WSConstants;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectReadService implements IHttpCallback {

    private IReadServiceCaller<List<Project>> serviceCaller;
    private ReadTask<Project> readTask;

    public ProjectReadService(IReadServiceCaller<List<Project>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void readProjects() {
        readTask = new ReadTask<>(WSResources.URL_WS_PROJECTS, this, Project.class);

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
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
