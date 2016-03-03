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
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemProject;
import com.sasd13.proadmin.pattern.command.IRefreshable;
import com.sasd13.proadmin.util.CollectionUtil;
import com.sasd13.proadmin.ws.task.RefreshableReadTask;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends MotherActivity implements IRefreshable {

    private Spin spin;
    private View viewLoad, viewTab;
    private Tab tab;

    private RefreshableReadTask<Project> readTask;
    private List<Project> projects = new ArrayList<>();

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

        spin = new Spin(this, spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillTabProjectsByAcademicLevel(AcademicLevel.valueOf(spin.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void fillTabProjectsByAcademicLevel(AcademicLevel academicLevel) {
        tab.clearItems();

        addProjectsToTab(CollectionUtil.filterProjectsByAcademicLevel(projects, academicLevel));
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

            tab.addItem(tabItemProject);
        }
    }

    private void createSwicthableViews() {
        viewLoad = findViewById(R.id.projects_view_load);
        viewTab = findViewById(R.id.projects_view_tab);
    }

    private void createTabProjects() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.projects_recyclerview);

        tab = new Tab(this, recyclerView, R.layout.tabitem_project);
    }

    private void fillSpinAcademicLevels() {
        for (AcademicLevel academicLevel : AcademicLevel.values()) {
            spin.addItem(String.valueOf(academicLevel));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        refresh();
    }

    private void refresh() {
        if (ConnectivityChecker.isActive(this)) {
            readTask = new RefreshableReadTask<>(this, Project.class, this);
            readTask.execute();
        } else {
            ConnectivityChecker.showConnectivityError(this);
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
    public void doInLoad() {
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
    public void doInCompleted() {
        projects.clear();
        projects.addAll(readTask.getContent());

        fillTabProjects();
        Cache.keepAll(projects);

        switchToLoadView(false);
    }

    public void fillTabProjects() {
        fillTabProjectsByAcademicLevel(AcademicLevel.valueOf(spin.getSelectedItem()));
    }

    @Override
    public void doInError() {
        switchToLoadView(false);
    }
}