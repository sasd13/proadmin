package com.sasd13.proadmin.handler;

import com.sasd13.proadmin.ProjectsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.ws.task.ReadTask;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectsHandler implements Promise {

    private ProjectsActivity projectsActivity;
    private ReadTask<Project> readTaskProjects;

    public ProjectsHandler(ProjectsActivity projectsActivity) {
        this.projectsActivity = projectsActivity;
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}
