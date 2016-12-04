package com.sasd13.proadmin.controller.project;

import android.content.Context;

import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class ProjectServiceCaller implements ReadService.Caller<Project> {

    private ProjectController controller;
    private Context context;

    public ProjectServiceCaller(ProjectController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<Project> projects) {
        controller.onReadProjects(projects);
    }

    @Override
    public void onErrors(List<String> errors) {
        controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
