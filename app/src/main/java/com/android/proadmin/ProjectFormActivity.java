package com.android.proadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import proadmin.constant.Extra;
import proadmin.beans.AcademicLevel;
import proadmin.beans.Project;
import proadmin.content.Year;
import proadmin.data.dao.DataAccessorManager;
import proadmin.data.dao.accessor.DataAccessor;
import proadmin.gui.app.KeyboardManager;
import proadmin.gui.color.ColorOnTouchListener;
import proadmin.form.FormException;
import proadmin.form.FormProjectValidator;
import proadmin.gui.widget.dialog.CustomDialog;
import proadmin.util.IdGenerator;

public class ProjectFormActivity extends ActionBarActivity {

    private class ViewHolder {
        public TextView textViewYear, textViewYearCreation, textViewId;
        public EditText editTextTitle, editTextDescription;
        public RadioGroup radioGroupGrade;
        public RadioButton radioButtonL1, radioButtonL2, radioButtonL3, radioButtonM1, radioButtonM2;
        public Button buttonSave, buttonMigrate, buttonRemove, buttonRemoveAll;
    }

    private ViewHolder formProject;

    private int mode = Extra.MODE_NEW;

    private DataAccessor dao = DataAccessorManager.getDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project_form);

        this.formProject = new ViewHolder();

        this.formProject.textViewYear = (TextView) findViewById(R.id.form_project_textview_project_year);
        this.formProject.textViewYearCreation = (TextView) findViewById(R.id.form_project_textview_project_year_creation);
        this.formProject.textViewId = (TextView) findViewById(R.id.form_project_textview_project_id);

        this.formProject.editTextTitle = (EditText) findViewById(R.id.form_project_edittext_project_title);
        this.formProject.editTextDescription = (EditText) findViewById(R.id.form_project_textview_project_description);

        TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    KeyboardManager.hide(v);

                    if (mode == Extra.MODE_CONSULT) {
                        updateProject();
                    }

                    return true;
                }

                return false;
            }
        };

        this.formProject.editTextTitle.setOnEditorActionListener(editorActionListener);
        this.formProject.editTextDescription.setOnEditorActionListener(editorActionListener);

        this.formProject.radioGroupGrade = (RadioGroup) findViewById(R.id.form_project_radiogroup_project_grade);
        this.formProject.radioGroupGrade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mode == Extra.MODE_CONSULT) {
                    updateProject();
                }
            }
        });

        this.formProject.radioButtonL1 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_l1);
        this.formProject.radioButtonL2 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_l2);
        this.formProject.radioButtonL3 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_l3);
        this.formProject.radioButtonM1 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_m1);
        this.formProject.radioButtonM2 = (RadioButton) findViewById(R.id.form_project_radiobutton_project_grade_m2);

        this.formProject.buttonSave = (Button) findViewById(R.id.form_project_button_save);
        this.formProject.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProject();
            }
        });

        this.formProject.buttonMigrate = (Button) findViewById(R.id.form_project_button_migrate);
        this.formProject.buttonMigrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.showYesNoDialog(
                        ProjectFormActivity.this,
                        "Project",
                        "Confirm migration for actual year ?",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                migrateProjectToActualYear();
                            }
                        });
            }
        });

        this.formProject.buttonRemove = (Button) findViewById(R.id.form_project_button_remove);
        this.formProject.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.showYesNoDialog(
                        ProjectFormActivity.this,
                        "Project",
                        "Groups and reports of its current year will be deleted. Confirm ?",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeProjectForYear();
                            }
                        });
            }
        });

        this.formProject.buttonRemoveAll = (Button) findViewById(R.id.form_project_button_remove_all);
        this.formProject.buttonRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.showYesNoDialog(
                        ProjectFormActivity.this,
                        "Project",
                        "Groups and reports of all years will be deleted. Confirm ?",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeProjectForAllYears();
                            }
                        });
            }
        });

        ColorOnTouchListener colorOnTouchListener = new ColorOnTouchListener(getResources().getColor(R.color.customOrange));

        this.formProject.buttonSave.setOnTouchListener(colorOnTouchListener);
        this.formProject.buttonMigrate.setOnTouchListener(colorOnTouchListener);
        this.formProject.buttonRemove.setOnTouchListener(colorOnTouchListener);
        this.formProject.buttonRemoveAll.setOnTouchListener(colorOnTouchListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.mode = getIntent().getIntExtra(Extra.MODE, 0);
        switch (this.mode) {
            case Extra.MODE_NEW:
                prepareFormForNewProject();
                break;
            case Extra.MODE_CONSULT:
                prepareFormForConsultProject();
                break;
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

    private void updateProject() {
        try {
            Project project = getProjectFromFormProject();

            this.dao.open();
            this.dao.updateProject(project);
            this.dao.close();
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private Project getProjectFromFormProject() throws FormException {
        Project project;

        String title = this.formProject.editTextTitle.getEditableText().toString().trim();
        String description = this.formProject.editTextDescription.getEditableText().toString().trim();

        FormProjectValidator.validForm(title, description);

        AcademicLevel academicLevel;

        int checkedRadioButtonId = this.formProject.radioGroupGrade.getCheckedRadioButtonId();
        if (checkedRadioButtonId == this.formProject.radioButtonM2.getId()) {
            academicLevel = AcademicLevel.M2;
        } else if (checkedRadioButtonId == this.formProject.radioButtonM1.getId()) {
            academicLevel = AcademicLevel.M1;
        } else if (checkedRadioButtonId == this.formProject.radioButtonL3.getId()) {
            academicLevel = AcademicLevel.L3;
        } else if (checkedRadioButtonId == this.formProject.radioButtonL2.getId()) {
            academicLevel = AcademicLevel.L2;
        } else {
            academicLevel = AcademicLevel.L1;
        }

        String projectId = this.formProject.textViewId.getText().toString().trim();
        if (this.mode == Extra.MODE_NEW) {
            projectId = IdGenerator.get(this, IdGenerator.TYPE_PROJECT);
        }

        project = new Project();
        project.setId(projectId);
        project.setTitle(title);
        project.setAcademicLevel(academicLevel);
        project.setDescription(description);

        return project;
    }

    private void addProject() {
        try {
            Project project = getProjectFromFormProject();

            this.dao.open();
            this.dao.insertProject(project, new Year());
            this.dao.close();

            goToProjectsActivity();
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private void migrateProjectToActualYear() {
        String projectId = getIntent().getStringExtra(Extra.PROJECT_ID);

        this.dao.open();
        Project project = this.dao.selectProject(projectId);

        try {
            this.dao.insertProject(project, new Year());

            goToProjectsActivity();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            this.dao.close();
        }
    }

    private void goToProjectsActivity() {
        Intent intent = new Intent(this, ProjectsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    private void prepareFormForNewProject() {
        this.formProject.textViewYear.setText(new Year().toString());
        this.formProject.textViewYearCreation.setText(new Year().toString());
        this.formProject.radioButtonL1.setChecked(true);

        this.formProject.buttonSave.setVisibility(View.VISIBLE);
        this.formProject.buttonMigrate.setVisibility(View.INVISIBLE);
        this.formProject.buttonRemove.setVisibility(View.INVISIBLE);
        this.formProject.buttonRemoveAll.setVisibility(View.INVISIBLE);
    }

    private void prepareFormForConsultProject() {
        String stringYear = getIntent().getStringExtra(Extra.YEAR);
        String projectId = getIntent().getStringExtra(Extra.PROJECT_ID);

        this.dao.open();

        try {
            Project project = this.dao.selectProject(projectId);
            ListYears listYearsOfProject = this.dao.selectYearsOfProjectByDesc(project.getId());
            Year yearCreation = listYearsOfProject.get(listYearsOfProject.size() - 1);

            this.formProject.textViewYear.setText(stringYear);
            this.formProject.textViewYearCreation.setText(yearCreation.toString());
            this.formProject.textViewId.setText(project.getId());
            this.formProject.editTextTitle.setText(project.getTitle(), TextView.BufferType.EDITABLE);
            this.formProject.editTextDescription.setText(project.getDescription(), TextView.BufferType.EDITABLE);

            switch (project.getAcademicLevel()) {
                case L1:
                    this.formProject.radioButtonL1.setChecked(true);
                    break;
                case L2:
                    this.formProject.radioButtonL2.setChecked(true);
                    break;
                case L3:
                    this.formProject.radioButtonL3.setChecked(true);
                    break;
                case M1:
                    this.formProject.radioButtonM1.setChecked(true);
                    break;
                case M2:
                    this.formProject.radioButtonM2.setChecked(true);
                    break;
            }

            if (!listYearsOfProject.contains(new Year())) {
                this.formProject.buttonMigrate.setVisibility(View.VISIBLE);
            } else {
                this.formProject.buttonMigrate.setVisibility(View.INVISIBLE);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            this.dao.close();
        }

        this.formProject.buttonSave.setVisibility(View.INVISIBLE);
        this.formProject.buttonRemove.setVisibility(View.VISIBLE);
        this.formProject.buttonRemoveAll.setVisibility(View.VISIBLE);
    }

    private void removeProjectForYear() {
        String stringYear = getIntent().getStringExtra(Extra.YEAR);
        String projectId = getIntent().getStringExtra(Extra.PROJECT_ID);

        this.dao.open();
        this.dao.deleteProjectFromYear(projectId, new Year(Long.parseLong(stringYear)));
        this.dao.close();

        goToProjectsActivity();
    }

    private void removeProjectForAllYears() {
        String projectId = getIntent().getStringExtra(Extra.PROJECT_ID);

        this.dao.open();
        this.dao.deleteProject(projectId);
        this.dao.close();

        goToProjectsActivity();
    }
}
