package com.android.proadmin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import proadmin.content.Grade;
import proadmin.content.ListProjects;
import proadmin.content.ListYears;
import proadmin.content.Project;
import proadmin.content.Year;
import proadmin.db.DataManager;
import proadmin.db.accessor.DataAccessor;
import proadmin.form.FormProjectValidator;
import proadmin.gui.widget.CustomDialog;
import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.gui.widget.SpinnerAdapter;

public class ProjectsActivity extends ActionBarActivity {

    private Spinner spinnerYears;
    private RecyclerView recyclerViewProjects;
    private View layoutProject;

    private class ViewHolder {
        public TextView textViewYear, textViewId;
        public EditText editTextTitle, editTextDescription;
        public RadioGroup radioGroupGrade;
        public RadioButton radioButtonL1, radioButtonL2, radioButtonL3, radioButtonM1, radioButtonM2;
        public Button buttonSave, buttonRemove, buttonRemoveAll;
    }

    private ViewHolder formProject;

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

        Button buttonList = (Button) findViewById(R.id.projects_button_list);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToList();
            }
        });

        Button buttonNew = (Button) findViewById(R.id.projects_button_new);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNew();
            }
        });

        //this.recyclerViewProjects = (RecyclerView) findViewById(R.id.projects_recyclerview);
        this.layoutProject = findViewById(R.id.projects_form_project_layout);

        this.formProject = new ViewHolder();

        this.formProject.textViewYear = (TextView) findViewById(R.id.form_project_textview_project_year);
        this.formProject.textViewId = (TextView) findViewById(R.id.form_project_textview_project_id);
        this.formProject.editTextTitle = (EditText) findViewById(R.id.form_project_edittext_project_title);
        this.formProject.editTextDescription = (EditText) findViewById(R.id.form_project_textview_project_description);
        this.formProject.radioGroupGrade = (RadioGroup) findViewById(R.id.form_project_radiogroup_project_grade);
        this.formProject.radioButtonL1 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_l1);
        this.formProject.radioButtonL2 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_l2);
        this.formProject.radioButtonL3 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_l3);
        this.formProject.radioButtonM1 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_m1);
        this.formProject.radioButtonM2 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_m2);

        this.formProject.buttonSave = (Button) findViewById(R.id.form_project_button_save);
        this.formProject.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProjectForCurrentYear();
            }
        });

        this.formProject.buttonRemove = (Button) findViewById(R.id.form_project_button_remove);
        this.formProject.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProjectForYear();
            }
        });

        this.formProject.buttonRemoveAll = (Button) findViewById(R.id.form_project_button_remove_all);
        this.formProject.buttonRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProjectForAllYears();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.dao = DataManager.getDao();

        int spinnerSize = addYearsInSpinner();
        if (spinnerSize > 0) {
            loadProjectsOfSelectedYear();
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
    }

    private void addProjectForCurrentYear() {
        Project project = validForm();

        if (project != null) {
            this.dao.open();

            boolean inserted = this.dao.insertProject(project, new Year());

            this.dao.close();

            if(inserted) {
                CustomDialog.showDialog(
                        this,
                        "Project",
                        "The project is added",
                        CustomDialogBuilder.TYPE_ONEBUTTON_OK,
                        null
                );
            } else {
                CustomDialog.showDialog(
                        this,
                        "Project",
                        "The project was not added",
                        CustomDialogBuilder.TYPE_ONEBUTTON_OK,
                        null
                );
            }
        }
    }

    private Project validForm() {
        Project project = null;

        String year = this.formProject.textViewYear.getText().toString().trim();
        String projectId = this.formProject.textViewId.getText().toString().trim();
        String title = this.formProject.editTextTitle.getEditableText().toString().trim();
        String description = this.formProject.editTextDescription.getEditableText().toString().trim();

        Grade grade;

        int checkedRadioButtonId = this.formProject.radioGroupGrade.getCheckedRadioButtonId();
        if (checkedRadioButtonId == this.formProject.radioButtonM2.getId()) {
            grade = Grade.M2;
        } else if (checkedRadioButtonId == this.formProject.radioButtonM1.getId()) {
            grade = Grade.M1;
        } else if (checkedRadioButtonId == this.formProject.radioButtonL3.getId()) {
            grade = Grade.L3;
        } else if (checkedRadioButtonId == this.formProject.radioButtonL2.getId()) {
            grade = Grade.L2;
        } else {
            grade = Grade.L1;
        }

        String message = FormProjectValidator.validForm(year, title, description);

        if (message != null ) {
            CustomDialog.showDialog(this, "Erreur formulaire", message, CustomDialogBuilder.TYPE_ONEBUTTON_OK, null);
        } else {
            project = new Project(title, grade, description);
        }

        return project;
    }

    private void switchToList() {
        loadProjectsOfSelectedYear();
        showList();
    }

    private void showList() {
        //recyclerViewProjects.setVisibility(View.VISIBLE);
        layoutProject.setVisibility(View.GONE);
    }

    private void switchToNew() {
        prepareFormForNewProject();

        this.formProject.buttonSave.setVisibility(View.VISIBLE);
        this.formProject.buttonRemove.setVisibility(View.INVISIBLE);
        this.formProject.buttonRemoveAll.setVisibility(View.INVISIBLE);

        showProject();
    }

    private void showProject() {
        layoutProject.setVisibility(View.VISIBLE);
        //recyclerViewProjects.setVisibility(View.GONE);
    }

    private void prepareFormForNewProject() {
        this.formProject.textViewYear.setText(new Year().toString());
        this.formProject.textViewId.setText("(Generated)");
        this.formProject.editTextTitle.setText("", EditText.BufferType.EDITABLE);
        this.formProject.editTextDescription.setText("", EditText.BufferType.EDITABLE);
        this.formProject.radioButtonL1.setChecked(true);
    }

    private void switchToConsult(String projectId) {
        loadProject(projectId);

        this.formProject.buttonSave.setVisibility(View.VISIBLE);
        this.formProject.buttonRemove.setVisibility(View.INVISIBLE);
        this.formProject.buttonRemoveAll.setVisibility(View.INVISIBLE);

        showProject();
    }

    private void updateProject() {
        //TODO

    }

    private void removeProjectForYear() {
        //TODO

    }

    private void removeProjectForAllYears() {
        //TODO

    }

    private void loadProject(String projectId) {
        //TODO

    }
}
