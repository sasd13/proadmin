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

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemProject;
import com.sasd13.proadmin.util.CollectionUtil;
import com.sasd13.proadmin.ws.task.ReadTask;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends MotherActivity implements IRefreshable {

    private View viewTab, viewLoad;

    private Spin spin;
    private Tab tab;
    private List<Project> projects = new ArrayList<>();
    private ReadTask<Project> readTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);
        createSpinAcademicLevels();
        createSwicthableViews();
        createTabProjects();
        fillSpinAcademicLevels();
    }

    @Override
    protected void onStart() {
        super.onStart();

        refresh();
    }

    private void refresh() {
        if (ConnectivityChecker.isActive(this)) {
            projects.clear();

            readTask = new ReadTask<>(this, Project.class);
            readTask.execute();
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    getResources().getString(R.string.message_error_connectivity)
            );
        }
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

    private void createSwicthableViews() {
        viewTab = findViewById(R.id.projects_view_tab);
        viewLoad = findViewById(R.id.projects_view_load);
    }

    private void createTabProjects() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.projects_recyclerview);

        tab = new Tab(this, recyclerView);
    }

    private void fillSpinAcademicLevels() {
        for (AcademicLevel academicLevel : AcademicLevel.values()) {
            spin.addItem(String.valueOf(academicLevel));
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
    public void displayLoad() {
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
    public void displayContent() {
        for (Project project : readTask.getContent()) {
            projects.add(project);
        }

        fillTabProjects();
    }

    public void fillTabProjects() {
        fillTabProjectsByAcademicLevel(AcademicLevel.valueOf(spin.getSelectedItem()));

        switchToLoadView(false);
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

    @Override
    public void displayNotFound() {
        switchToLoadView(false);
    }
}