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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import proadmin.constant.Extra;
import proadmin.beans.Project;
import proadmin.beans.Team;
import proadmin.beans.Teacher;
import proadmin.content.Year;
import proadmin.data.dao.DataAccessorManager;
import proadmin.data.dao.accessor.DataAccessor;
import proadmin.gui.app.KeyboardManager;
import proadmin.gui.color.ColorOnTouchListener;
import proadmin.gui.widget.dialog.CustomDialog;
import proadmin.gui.widget.spin.Spin;
import proadmin.session.Session;
import proadmin.util.IdGenerator;

public class SquadFormActivity extends ActionBarActivity {

    private class ViewHolder {
        public TextView textViewYear, textViewId;
        public Spinner spinnerProject;
        public EditText editTextName;
        public Button buttonSave, buttonRemove, buttonStudents;
    }

    private ViewHolder formSquad;

    private int mode = Extra.MODE_NEW;

    private DataAccessor dao = DataAccessorManager.getDao();
    private Spin spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_squad_form);

        this.formSquad = new ViewHolder();

        this.formSquad.textViewYear = (TextView) findViewById(R.id.form_squad_textview_squad_year);
        this.formSquad.textViewId = (TextView) findViewById(R.id.form_squad_textview_squad_id);

        this.spin = new Spin(this);

        this.formSquad.spinnerProject = (Spinner) findViewById(R.id.form_squad_spinner_project);
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mode == Extra.MODE_CONSULT) {
                    updateSquad();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        this.spin.adapt(this.formSquad.spinnerProject, onItemSelectedListener);

        this.formSquad.editTextName = (EditText) findViewById(R.id.form_squad_edittext_squad_name);

        TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    KeyboardManager.hide(v);

                    if (mode == Extra.MODE_CONSULT) {
                        updateSquad();
                    }

                    return true;
                }

                return false;
            }
        };

        this.formSquad.editTextName.setOnEditorActionListener(editorActionListener);

        this.formSquad.buttonSave = (Button) findViewById(R.id.form_squad_button_save);
        this.formSquad.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSquad();
            }
        });

        this.formSquad.buttonRemove = (Button) findViewById(R.id.form_squad_button_remove);
        this.formSquad.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.showYesNoDialog(
                        SquadFormActivity.this,
                        "Squad",
                        "Groups and reports of its current year will be deleted. Confirm ?",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeSquad();
                            }
                        });
            }
        });

        this.formSquad.buttonStudents = (Button) findViewById(R.id.form_squad_button_students);
        this.formSquad.buttonStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SquadFormActivity.this, StudentsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });

        ColorOnTouchListener colorOnTouchListener = new ColorOnTouchListener(getResources().getColor(R.color.customGreen));

        this.formSquad.buttonSave.setOnTouchListener(colorOnTouchListener);
        this.formSquad.buttonRemove.setOnTouchListener(colorOnTouchListener);
        this.formSquad.buttonStudents.setOnTouchListener(colorOnTouchListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.mode = getIntent().getIntExtra(Extra.MODE, 0);
        switch (this.mode) {
            case Extra.MODE_NEW:
                prepareFormForNewSquad();
                break;
            case Extra.MODE_CONSULT:
                prepareFormForConsultSquad();
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

    private void updateSquad() {
        try {
            Team team = getSquadFromFormSquad();

            this.dao.open();

            Teacher teacher = this.dao.selectTeacher(Session.getSessionId());
            team.setTeacher(teacher);

            this.dao.updateSquad(team);
            this.dao.close();
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private void addSquad() {
        try {
            Team team = getSquadFromFormSquad();

            this.dao.open();

            Teacher teacher = this.dao.selectTeacher(Session.getSessionId());
            team.setTeacher(teacher);

            this.dao.insertSquad(team);
            this.dao.close();

            goToSquadsActivity();
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private Team getSquadFromFormSquad() throws FormException {
        Team team;

        String name = this.formSquad.editTextName.getEditableText().toString().trim();

        FormSquadValidator.validForm(name);

        String squadId = this.formSquad.textViewId.getText().toString().trim();
        if (this.mode == Extra.MODE_NEW) {
            squadId = IdGenerator.get(this, IdGenerator.TYPE_SQUAD);
        }

        String projectId = this.spin.getSelectedItem();

        this.dao.open();
        Project project = this.dao.selectProject(projectId);
        this.dao.close();

        team = new Team();
        team.setId(squadId);
        team.setName(name);
        team.setYear(new Year());
        team.setProject(project);

        return team;
    }

    private void goToSquadsActivity() {
        Intent intent = new Intent(this, SquadsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    private void prepareFormForNewSquad() {
        this.formSquad.textViewYear.setText(new Year().toString());

        addProjectsIdsInSpinnerProjects();

        this.formSquad.buttonSave.setVisibility(View.VISIBLE);
        this.formSquad.buttonRemove.setVisibility(View.INVISIBLE);
        this.formSquad.buttonStudents.setVisibility(View.INVISIBLE);
    }

    private void addProjectsIdsInSpinnerProjects() {

    }

    private void prepareFormForConsultSquad() {
        String stringYear = getIntent().getStringExtra(Extra.YEAR);
        String squadId = getIntent().getStringExtra(Extra.SQUAD_ID);

        this.dao.open();

        try {
            Team team = this.dao.selectSquad(squadId);

            this.formSquad.textViewYear.setText(stringYear);
            this.formSquad.textViewId.setText(team.getId());
            this.formSquad.editTextName.setText(team.getName(), TextView.BufferType.EDITABLE);

            addProjectsIdsInSpinnerProjects();
            selectProjectIdForSquadInSpinnerProjects(team.getProject());
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            this.dao.close();
        }

        this.formSquad.buttonSave.setVisibility(View.INVISIBLE);
        this.formSquad.buttonRemove.setVisibility(View.VISIBLE);
        this.formSquad.buttonStudents.setVisibility(View.VISIBLE);
    }

    private void selectProjectIdForSquadInSpinnerProjects(Project project) {
        this.spin.setSelectedItem(project.getId());
    }

    private void removeSquad() {
        String squadId = getIntent().getStringExtra(Extra.SQUAD_ID);

        this.dao.open();
        this.dao.deleteSquad(squadId);
        this.dao.close();

        goToSquadsActivity();
    }
}
