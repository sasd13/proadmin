package com.sasd13.proadmin.android.controller.project;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.service.IProjectService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ProjectReadTask extends ReadRequestorTask {

    private ProjectController controller;
    private IProjectService service;

    public ProjectReadTask(ProjectController controller, IProjectService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.readAll();
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<List<Project>> result = (ServiceResult<List<Project>>) out;

        if (result.isSuccess()) {
            controller.onReadProjects(result.getData());
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
