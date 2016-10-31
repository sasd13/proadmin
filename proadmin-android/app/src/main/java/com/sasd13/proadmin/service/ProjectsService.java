package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.project.ProjectsFragment;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectsService implements IHttpCallback {

    private ProjectsFragment projectsFragment;
    private ReadTask<Project> readTask;

    public ProjectsService(ProjectsFragment projectsFragment) {
        this.projectsFragment = projectsFragment;
    }

    public void readProjects() {
        readTask = new ReadTask<>(WSResources.URL_WS_PROJECTS, this, Project.class);

        readTask.execute();
    }

    public List<Project> readProjectsFromCache() {
        return null;
    }

    @Override
    public void onLoad() {
        projectsFragment.onLoad();
    }

    @Override
    public void onSuccess() {
        try {
            List<Project> projects = readTask.getResults();

            projectsFragment.onReadSucceeded(projects);
        } catch (IndexOutOfBoundsException e) {
            projectsFragment.onError(R.string.error_ws_retrieve_data);
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        projectsFragment.onError(R.string.error_ws_server_connection);
    }
}
