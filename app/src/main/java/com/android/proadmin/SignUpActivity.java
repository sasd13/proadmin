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
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValid;
    }

    private ViewHolder formUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        //Set Form user
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
            DAO.open();

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

        String firstName = this.formUser.editTextFirstName.getEditableText().toString().trim();
        String lastName = this.formUser.editTextLastName.getEditableText().toString().trim();
        String email = this.formUser.editTextEmail.getEditableText().toString().trim();
        String password = this.formUser.editTextPassword.getEditableText().toString().trim();
        String confirmPassword = this.formUser.editTextConfirmPassword.getEditableText().toString().trim();
        Boolean checkBoxValid = this.formUser.checkBoxValid.isChecked();

        String message = FormUserValidator.validForm(firstName, lastName, email, password, confirmPassword, checkBoxValid);

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