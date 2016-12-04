package com.sasd13.proadmin.controller.caller.project;

import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.controller.ProjectsActivity;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class ProjectsServiceCaller implements ReadService.Caller<Project> {

    private ProjectsActivity projectsActivity;

    public ProjectsServiceCaller(ProjectsActivity projectsActivity) {
        this.projectsActivity = projectsActivity;
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<Project> projects) {
        projectsActivity.onReadProjects(projects);
    }

    @Override
    public void onErrors(List<String> errors) {
        projectsActivity.displayMessage(WebServiceUtils.handleErrors(projectsActivity, errors));
    }
}
