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
import proadmin.content.Teacher;
import proadmin.content.id.TeacherId;
import proadmin.pattern.dao.DataManager;
import proadmin.form.FormException;
import proadmin.form.FormUserValidator;
import proadmin.gui.app.KeyboardManager;
import proadmin.gui.widget.CustomDialog;
import proadmin.pattern.dao.accessor.DataAccessor;
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
            CustomDialog.showOkDialog(this, "Error logout", "You have not been logged out");
        }
    }

    public void loadTeacher() {
        this.dao.open();

        Teacher teacher = this.dao.selectTeacher(new TeacherId(Session.getSessionId()));

        this.dao.close();

        this.formUser.editTextFirstName.setText(teacher.getFirstName(), EditText.BufferType.EDITABLE);
        this.formUser.editTextLastName.setText(teacher.getLastName(), EditText.BufferType.EDITABLE);
        this.formUser.editTextEmail.setText(teacher.getEmail(), EditText.BufferType.EDITABLE);
    }

    public void update() {
        try {
            Teacher teacher1 = validForm();

            this.dao.open();

            Teacher teacher2 = this.dao.selectTeacher(teacher1.getId());
            teacher1.setPassword(teacher2.getPassword());

            this.dao.updateTeacher(teacher1);
            this.dao.close();
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private Teacher validForm() throws FormException {
        String firstName = this.formUser.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formUser.editTextLastName.getEditableText().toString().trim();
        String email = this.formUser.editTextEmail.getEditableText().toString().trim();

        FormUserValidator.validForm(firstName, lastName, email);

        return new Teacher(new TeacherId(Session.getSessionId()), firstName, lastName, email);
    }
}
