package com.android.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import proadmin.constant.Extra;
import proadmin.bean.project.Project;
import proadmin.content.Year;
import proadmin.data.dao.DataAccessorManager;
import proadmin.data.dao.accessor.DataAccessor;
import proadmin.gui.color.ColorOnTouchListener;
import proadmin.gui.widget.recycler.tab.Tab;
import proadmin.gui.widget.recycler.tab.TabItemProject;
import proadmin.gui.widget.recycler.tab.TabItemProjectTitle;
import proadmin.gui.widget.spin.Spin;

public class ProjectsActivity extends ActionBarActivity {

    private DataAccessor dao = DataAccessorManager.getDao();
    private Tab tab;
    private Spin spin;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);

        this.tab = new Tab(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.projects_recyclerview);
        this.tab.adapt(recyclerView);

        this.spin = new Spin(this);

        Spinner spinnerYears = (Spinner) findViewById(R.id.projects_spinner_year);
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadProjectsOfSelectedYear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        this.spin.adapt(spinnerYears, onItemSelectedListener);

        Button buttonNew = (Button) findViewById(R.id.projects_button_new);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectsActivity.this, ProjectFormActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.MODE, Extra.MODE_NEW);

                startActivity(intent);
            }
        });
        buttonNew.setOnTouchListener(new ColorOnTouchListener(getResources().getColor(R.color.customOrange)));

        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        addYearsInSpinner();

        if (this.spin.size() > 0) {
            this.spin.setVisible(true);

            loadProjectsOfSelectedYear();
        } else {
            this.spin.setVisible(false);
        }
    }

    private void addYearsInSpinner() {
        this.spin.clearItems();

        this.dao.open();
        ListYears listYears = this.dao.selectYears();
        this.dao.close();

        for (Object year : listYears) {
            this.spin.addItem(year.toString());
        }

        this.spin.validate();
    }

    private void loadProjectsOfSelectedYear() {
        String stringYear = this.spin.getSelectedItem();
        Year year = new Year(Long.parseLong(stringYear));

        this.dao.open();
        ListProjects listProjects = this.dao.selectProjectsOfYear(year);
        this.dao.close();

        createTabForListProjects(year, listProjects);
    }

    private void createTabForListProjects(Year year, ListProjects listProjects) {
        this.tab.clearItems();
        this.tab.addItem(new TabItemProjectTitle());

        TabItemProject itemProject;
        ListSquads listSquads;
        Intent intent;

        this.dao.open();

        for (Object project : listProjects) {
            itemProject = new TabItemProject();
            itemProject.setTitle(((Project) project).getTitle());
            itemProject.setGrade(((Project) project).getAcademicLevel().toString());

            listSquads = this.dao.selectSquadsOfYearAndProject(year, ((Project) project).getId());
            itemProject.setNbrSquads(Integer.toString(listSquads.size()));

            intent = new Intent(this, ProjectFormActivity.class);
            intent.putExtra(Extra.MODE, Extra.MODE_CONSULT);
            intent.putExtra(Extra.YEAR, year.toString());
            intent.putExtra(Extra.PROJECT_ID, ((Project) project).getId());
            itemProject.setIntent(intent);

            this.tab.addItem(itemProject);
        }

        this.dao.close();
    }
}
