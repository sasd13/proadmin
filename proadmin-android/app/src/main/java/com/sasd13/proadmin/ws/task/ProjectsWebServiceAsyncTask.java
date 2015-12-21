package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;

import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.DAO;
import com.sasd13.proadmin.ws.rest.ProjectsWebServiceClient;

public class ProjectsWebServiceAsyncTask extends AsyncTask<Long, Integer, Project[]> {

    private ProjectsWebServiceClient service;

    public ProjectsWebServiceAsyncTask() {
        service = new ProjectsWebServiceClient();
    }

    @Override
    protected Project[] doInBackground(Long... ids) {
        Project[] projects = null;

        if (!isCancelled()) {
            if (ids.length == 0) {
                projects = service.getAll();
            } else {
                projects = new Project[ids.length];

                for (int i=0; i<ids.length; i++) {
                    projects[i] = (ids[i] > 0) ? service.get(ids[i]) : null;
                }
            }
        }

        return projects;
    }

    @Override
    protected void onPostExecute(Project[] projects) {
        super.onPostExecute(projects);

        if (projects != null) {
            persistPulledProjects(projects);
        }
    }

    private void persistPulledProjects(Project[] projects) {
        DAO dao = DAOFactory.make();

        dao.open();
        for (Project project : projects) {
            dao.persistProject(project);
        }
        dao.close();
    }
}
