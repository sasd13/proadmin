package com.sasd13.proadmin.ws;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.proadmin.MotherActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.gui.widget.recycler.tab.Tab;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemProject;
import com.sasd13.proadmin.gui.widget.spin.Spin;
import com.sasd13.proadmin.util.CollectionSorter;

import java.util.List;

public class RunningActivity extends MotherActivity {

    private Spin spin;
    private Tab tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        createSpinAcademicLevels();
        createTabProjects();
    }

    private void createSpinAcademicLevels() {

    }

    private void createTabProjects() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        fillSpinAcademicLevels();

        try {
            List<Project> projects = WebServiceFactory.get("PROJECT").get();

            fillTabProjects(projects);
        } catch (NullPointerException e) {
            CustomDialog.showOkDialog(this, "Error", "Service not found");
        }
    }

    private void fillSpinAcademicLevels() {
        this.spin.clearItems();

        for (AcademicLevel academicLevel : AcademicLevel.values()) {
            this.spin.addItem(String.valueOf(academicLevel));
        }

        this.spin.validate();
    }

    private void fillTabProjects(List<Project> list) {
        this.tab.clearItems();

        this.tab.addItem(new TabItemProjectTitle());

        CollectionSorter.sortProjectsByAcademicLevel(list);

        addProjectsToTab(list);
    }

    private void addProjectsToTab(List<Project> list) {
        TabItemProject tabItemProject;
        Intent intent;

        for (Project project : list) {
            tabItemProject = new TabItemProject();

            tabItemProject.setTitle(project.getTitle());
            tabItemProject.setCode(project.getCode());
            tabItemProject.setDescription(project.getDescription());

            intent = new Intent();
            intent.putExtra(Extra.PROJECT_ID, project.getId());
            tabItemProject.setIntent(intent);

            this.tab.addItem(tabItemProject);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home_action_logout:
                logOut();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}