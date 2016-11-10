package com.sasd13.proadmin.service.project;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.wrapper.IProjectReadWrapper;
import com.sasd13.proadmin.wrapper.impl.ProjectReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectReadService implements IHttpCallback {

    private IReadServiceCaller<IProjectReadWrapper> serviceCaller;
    private ReadTask<Project> readTask;

    public ProjectReadService(IReadServiceCaller<IProjectReadWrapper> serviceCaller) {
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
