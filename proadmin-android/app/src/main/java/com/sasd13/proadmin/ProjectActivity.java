package com.sasd13.proadmin;

import android.os.Bundle;

import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.project.Project;

public class ProjectActivity extends MotherActivity {

    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);
        createDescriptorProject();
    }

    private void createDescriptorProject() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        project = (Project) Cache.load(getProjectIdFromIntent(), Project.class);

        fillDescriptorProject();
    }

    private long getProjectIdFromIntent() {
        return getIntent().getLongExtra(Extra.PROJECT_ID, 0);
    }

    private void fillDescriptorProject() {

    }
}