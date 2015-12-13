package com.sasd13.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sasd13.wsprovider.proadmin.bean.AcademicLevel;
import com.sasd13.wsprovider.proadmin.bean.project.Project;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.gui.widget.recycler.tab.Tab;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemProject;
import com.sasd13.proadmin.gui.widget.spin.Spin;
import com.sasd13.proadmin.util.CollectionUtil;
import com.sasd13.proadmin.ws.WebServiceProviderFactory;

import java.util.List;

public class ProjectsActivity extends MotherActivity {

    private Spin spin;
    private Tab tab;
    private List<Project> projects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);

        createSpinAcademicLevels();
        createTabProjects();
    }

    private void createSpinAcademicLevels() {
        Spinner spinner = (Spinner) findViewById(R.id.projects_spinner);

        this.spin = new Spin(this, spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillTabProjectsByAcademicLevel(AcademicLevel.valueOf(spin.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void createTabProjects() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.projects_recyclerview);

        this.tab = new Tab(this, recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        fillSpinAcademicLevels();

        try {
            fillTabProjects();
        } catch (NullPointerException e) {
            CustomDialog.showOkDialog(this, "Error", "Service not found");
        }
    }

    private void fillSpinAcademicLevels() {
        this.spin.clearItems();

        for (AcademicLevel academicLevel : AcademicLevel.values()) {
            this.spin.addItem(String.valueOf(academicLevel));
        }
    }

    private void fillTabProjects() {
        this.projects = WebServiceProviderFactory.get("PROJECT").get();

        this.spin.resetPosition();

        fillTabProjectsByAcademicLevel(AcademicLevel.valueOf(this.spin.getSelectedItem()));
    }

    private void fillTabProjectsByAcademicLevel(AcademicLevel academicLevel) {
        this.tab.clearItems();

        addProjectsToTab(CollectionUtil.filterProjectsByAcademicLevel(this.projects, academicLevel));
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

    private void refresh() {
        fillTabProjects();

        Toast.makeText(this, R.string.toast_updated, Toast.LENGTH_SHORT).show();
    }
}