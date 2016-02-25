package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.project.Project;

public class ProjectActivity extends MotherActivity {

    private class DescriptorProjectViewHolder {
        public TextView textViewTitle, textViewAcademicLevel, textViewCode, textViewDescription;
    }

    private DescriptorProjectViewHolder descriptorProject;

    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);
        createDescriptorProject();
    }

    private void createDescriptorProject() {
        descriptorProject = new DescriptorProjectViewHolder();
        descriptorProject.textViewTitle = (TextView) findViewById(R.id.project_textview_title);
        descriptorProject.textViewAcademicLevel = (TextView) findViewById(R.id.project_textview_academiclevel);
        descriptorProject.textViewCode = (TextView) findViewById(R.id.project_textview_code);
        descriptorProject.textViewDescription = (TextView) findViewById(R.id.project_textview_description);
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
        descriptorProject.textViewTitle.setText(project.getTitle());
        descriptorProject.textViewAcademicLevel.setText(String.valueOf(project.getAcademicLevel()));
        descriptorProject.textViewCode.setText(project.getCode());
        descriptorProject.textViewDescription.setText(project.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_project, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_project_action_runnings:
                listRunnings();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void listRunnings() {
        Intent intent = new Intent(this, RunningsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.PROJECT_ID, project.getId());

        startActivity(intent);
    }
}