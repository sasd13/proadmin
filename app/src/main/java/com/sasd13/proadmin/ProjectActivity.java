package com.sasd13.proadmin;

import android.os.Bundle;

import com.sasd13.proadmin.db.DAOFactory;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.wsprovider.proadmin.bean.project.Project;

public class ProjectActivity extends MotherActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);

        createPresentationProject();
    }

    private void createPresentationProject() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            Project project = DAOFactory.make().selectProject(getProjectIdFromIntent());

            fillPresentationProject(project);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private long getProjectIdFromIntent() {
        return getIntent().getLongExtra(Extra.PROJECT_ID, 0);
    }

    private void fillPresentationProject(Project project) {

    }
}