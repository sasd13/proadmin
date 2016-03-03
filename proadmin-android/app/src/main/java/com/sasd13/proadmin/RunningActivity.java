package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.project.Project;

public class RunningActivity extends MotherActivity {

    private class DescriptorRunningViewHolder {
        public TextView textViewTitle, textViewAcademicLevel, textViewCode, textViewDescription;
    }

    private DescriptorRunningViewHolder descriptorRunning;

    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);
        createDescriptorRunning();
    }

    private void createDescriptorRunning() {
        descriptorRunning = new DescriptorRunningViewHolder();
        descriptorRunning.textViewTitle = (TextView) findViewById(R.id.project_textview_title);
        descriptorRunning.textViewAcademicLevel = (TextView) findViewById(R.id.project_textview_academiclevel);
        descriptorRunning.textViewCode = (TextView) findViewById(R.id.project_textview_code);
        descriptorRunning.textViewDescription = (TextView) findViewById(R.id.project_textview_description);
    }

    @Override
    protected void onStart() {
        super.onStart();

        project = Cache.load(getProjectIdFromIntent(), Project.class);

        fillDescriptorProject();
    }

    private long getProjectIdFromIntent() {
        return getIntent().getLongExtra(Extra.PROJECT_ID, 0);
    }

    private void fillDescriptorProject() {
        descriptorRunning.textViewTitle.setText(project.getTitle());
        descriptorRunning.textViewAcademicLevel.setText(String.valueOf(project.getAcademicLevel()));
        descriptorRunning.textViewCode.setText(project.getCode());
        descriptorRunning.textViewDescription.setText(project.getDescription());
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

    }
}