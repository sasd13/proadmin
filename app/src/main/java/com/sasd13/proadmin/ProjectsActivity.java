package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.gui.widget.recycler.tab.Tab;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemProject;
import com.sasd13.proadmin.gui.widget.spin.Spin;
import com.sasd13.proadmin.util.CollectionSorter;
import com.sasd13.proadmin.ws.WebServiceFactory;

import java.util.List;

public class ProjectsActivity extends MotherActivity {

    private Spin spin;
    private Tab tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project);

        createSpinAcademicLevels();
        createTabProjects();
    }

    private void createSpinAcademicLevels() {
        Spinner spinner = (Spinner) findViewById(R.id.projects_spinner_academiclevels);

        this.spin = new Spin(this);
        this.spin.adapt(spinner, new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void createTabProjects() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        fillSpinAcademicLevels();

        try {
            refresh();
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

    private void refresh() {
        List<Project> projects = WebServiceFactory.get("PROJECT").get();

        fillTabProjects(projects);
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

            intent = new Intent(this, ProjectActivity.class);
            intent.putExtra(Extra.PROJECT_ID, project.getId());
            tabItemProject.setIntent(intent);

            this.tab.addItem(tabItemProject);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_projects, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_projects_action_refresh:
                refresh();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}