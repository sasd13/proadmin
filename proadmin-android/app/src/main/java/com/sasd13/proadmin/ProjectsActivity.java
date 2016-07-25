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

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
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
    private Recycler tabProjects;
    private List<Project> projects;
    private WaitDialog waitDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);
        GUIHelper.colorTitles(this);

        projectsHandler = new ProjectsHandler(this);
        projects = new ArrayList<>();

        buildView();
    }

    private void buildView() {
        buildSpinAcademicLevels();
        buildTabProjects();
        readTeacher();
    }

    private void buildSpinAcademicLevels() {
        Spinner spinner = (Spinner) findViewById(R.id.projects_spinner);

        spinAcademicLevels = new Spin(spinner, new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                fillTabProjectsByAcademicLevel(EnumAcademicLevel.values()[position]);
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

    private void buildTabProjects() {
        tabProjects = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) findViewById(R.id.projects_recyclerview));
        tabProjects.addDividerItemDecoration();
    }

    private void fillTabProjectsByAcademicLevel(EnumAcademicLevel academicLevel) {
        tabProjects.clear();

        addProjectsToTab(new AcademicLevelCriteria(academicLevel).meetCriteria(projects));
    }

    private void addProjectsToTab(List<Project> projects) {
        tabProjects.clear();

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

        RecyclerHelper.addAll(tabProjects, recyclerHolder);
    }

    private void readTeacher() {
        projectsHandler.readProjects();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_projects, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_projects_action_refresh:
                readTeacher();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void onLoad() {
        waitDialog = new WaitDialog(this);
        waitDialog.show();
    }

    public void onReadSuccess(List<Project> projects) {
        waitDialog.dismiss();

        this.projects.clear();
        this.projects.addAll(projects);

        refreshView();
    }

    private void refreshView() {
        int position = spinAcademicLevels.getSelectedPosition() > -1 ? spinAcademicLevels.getSelectedPosition() : 0;
        fillTabProjectsByAcademicLevel(EnumAcademicLevel.values()[position]);
    }

    public void onError(String message) {
        waitDialog.dismiss();

        projects.clear();
        projects.addAll(projectsHandler.readProjectsFromCache());

        refreshView();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}