package com.sasd13.proadmin.controller.project;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.service.IProjectService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ProjectReadStrategy extends ReadRequestorStrategy {

    private ProjectController controller;
    private IProjectService service;

    public ProjectReadStrategy(ProjectController controller, IProjectService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object in) {
        return service.readAll();
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onReadProjects(((ServiceResult<List<Project>>) out).getResult());
        } else {
            controller.display(EnumErrorRes.find(((ServiceResult) out).getHttpStatus()).getStringRes());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
