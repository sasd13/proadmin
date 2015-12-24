package com.sasd13.proadmin;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.DAO;
import com.sasd13.proadmin.db.SQLiteDAO;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemProject;
import com.sasd13.proadmin.util.CollectionUtil;

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

        fillSpinAcademicLevels();
        fillTabProjects();
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

    private void createTabProjects() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.projects_recyclerview);

        tab = new Tab(this, recyclerView);
    }

    private void fillSpinAcademicLevels() {
        for (AcademicLevel academicLevel : AcademicLevel.values()) {
            spin.addItem(String.valueOf(academicLevel));
        }
    }

    public void fillTabProjects() {
        DAO dao = SQLiteDAO.getInstance();

        dao.open();

        IEntityDAO entityDAO = dao.getEntityDAO(Project.class);
        projects = entityDAO.selectAll();

        dao.close();

        spin.resetPosition();

        fillTabProjectsByAcademicLevel(AcademicLevel.valueOf(spin.getSelectedItem()));
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
        if (ConnectivityChecker.isOnline(this)) {
            tryToPerformRefresh();
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    getResources().getString(R.string.message_error_connectivity)
            );
        }
    }

    private void tryToPerformRefresh() {

    }
}