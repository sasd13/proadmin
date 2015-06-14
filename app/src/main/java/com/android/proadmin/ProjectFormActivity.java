package com.android.proadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import proadmin.constant.Extra;
import proadmin.content.Grade;
import proadmin.content.Project;
import proadmin.content.Year;
import proadmin.content.id.ProjectId;
import proadmin.pattern.dao.DataManager;
import proadmin.form.FormException;
import proadmin.form.FormProjectValidator;
import proadmin.gui.widget.CustomDialog;
import proadmin.pattern.dao.accessor.DataAccessor;

public class ProjectFormActivity extends ActionBarActivity {

    private class ViewHolder {
        public TextView textViewYear, textViewId;
        public EditText editTextTitle, editTextDescription;
        public RadioGroup radioGroupGrade;
        public RadioButton radioButtonL1, radioButtonL2, radioButtonL3, radioButtonM1, radioButtonM2;
        public Button buttonSave, buttonRemove, buttonRemoveAll;
    }

    private ViewHolder formProject;

    private DataAccessor dao;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project_form);

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

        if (getIntent().hasExtra(Extra.MODE)) {
            this.mode = getIntent().getIntExtra(Extra.MODE, 0);
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

    private void addProjectForCurrentYear() {
        try {
            Project project = validForm();

            this.dao.open();
            this.dao.insertProject(project, new Year());
            this.dao.close();

            goToProjectsActivity();
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private Project validForm() throws FormException {
        String year = this.formProject.textViewYear.getText().toString().trim();
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

        String id = this.formProject.textViewId.getText().toString().trim();

        ProjectId projectId;
        if (this.mode == Extra.MODE_CONSULT) {
            projectId = new ProjectId(id);
        } else {
            projectId = new ProjectId(grade);
        }

        FormProjectValidator.validForm(title, description);

        return new Project(projectId, title, grade, description);
    }

    private void goToProjectsActivity() {
        Intent intent = new Intent(this, ProjectsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
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
