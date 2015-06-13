package com.android.proadmin;

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
import android.widget.TextView;

import proadmin.constant.Extra;
import proadmin.content.Id;
import proadmin.content.Teacher;
import proadmin.db.DataManager;
import proadmin.db.accessor.DataAccessor;
import proadmin.form.FormUserValidator;
import proadmin.gui.app.KeyboardManager;
import proadmin.gui.widget.CustomDialog;
import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.session.Session;

public class SettingsActivity extends ActionBarActivity {

    private class ViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private ViewHolder formUser;

    private DataAccessor dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        this.formUser = new ViewHolder();

        this.formUser.editTextFirstName = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.formUser.editTextLastName = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.formUser.editTextEmail = (EditText) findViewById(R.id.form_user_edittext_email);

        EditText.OnEditorActionListener listener = new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    KeyboardManager.hide(v);

                    update();

                    return true;
                }

                return false;
            }
        };

        this.formUser.editTextFirstName.setOnEditorActionListener(listener);
        this.formUser.editTextLastName.setOnEditorActionListener(listener);
        this.formUser.editTextEmail.setOnEditorActionListener(listener);

        Button buttonLogout = (Button) findViewById(R.id.settings_button_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.dao = DataManager.getDao();

        loadTeacher();
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

    public void logOut() {
        if (Session.logOut()) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(Extra.EXIT, true);

            startActivity(intent);
            finish();
        } else {
            CustomDialog.showDialog(
                    this,
                    "Error logout",
                    "You have not been logged out",
                    CustomDialogBuilder.TYPE_ONEBUTTON_OK,
                    null
            );
        }
    }

    public void loadTeacher() {
        String teacherId = Session.getSessionId();

        this.dao.open();

        Teacher teacher = this.dao.selectTeacher(new Id(teacherId));

        this.dao.close();

        this.formUser.editTextFirstName.setText(teacher.getFirstName(), EditText.BufferType.EDITABLE);
        this.formUser.editTextLastName.setText(teacher.getLastName(), EditText.BufferType.EDITABLE);
        this.formUser.editTextEmail.setText(teacher.getEmail(), EditText.BufferType.EDITABLE);
    }

    public void update() {
        Teacher teacher1 = validForm();

        if (teacher1 != null) {
            this.dao.open();

            Teacher teacher2 = this.dao.selectTeacher(new Id(Session.getSessionId()));

            teacher2.setFirstName(teacher1.getFirstName());
            teacher2.setLastName(teacher1.getLastName());
            teacher2.setEmail(teacher1.getEmail());

            this.dao.updateTeacher(teacher2);

            this.dao.close();
        }
    }

    private Teacher validForm() {
        Teacher teacher = null;

        String firstName = this.formUser.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formUser.editTextLastName.getEditableText().toString().trim();
        String email = this.formUser.editTextEmail.getEditableText().toString().trim();

        String message = FormUserValidator.validForm(firstName, lastName, email);

        if (message != null ) {
            CustomDialog.showDialog(this, "Erreur formulaire", message, CustomDialogBuilder.TYPE_ONEBUTTON_OK, null);
        } else {
            teacher = new Teacher();
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setEmail(email);
        }

        return teacher;
    }
}
