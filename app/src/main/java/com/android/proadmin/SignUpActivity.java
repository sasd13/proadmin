package com.android.proadmin;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proadmin.R;

import proadmin.content.Teacher;
import proadmin.db.DAO;
import proadmin.gui.widget.CustomDialogBuilder;
import proadmin.tool.FormValidator;
import proadmin.tool.Session;

public class SignUpActivity extends ActionBarActivity {

    private static final int SIGNUP_TIME_OUT = 2000;
    private Handler handler;
    private Runnable runnable;

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
        DAO.open(this);

        String firstName = this.form.firstNameEditText.getEditableText().toString().trim();
        String lastName = this.form.lastNameEditText.getEditableText().toString().trim();
        String email = this.form.emailEditText.getEditableText().toString().trim();
        String password = this.form.passwordEditText.getEditableText().toString().trim();
        String confirmPassword = this.form.confirmPasswordEditText.getEditableText().toString().trim();
        Boolean validCheckBox = this.form.validCheckBox.isChecked();

        Teacher teacher = new Teacher(firstName, lastName, email, password);

        boolean signed = false;
        boolean valid = FormValidator.validTeacher(teacher);
        if(valid && confirmPassword.compareTo(password) == 0 && validCheckBox) {
            signed = DAO.insertTeacher(teacher);
        }

        DAO.close();

        if(!signed) {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_ONEBUTTON_OK);
            builder.setTitle(R.string.signup_alertdialog_signup_title_error)
                    .setMessage(R.string.signup_alertdialog_signup_message_error)
                    .setNeutralButton(null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
            final AlertDialog dialog = builder.create();

            final Intent intent = new Intent(this, LogInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("CLOSE", true);
            intent.putExtra("NEW_USER_FIRSTNAME", teacher.getFirstName());

            this.runnable = new Runnable() {

                @Override
                public void run() {
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            };

            this.handler = new Handler();
            this.handler.postDelayed(this.runnable, SIGNUP_TIME_OUT);

            dialog.show();
            Session.logIn(teacher.getEmail(), teacher.getPassword());
        }
    }
}