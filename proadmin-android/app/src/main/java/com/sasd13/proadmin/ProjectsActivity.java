package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.ws.task.ReadTask;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends MotherActivity {

    private Spin spinAcademicLevels;
    private Tab tabProjects;

    private List<Project> projects = new ArrayList<>();
    private ReadTask<Project> readTaskProjects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);
        createSpinAcademicLevels();
        createTabProjects();
        fillSpinAcademicLevels();
    }

    private void createSpinAcademicLevels() {
        Spinner spinner = (Spinner) findViewById(R.id.projects_spinner);

        spinAcademicLevels = new Spin(spinner, new AdapterView.OnItemSelectedListener() {

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
        tabProjects.clear();

        //addProjectsToTab(new AcademicLevelCriteria(academicLevel).meetCriteria(projects));
    }
    /*
    private void addProjectsToTab(List<Project> list) {
        ProjectItem tabItemProject;

        for (final Project project : list) {
            tabItemProject = new ProjectItem();
            tabItemProject.setLabel(project.getTitle());
            tabItemProject.setCode(project.getCode());
            tabItemProject.setDescription(project.getDescription());

            tabItemProject.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClickOnRecyclerItem(RecyclerItem recyclerItem) {
                    Intent intent = new Intent(ProjectsActivity.this, ProjectActivity.class);
                    intent.putExtra(Extra.PROJECT_ID, project.getId());
                }
            });

            tabProjects.addItem(tabItemProject);
        }
    }
    */
    private void createTabProjects() {
        //tabProjects = new Tab((RecyclerView) findViewById(R.id.projects_recyclerview));
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
        /*
        if (NetworkHelper.isConnected(this)) {
            readTaskProjects = new ReadTask<>(this, Project.class);
            readTaskProjects.execute();
        } else {
            NetworkHelper.displayMessageNotConnected(this);
        }*/
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

    public void onSuccess() {
        projects.clear();
        projects.addAll(readTaskProjects.getResults());

        fillTabProjects();
        Cache.keepAll(projects);
    }

    public void fillTabProjects() {
        fillTabProjectsByAcademicLevel(AcademicLevel.find(spinAcademicLevels.getSelectedItem()));
    }
}