package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.ReadTask;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.fragment.project.ProjectFragment;
import com.sasd13.proadmin.ws.WSInformation;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectHandler implements IWSPromise {

    private ProjectFragment projectFragment;
    private ReadTask<Project> readTask;

    public ProjectHandler(ProjectFragment projectFragment) {
        this.projectFragment = projectFragment;
    }

    public void readProjects(long id) {
        readTask = new ReadTask<>(Project.class, WSInformation.URL_PROJECTS, this, 10000);
        readTask.execute(id);
    }

    public Project readProjectFromCache(long id) {
        return Cache.load(projectFragment.getContext(), id, Project.class);
    }

    @Override
    public void onLoad() {
        projectFragment.onLoad();
    }

    @Override
    public void onSuccess() {
        try {
            Project project = readTask.getResults().get(0);

            Cache.keep(projectFragment.getContext(), project);
            projectFragment.onReadSucceeded(project);
        } catch (IndexOutOfBoundsException e) {
            projectFragment.onError("Erreur de chargement des données");
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        projectFragment.onError("Echec de la connexion au serveur");
    }
}
