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
import proadmin.content.ListProjects;
import proadmin.content.ListYears;
import proadmin.content.Project;
import proadmin.content.Year;
import proadmin.content.ListSquads;
import proadmin.gui.color.ColorOnTouchListener;
import proadmin.gui.recycler.tab.Tab;
import proadmin.gui.recycler.tab.TabItemProject;
import proadmin.gui.recycler.tab.TabItemProjectTitle;
import proadmin.pattern.dao.DataManager;
import proadmin.gui.widget.SpinnerAdapter;
import proadmin.pattern.dao.accessor.DataAccessor;

public class ProjectsActivity extends ActionBarActivity {

    private Spinner spinnerYears;
    private Tab tab;

    private DataAccessor dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);

        this.spinnerYears = (Spinner) findViewById(R.id.projects_spinner_year);
        this.spinnerYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadProjectsOfSelectedYear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button buttonNew = (Button) findViewById(R.id.projects_button_new);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectsActivity.this, ProjectFormActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.MODE, Extra.MODE_NEW);

                startActivity(intent);
                finish();
            }
        });
        buttonNew.setOnTouchListener(new ColorOnTouchListener(getResources().getColor(R.color.customOrange)));

        RecyclerView recyclerViewProjects = (RecyclerView) findViewById(R.id.projects_recyclerview);
        this.tab = new Tab(this);
        this.tab.adapt(recyclerViewProjects);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.dao = DataManager.getDao();

        int spinnerSize = addYearsInSpinner();
        if (spinnerSize > 0) {
            this.spinnerYears.setVisibility(View.VISIBLE);
        } else {
            this.spinnerYears.setVisibility(View.INVISIBLE);
        }
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

    private int addYearsInSpinner() {
        this.dao.open();
        ListYears listYears = this.dao.selectYears();
        this.dao.close();

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, listYears);
        this.spinnerYears.setAdapter(spinnerAdapter.getAdapter());

        return listYears.size();
    }

    private void loadProjectsOfSelectedYear() {
        String selectedYear = (String) this.spinnerYears.getSelectedItem();
        Year year = new Year(Long.parseLong(selectedYear));

        this.dao.open();
        ListProjects listProjects = this.dao.selectProjectsOfYear(year);
        this.dao.close();

        createTabForListProjects(year, listProjects);
    }

    private void createTabForListProjects(Year year, ListProjects listProjects) {
        this.tab.clearItems();

        TabItemProjectTitle itemProjectTitle = new TabItemProjectTitle();
        this.tab.addItem(itemProjectTitle);

        TabItemProject itemProject;
        ListSquads listSquads;
        Intent intent;

        this.dao.open();

        for (Object project : listProjects) {
            itemProject = new TabItemProject();
            itemProject.setTitle(((Project) project).getTitle());
            itemProject.setGrade(((Project) project).getGrade().toString());

            listSquads = this.dao.selectSquadsOfYearAndProject(year, ((Project) project).getId());
            itemProject.setNbrSquads(listSquads.size());

            intent = new Intent(this, ProjectFormActivity.class);
            intent.putExtra(Extra.MODE, Extra.MODE_CONSULT);
            intent.putExtra(Extra.YEAR, year.toString());
            intent.putExtra(Extra.PROJECT_ID, ((Project) project).getId().toString());
            itemProject.setIntent(intent);

            this.tab.addItem(itemProject);
        }

        this.dao.close();
    }
}
