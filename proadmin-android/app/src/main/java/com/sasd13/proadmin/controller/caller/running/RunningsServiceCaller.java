package com.sasd13.proadmin.controller.caller.running;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.ProjectsActivity;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.service.RunningsService;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class RunningsServiceCaller implements RunningsService.Caller {

    private ProjectsActivity projectsActivity;

    public RunningsServiceCaller(ProjectsActivity projectsActivity) {
        this.projectsActivity = projectsActivity;
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onCreated() {
        projectsActivity.displayMessage(projectsActivity.getResources().getString(R.string.message_saved));
        projectsActivity.onBackPressed();
    }

    @Override
    public void onUpdated() {
        projectsActivity.displayMessage(projectsActivity.getResources().getString(R.string.message_updated));
    }

    @Override
    public void onDeleted() {
        projectsActivity.displayMessage(projectsActivity.getResources().getString(R.string.message_deleted));
        projectsActivity.listProjects();
    }

    @Override
    public void onReaded(List<Running> runnings) {
        projectsActivity.onReadRunnings(runnings);
    }

    @Override
    public void onErrors(List<String> errors) {
        projectsActivity.displayMessage(WebServiceUtils.handleErrors(projectsActivity, errors));
    }
}
