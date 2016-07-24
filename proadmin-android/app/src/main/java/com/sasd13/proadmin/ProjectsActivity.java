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

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.gui.tab.ProjectItemModel;
import com.sasd13.proadmin.handler.ProjectsHandler;
import com.sasd13.proadmin.util.EnumAcademicLevelRes;
import com.sasd13.proadmin.util.filter.project.AcademicLevelCriteria;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends MotherActivity {

    private ProjectsHandler projectsHandler;
    private Spin spinAcademicLevels;
    private Recycler tab;

    private List<Project> projects = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);

        projectsHandler = new ProjectsHandler(this);

        buildView();
    }

    private void buildView() {
        buildSpinAcademicLevels();
        buildProjectsTab();
    }

    private void buildSpinAcademicLevels() {
        Spinner spinner = (Spinner) findViewById(R.id.projects_spinner);

        spinAcademicLevels = new Spin(spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                fillTabByAcademicLevel(EnumAcademicLevel.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fillSpinAcademicLevels();
    }

    private void fillSpinAcademicLevels() {
        List<String> list = new ArrayList<>();

        for (EnumAcademicLevelRes academicLevelsRes : EnumAcademicLevelRes.values()) {
            list.add(getResources().getString(academicLevelsRes.getStringRes()));
        }

        spinAcademicLevels.addAll(list);
    }

    private void fillTabByAcademicLevel(EnumAcademicLevel academicLevel) {
        tab.clear();

        addProjectsToTab(new AcademicLevelCriteria(academicLevel).meetCriteria(projects));
    }

    private void buildProjectsTab() {
        tab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) findViewById(R.id.projects_recyclerview));
    }

    private void fillProjectsTab(List<Project> projects) {
        tab.clear();

        List<RecyclerHolderPair> pairs = new ArrayList<>();
        RecyclerHolderPair pair;

        for (final Project project : projects) {
            pair = new RecyclerHolderPair(new ProjectItemModel(project));

            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    Intent intent = new Intent(ProjectsActivity.this, ProjectActivity.class);
                    intent.putExtra(Extra.PROJECT_ID, project.getId());

                    startActivity(intent);
                }
            });

            pairs.add(pair);
        }

        RecyclerHolder recyclerHolder = new RecyclerHolder();
        recyclerHolder.addAll(pairs);

        RecyclerHelper.addAll(tab, recyclerHolder);
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
        Cache.keepAll(this, projects);
    }

    public void fillTabProjects() {
        fillTabByAcademicLevel(EnumAcademicLevel.values()[spinAcademicLevels.getSelectedPosition()]);
    }
}