package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.SessionHelper;

public class ProjectActivity extends MotherActivity {

    private static class ProjectViewHolder {
        TextView textViewTitle, textViewAcademicLevel, textViewCode, textViewDescription;
    }

    private ProjectViewHolder projectViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);
        buildView();
    }

    private void buildView() {
        projectViewHolder = new ProjectViewHolder();
        projectViewHolder.textViewTitle = (TextView) findViewById(R.id.project_textview_title);
        projectViewHolder.textViewAcademicLevel = (TextView) findViewById(R.id.project_textview_academiclevel);
        projectViewHolder.textViewCode = (TextView) findViewById(R.id.project_textview_code);
        projectViewHolder.textViewDescription = (TextView) findViewById(R.id.project_textview_description);
    }

    @Override
    protected void onStart() {
        super.onStart();

        long id = SessionHelper.getIntentExtraId(this, Extra.PROJECT_ID);
        Project project = Cache.load(this, id, Project.class);

        fillFormProject(project);
    }

    private void fillFormProject(Project project) {
        projectViewHolder.textViewTitle.setText(project.getTitle());
        projectViewHolder.textViewAcademicLevel.setText(project.getAcademicLevel().getCode());
        projectViewHolder.textViewCode.setText(project.getCode());
        projectViewHolder.textViewDescription.setText(project.getDescription());
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
        startActivity(new Intent(this, RunningsActivity.class));
    }
}