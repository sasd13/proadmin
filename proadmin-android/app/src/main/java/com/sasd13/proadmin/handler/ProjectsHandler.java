package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.ReadTask;
import com.sasd13.proadmin.ProjectsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.ws.WSInformation;

import java.util.List;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectsHandler implements IWSPromise {

    private ProjectsActivity projectsActivity;
    private ReadTask<Project> readTask;

    public ProjectsHandler(ProjectsActivity projectsActivity) {
        this.projectsActivity = projectsActivity;
    }

    public void readProjects() {
        readTask = new ReadTask<>(Project.class, WSInformation.URL_PROJECTS, this);
        readTask.execute();
    }

    public List<Project> readProjectsFromCache() {
        return Cache.loadAll(projectsActivity, Project.class);
    }

    @Override
    public void onLoad() {
        projectsActivity.onLoad();
    }

    @Override
    public void onSuccess() {
        try {
            List<Project> projects = readTask.getResults();

            Cache.keepAll(projectsActivity, projects);
            projectsActivity.onReadSuccess(projects);
        } catch (IndexOutOfBoundsException e) {
            projectsActivity.onError("Erreur de chargement des données");
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        projectsActivity.onError("Echec de la connexion au serveur");
    }
}
