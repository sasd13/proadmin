package com.android.proadmin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proadmin.R;

public class ProjectsActivity extends ActionBarActivity {

    private Spinner spinnerYears;
    private Button buttonList, buttonNew;
    private RecyclerView recyclerViewProjects;
    private View layoutProject;

    private class ViewHolder {
        public TextView textViewYear, textViewId;
        public EditText editTextTitle, editTextDescription;
        public RadioGroup radioGroupGrade;
        public Button buttonCreate, buttonDelete, buttonDeleteAll;
    }

    private ViewHolder formProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);

        this.spinnerYears = (Spinner) findViewById(R.id.projects_spinner_year);
        addYearsInSpinner();
        selectProjects();

        this.buttonList = (Button) findViewById(R.id.projects_button_list);
        this.buttonNew = (Button) findViewById(R.id.projects_button_new);

        this.recyclerViewProjects = (RecyclerView) findViewById(R.id.projects_recyclerview);
        this.layoutProject = findViewById(R.id.projects_project_layout);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.projects_button_list:
                        recyclerViewProjects.setVisibility(View.VISIBLE);
                        layoutProject.setVisibility(View.GONE);
                        break;
                    case R.id.projects_button_new:
                        recyclerViewProjects.setVisibility(View.GONE);
                        layoutProject.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };

        this.buttonList.setOnClickListener(listener);
        this.buttonNew.setOnClickListener(listener);

        this.formProject = new ViewHolder();

        this.formProject.textViewYear = (TextView) findViewById(R.id.form_project_textview_project_year);
        this.formProject.textViewId = (TextView) findViewById(R.id.form_project_textview_project_id);
        this.formProject.editTextTitle = (EditText) findViewById(R.id.form_project_edittext_project_title);
        this.formProject.radioGroupGrade = (RadioGroup) findViewById(R.id.form_project_radiogroup_project_grade);
        this.formProject.editTextDescription = (EditText) findViewById(R.id.form_project_textview_project_description);

        this.formProject.buttonCreate = (Button) findViewById(R.id.form_project_button_create);
        this.formProject.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        this.formProject.buttonDelete = (Button) findViewById(R.id.form_project_button_delete);
        this.formProject.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        this.formProject.buttonDeleteAll = (Button) findViewById(R.id.form_project_button_delete_all);
        this.formProject.buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });
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

    private void addYearsInSpinner() {
        //TODO

    }

    private void selectProjects() {
        //TODO

    }

    private void insert() {
        //TODO

    }

    private void update() {
        //TODO

    }

    private void delete() {
        //TODO

    }

    private void deleteAll() {
        //TODO

    }

    private void select(String projectId) {
        //TODO

    }
}
