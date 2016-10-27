package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.IWSPromise;
import com.sasd13.androidex.ws.rest.task.ReadTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.project.ProjectsFragment;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.ws.WSInformation;

import java.util.List;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectsService implements IWSPromise {

    private ProjectsFragment projectsFragment;
    private ReadTask<Project> readTask;

    public ProjectsService(ProjectsFragment projectsFragment) {
        this.projectsFragment = projectsFragment;
    }

    public void readProjects() {
        readTask = new ReadTask<>(WSInformation.URL_WS_PROJECTS, this, Project.class);

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
            projectsFragment.onError(R.string.ws_error_data_retrieval_error);
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        projectsFragment.onError(R.string.ws_error_server_connection_failed);
    }
}
