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

import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.gui.widget.spin.Spin;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.db.DAO;
import com.sasd13.proadmin.db.DAOFactory;
import com.sasd13.proadmin.gui.widget.recycler.tab.TabItemProject;
import com.sasd13.proadmin.util.CollectionUtil;
import com.sasd13.proadmin.wsclient.RestWebServiceClientFactory;

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

    private void fillSpinAcademicLevels() {
        for (AcademicLevel academicLevel : AcademicLevel.values()) {
            this.spin.addItem(String.valueOf(academicLevel));
        }
    }

    private void fillTabProjects() {
        DAO dao = DAOFactory.make();

        dao.open();
        this.projects = dao.selectAllProjects();
        dao.close();

        this.spin.resetPosition();

        fillTabProjectsByAcademicLevel(AcademicLevel.valueOf(this.spin.getSelectedItem()));
    }

    private void fillTabProjectsByAcademicLevel(AcademicLevel academicLevel) {
        this.tab.clearItems();

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
        pullProjectsFromWebService();

        fillTabProjects();

        Toast.makeText(this, R.string.toast_updated, Toast.LENGTH_SHORT).show();
    }

    private void pullProjectsFromWebService() {
        Project[] projects  = (Project[]) RestWebServiceClientFactory.make("PROJECT").getAll();

        persistPulledProjects(projects);
    }

    private void persistPulledProjects(Project[] projects) {
        DAO dao = DAOFactory.make();

        dao.open();
        for (Project project : projects) {
            dao.persistProject(project);
        }
        dao.close();
    }
}