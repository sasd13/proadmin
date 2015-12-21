package com.sasd13.proadmin.ws.task;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.proadmin.ProjectsActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.DAO;
import com.sasd13.proadmin.db.DAOFactory;
import com.sasd13.proadmin.ws.rest.ProjectsWebServiceClient;

public class ProjectsWebServiceAsyncTask2 extends AsyncTask<Long, Integer, Project[]> {

    private ProjectsActivity projectsActivity;
    private ProjectsWebServiceClient service;
    private WaitDialog waitDialog;

    public ProjectsWebServiceAsyncTask2(final ProjectsActivity projectsActivity) {
        this.projectsActivity = projectsActivity;
        service = new ProjectsWebServiceClient();
        waitDialog = new WaitDialog(projectsActivity, projectsActivity.getResources().getString(R.string.dialog_title_request));

        waitDialog.setCancelable(true);
        waitDialog.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                projectsActivity.getResources().getString(R.string.button_cancel),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cancel(true);

                        waitDialog.dismiss();

                        Toast.makeText(projectsActivity, R.string.dialog_message_error_request_canceled, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        waitDialog.show();
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

        System.out.println("Hello");

        return projects;
    }

    @Override
    protected void onPostExecute(Project[] projects) {
        super.onPostExecute(projects);

        waitDialog.dismiss();

        try {
            persistPulledProjects(projects);

            projectsActivity.fillTabProjects();

            Toast.makeText(projectsActivity, R.string.message_updated, Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e) {
            e.printStackTrace();
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
