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

import com.example.proadmin.R;

import proadmin.constant.Extra;
import proadmin.content.Teacher;
import proadmin.db.DAO;
import proadmin.gui.widget.CustomDialog;
import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.tool.form.FormUserValidator;
import proadmin.session.Session;

public class SignUpActivity extends ActionBarActivity {

    private static final int SIGNUP_TIME_OUT = 2000;

    private class ViewHolder {
        public EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
        public CheckBox validCheckBox;
    }

    private ViewHolder form;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        //Set User form
        this.form = new ViewHolder();

        this.form.firstNameEditText = (EditText) findViewById(R.id.form_user_edittext_firstname);
        this.form.lastNameEditText = (EditText) findViewById(R.id.form_user_edittext_lastname);
        this.form.emailEditText = (EditText) findViewById(R.id.form_user_edittext_email);
        this.form.passwordEditText = (EditText) findViewById(R.id.form_user_edittext_password);
        this.form.confirmPasswordEditText = (EditText) findViewById(R.id.form_user_edittext_confirmpassword);
        this.form.validCheckBox = (CheckBox) findViewById(R.id.signup_form_user_checkbox_valid);

        Button buttonSave = (Button) findViewById(R.id.signup_button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
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

    public void signUp() {
        Teacher teacher = validForm();

        if (teacher != null) {
            DAO.open(this);

            boolean signed = DAO.insertTeacher(teacher);

            DAO.close();

            if(!signed) {
                CustomDialog.showDialog(this,
                        R.string.signup_alertdialog_signup_title_error,
                        R.string.signup_alertdialog_signup_message_error,
                        CustomDialogBuilder.TYPE_ONEBUTTON_OK,
                        null);
            } else {
                goToHome(teacher);
            }
        }
    }

    private Teacher validForm() {
        Teacher teacher = null;

        String firstName = this.form.firstNameEditText.getEditableText().toString().trim();
        String lastName = this.form.lastNameEditText.getEditableText().toString().trim();
        String email = this.form.emailEditText.getEditableText().toString().trim();
        String password = this.form.passwordEditText.getEditableText().toString().trim();
        String confirmPassword = this.form.confirmPasswordEditText.getEditableText().toString().trim();
        Boolean validCheckBox = this.form.validCheckBox.isChecked();

        String message = FormUserValidator.validForm(firstName, lastName, email, password, confirmPassword, validCheckBox);

        if (message != null ) {
            CustomDialog.showDialog(this, "Erreur formulaire", message, CustomDialogBuilder.TYPE_ONEBUTTON_OK, null);
        } else {
            teacher = new Teacher(firstName, lastName, email, password);
        }

        return teacher;
    }

    private void goToHome(Teacher teacher) {
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