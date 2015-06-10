package com.android.proadmin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proadmin.R;

import proadmin.content.Grade;
import proadmin.content.ListProjects;
import proadmin.content.ListYears;
import proadmin.content.Project;
import proadmin.content.Year;
import proadmin.db.DAO;
import proadmin.gui.widget.CustomDialog;
import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.gui.widget.SpinnerAdapter;
import proadmin.tool.form.FormProjectValidator;

public class ProjectsActivity extends ActionBarActivity {

    private Spinner spinnerYears;
    private RecyclerView recyclerViewProjects;
    private View layoutProject;

    private class ViewHolder {
        public TextView textViewYear, textViewId;
        public EditText editTextTitle, editTextDescription;
        public RadioGroup radioGroupGrade;
        public RadioButton radioButtonL1, radioButtonL2, radioButtonL3, radioButtonM1, radioButtonM2;
        public Button buttonCreate, buttonDelete, buttonDeleteAll;
    }

    private ViewHolder formProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);

        this.spinnerYears = (Spinner) findViewById(R.id.projects_spinner_year);

        Button buttonList = (Button) findViewById(R.id.projects_button_list);
        Button buttonNew = (Button) findViewById(R.id.projects_button_new);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.projects_button_list:
                        switchToList();
                        break;
                    case R.id.projects_button_new:
                        switchToNew();
                        break;
                }
            }
        };

        buttonList.setOnClickListener(listener);
        buttonNew.setOnClickListener(listener);

        this.recyclerViewProjects = (RecyclerView) findViewById(R.id.projects_recyclerview);
        this.layoutProject = findViewById(R.id.projects_project_layout);

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
    protected void onStart() {
        super.onStart();

        int spinnerSize = addYearsInSpinner();
        if (spinnerSize > 0) {
            selectProjectsOfSelectedYear();
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
        DAO.open();

        ListYears listYears = DAO.selectYears();

        DAO.close();

        SpinnerAdapter adapter = new SpinnerAdapter(this, listYears);
        this.spinnerYears.setAdapter(adapter.getAdapter());

        return listYears.size();
    }

    private void selectProjectsOfSelectedYear() {
        String selectedYear = (String) this.spinnerYears.getSelectedItem();
        Year year = new Year(Long.parseLong(selectedYear));

        DAO.open();

        ListProjects listProjects = DAO.selectProjectsOfYear(year);

        DAO.close();
    }

    private void insert() {
        Project project = validForm();

        if (project != null) {
            DAO.open();

            boolean inserted = DAO.insertProject(project, new Year());

            DAO.close();

            if(!inserted) {
                CustomDialog.showDialog(this,
                        "Error",
                        "Project is not created",
                        CustomDialogBuilder.TYPE_ONEBUTTON_OK,
                        null);
            } else {
                CustomDialog.showDialog(this,
                        "New",
                        "Project is created",
                        CustomDialogBuilder.TYPE_ONEBUTTON_OK,
                        null);
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
        recyclerViewProjects.setVisibility(View.VISIBLE);
        layoutProject.setVisibility(View.GONE);

        selectProjectsOfSelectedYear();
    }

    private void switchToNew() {
        layoutProject.setVisibility(View.VISIBLE);
        recyclerViewProjects.setVisibility(View.GONE);

        prepareFormForNewProject();
    }

    private void prepareFormForNewProject() {
        this.formProject.textViewYear.setText(new Year().toString());
        this.formProject.textViewId.setText("(Auto generated)");
        this.formProject.editTextTitle.setText("", EditText.BufferType.EDITABLE);
        this.formProject.editTextDescription.setText("", EditText.BufferType.EDITABLE);
        this.formProject.radioButtonL1.setChecked(true);
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
