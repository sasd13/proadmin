package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.ReadTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.activities.fragments.project.ProjectsFragment;
import com.sasd13.proadmin.ws.WSInformation;

import java.util.List;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectsHandler implements IWSPromise {

    private ProjectsFragment projectsFragment;
    private ReadTask<Project> readTask;

    public ProjectsHandler(ProjectsFragment projectsFragment) {
        this.projectsFragment = projectsFragment;
    }

    public void readProjects() {
        readTask = new ReadTask<>(Project.class, WSInformation.URL_WS_PROJECTS, this, 10000);

        readTask.execute();
    }

    public List<Project> readProjectsFromCache() {
        return Cache.loadAll(projectsFragment.getContext(), Project.class);
    }

    @Override
    public void onLoad() {
        projectsFragment.onLoad();
    }

    @Override
    public void onSuccess() {
        try {
            List<Project> projects = readTask.getResults();

            Cache.keepAll(projectsFragment.getContext(), projects);
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
