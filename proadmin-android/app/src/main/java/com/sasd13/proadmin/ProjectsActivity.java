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

import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemProject;
import com.sasd13.proadmin.pattern.command.ILoader;
import com.sasd13.proadmin.util.filter.project.AcademicLevelCriteria;
import com.sasd13.proadmin.ws.task.LoaderReadTask;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends MotherActivity implements ILoader {

    private Spin spinAcademicLevels;
    private View viewLoad, viewTab;
    private Tab tabProjects;

    private List<Project> projects = new ArrayList<>();
    private LoaderReadTask<Project> readTaskProjects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);
        createSpinAcademicLevels();
        createSwicthableViews();
        createTabProjects();
        fillSpinAcademicLevels();
    }

    private void createSpinAcademicLevels() {
        Spinner spinner = (Spinner) findViewById(R.id.projects_spinner);

        spinAcademicLevels = new Spin(this, spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillTabProjectsByAcademicLevel(AcademicLevel.find(spinAcademicLevels.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void fillTabProjectsByAcademicLevel(AcademicLevel academicLevel) {
        tabProjects.clearItems();

        addProjectsToTab(new AcademicLevelCriteria(academicLevel).meetCriteria(projects));
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

            tabProjects.addItem(tabItemProject);
        }
    }

    private void createSwicthableViews() {
        viewLoad = findViewById(R.id.projects_view_load);
        viewTab = findViewById(R.id.projects_view_tab);
    }

    private void createTabProjects() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.projects_recyclerview);

        tabProjects = new Tab(this, recyclerView, R.layout.tabitem_project);
    }

    private void fillSpinAcademicLevels() {
        for (AcademicLevel academicLevel : AcademicLevel.getLevels()) {
            spinAcademicLevels.addItem(academicLevel.getName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        refresh();
    }

    private void refresh() {
        if (ConnectivityChecker.isActive(this)) {
            readTaskProjects = new LoaderReadTask<>(this, Project.class, this);
            readTaskProjects.execute();
        } else {
            ConnectivityChecker.showNotActive(this);
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

    @Override
    public void onLoad() {
        switchToLoadView(true);
    }

    private void switchToLoadView(boolean switched) {
        if (switched) {
            viewLoad.setVisibility(View.VISIBLE);
            viewTab.setVisibility(View.GONE);
        } else {
            viewTab.setVisibility(View.VISIBLE);
            viewLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCompleted() {
        projects.clear();
        projects.addAll(readTaskProjects.getResults());

        fillTabProjects();
        Cache.keepAll(projects);
        switchToLoadView(false);
    }

    public void fillTabProjects() {
        fillTabProjectsByAcademicLevel(AcademicLevel.find(spinAcademicLevels.getSelectedItem()));
    }

    @Override
    public void onError() {
        switchToLoadView(false);
    }
}