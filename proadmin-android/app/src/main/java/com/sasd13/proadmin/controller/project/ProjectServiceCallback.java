package com.sasd13.proadmin.controller.project;

import android.content.Context;

import com.sasd13.androidex.ws.rest.promise.ReadPromise;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class ProjectServiceCallback implements ReadPromise.Callback<Project> {

    private ProjectController controller;
    private Context context;

    public ProjectServiceCallback(ProjectController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onPreExecute() {
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
