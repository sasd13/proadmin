package com.sasd13.proadmin;

import android.os.Bundle;

import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.DAO;

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


    }

    private long getProjectIdFromIntent() {
        return getIntent().getLongExtra(Extra.PROJECT_ID, 0);
    }

    private void fillPresentationProject(Project project) {

    }
}