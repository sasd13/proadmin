package com.sasd13.proadmin.wsclient;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.DAO;
import com.sasd13.proadmin.db.DAOFactory;

public class ProjectsWebServiceAsyncTask extends AsyncTask<Void, Integer, Project[]> {

    private Context context;
    private ProjectsWebServiceClient service;
    private WaitDialog waitDialog;

    public ProjectsWebServiceAsyncTask(Context context) {
        this.context = context;
        this.service = new ProjectsWebServiceClient();
        this.waitDialog = new WaitDialog(this.context, this.context.getResources().getString(R.string.dialog_title_request));

        this.waitDialog.setCancelable(true);
        this.waitDialog.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                this.context.getResources().getString(R.string.button_cancel),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        waitDialog.dismiss();
                        cancel(true);
                    }
                }
        );
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        this.waitDialog.show();
    }

    @Override
    protected Project[] doInBackground(Void... aVoid) {
        while (!isCancelled()) {
            return this.service.getAll();
        }

        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        Toast.makeText(this.context, R.string.dialog_message_error_request_canceled, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Project[] projects) {
        super.onPostExecute(projects);

        this.waitDialog.dismiss();

        try {
            persistPulledProjects(projects);

            Toast.makeText(this.context, R.string.message_updated, Toast.LENGTH_SHORT).show();
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
