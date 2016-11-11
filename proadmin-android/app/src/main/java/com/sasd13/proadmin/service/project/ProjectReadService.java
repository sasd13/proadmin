package com.sasd13.proadmin.service.project;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.wrapper.read.IReadWrapper;
import com.sasd13.proadmin.wrapper.read.project.ProjectReadWrapper;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectReadService implements IHttpCallback {

    private IReadServiceCaller<IReadWrapper<Project>> serviceCaller;
    private ReadTask<Project> readTask;

    public ProjectReadService(IReadServiceCaller<IReadWrapper<Project>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void readProjects() {
        readTask = new ReadTask<>(WSResources.URL_WS_PROJECTS, this, Project.class);

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
                serviceCaller.onReadSucceeded(new ProjectReadWrapper(readTask.getResults()));
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
