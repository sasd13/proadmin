package com.android.proadmin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import proadmin.constant.Extra;
import proadmin.content.Teacher;
import proadmin.content.id.TeacherId;
import proadmin.data.dao.DataAccessorManager;
import proadmin.data.dao.accessor.DataAccessor;
import proadmin.gui.color.ColorOnTouchListener;
import proadmin.form.FormException;
import proadmin.form.FormUserValidator;
import proadmin.gui.widget.CustomDialog;
import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.session.Session;

public class SignUpActivity extends ActionBarActivity {

    private static final int SIGNUP_TIME_OUT = 2000;

    private class ViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValid;
    }

    private ViewHolder formUser;

    private DataAccessor dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        this.formUser = new ViewHolder();

        this.formUser.editTextFirstName = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.formUser.editTextLastName = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.formUser.editTextEmail = (EditText) findViewById(R.id.form_user_edittext_email);
        this.formUser.editTextPassword = (EditText) findViewById(R.id.form_user_edittext_password);
        this.formUser.editTextConfirmPassword = (EditText) findViewById(R.id.form_user_edittext_confirmpassword);
        this.formUser.checkBoxValid = (CheckBox) findViewById(R.id.signup_form_user_checkbox_valid);

        Button buttonSave = (Button) findViewById(R.id.signup_button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        buttonSave.setOnTouchListener(new ColorOnTouchListener(getResources().getColor(R.color.customOrange)));
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.dao = DataAccessorManager.getDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    private void signUp() {
        try {
            Teacher teacher = validForm();

            this.dao.open();
            this.dao.insertTeacher(teacher);
            this.dao.close();

            goToHomeActivity(teacher);
        } catch (FormException e) {
            CustomDialog.showOkDialog(this, "Form error", e.getMessage());
        }
    }

    private Teacher validForm() throws FormException {
        Teacher teacher;

        String firstName = this.formUser.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formUser.editTextLastName.getEditableText().toString().trim();
        String email = this.formUser.editTextEmail.getEditableText().toString().trim();
        String password = this.formUser.editTextPassword.getEditableText().toString().trim();
        String confirmPassword = this.formUser.editTextConfirmPassword.getEditableText().toString().trim();
        Boolean checkBoxValid = this.formUser.checkBoxValid.isChecked();

        FormUserValidator.validForm(firstName, lastName, email, password, confirmPassword, checkBoxValid);

        teacher = new Teacher(new TeacherId(), firstName, lastName, email);
        teacher.setPassword(password);

        return teacher;
    }

    private void goToHomeActivity(Teacher teacher) {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.CLOSE, true);
        intent.putExtra(Extra.TEACHER_FIRSTNAME, teacher.getFirstName());

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, SIGNUP_TIME_OUT);

        dialog.show();
        Session.logIn(teacher.getEmail(), teacher.getPassword());
    }
}