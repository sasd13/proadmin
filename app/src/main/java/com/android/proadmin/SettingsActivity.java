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
import proadmin.beans.members.Teacher;
import proadmin.data.dao.DataAccessorManager;
import proadmin.data.dao.accessor.DataAccessor;
import proadmin.gui.color.ColorOnTouchListener;
import proadmin.gui.app.KeyboardManager;
import proadmin.gui.widget.dialog.CustomDialog;
import proadmin.session.Session;

public class SettingsActivity extends ActionBarActivity {

    private class ViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private ViewHolder formUser;

    private DataAccessor dao = DataAccessorManager.getDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        this.formUser = new ViewHolder();

        this.formUser.editTextFirstName = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.formUser.editTextLastName = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.formUser.editTextEmail = (EditText) findViewById(R.id.form_user_edittext_email);

        EditText.OnEditorActionListener editorActionListener = new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    KeyboardManager.hide(v);
                    updateTeacher();

                    return true;
                }

                return false;
            }
        };

        this.formUser.editTextFirstName.setOnEditorActionListener(editorActionListener);
        this.formUser.editTextLastName.setOnEditorActionListener(editorActionListener);
        this.formUser.editTextEmail.setOnEditorActionListener(editorActionListener);

        Button buttonLogout = (Button) findViewById(R.id.settings_button_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        buttonLogout.setOnTouchListener(new ColorOnTouchListener(getResources().getColor(R.color.customBlue)));

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
        loadTeacher();
    }

    private void updateTeacher() {
        try {
            Teacher teacher1 = validForm();

            this.dao.open();

            Teacher teacher2 = this.dao.selectTeacher(teacher1.getId());
            teacher1.setPassword(teacher2.getPassword());

            this.dao.updateTeacher(teacher1);
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            this.dao.close();
        }
    }

    private Teacher validForm() throws FormException {
        Teacher teacher;

        String teacherId = Session.getSessionId();
        String firstName = this.formUser.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formUser.editTextLastName.getEditableText().toString().trim();
        String email = this.formUser.editTextEmail.getEditableText().toString().trim();

        FormUserValidator.validForm(firstName, lastName, email);

        teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setEmail(email);

        return teacher;
    }

    private void logOut() {
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

    private void loadTeacher() {
        this.dao.open();
        Teacher teacher = this.dao.selectTeacher(Session.getSessionId());
        this.dao.close();

        try {
            this.formUser.editTextFirstName.setText(teacher.getFirstName(), EditText.BufferType.EDITABLE);
            this.formUser.editTextLastName.setText(teacher.getLastName(), EditText.BufferType.EDITABLE);
            this.formUser.editTextEmail.setText(teacher.getEmail(), EditText.BufferType.EDITABLE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
