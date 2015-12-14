package com.sasd13.proadmin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sasd13.wsprovider.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.db.DAO;
import com.sasd13.proadmin.db.DAOFactory;
import com.sasd13.proadmin.form.FormValidator;
import com.sasd13.proadmin.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.gui.widget.dialog.CustomDialogBuilder;
import com.sasd13.proadmin.session.Session;

public class SignActivity extends ActionBarActivity {

    private class FormTeacherViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail, editTextNumber, editTextPassword, editTextConfirmPassword;
        public CheckBox checkBoxValidTerms;
    }

    private static final int SIGNUP_TIMEOUT = 2000;

    private FormTeacherViewHolder formTeacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);

        createFormTeacher();
    }

    private void createFormTeacher() {
        this.formTeacher = new FormTeacherViewHolder();

        this.formTeacher.editTextFirstName = (EditText) findViewById(R.id.sign_form_user_edittext_firstname);
        this.formTeacher.editTextLastName = (EditText) findViewById(R.id.sign_form_user_edittext_lastname);
        this.formTeacher.editTextEmail = (EditText) findViewById(R.id.sign_form_user_edittext_email);
        this.formTeacher.editTextNumber = (EditText) findViewById(R.id.sign_form_user_edittext_number);
        this.formTeacher.editTextPassword = (EditText) findViewById(R.id.sign_form_user_edittext_password);
        this.formTeacher.editTextConfirmPassword = (EditText) findViewById(R.id.sign_form_user_edittext_confirmpassword);
        this.formTeacher.checkBoxValidTerms = (CheckBox) findViewById(R.id.sign_form_user_checkbox_terms);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sign, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_action_accept:
                signUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void signUp() {
        String[] tabFormErrors = validFormTeacher();

        if (tabFormErrors.length == 0) {
            tryToPerformSignUp();
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormTeacher() {
        FormValidator formValidator = new FormValidator();

        String firstName = this.formTeacher.editTextFirstName.getText().toString().trim();
        String lastName = this.formTeacher.editTextLastName.getText().toString().trim();
        String email = this.formTeacher.editTextEmail.getText().toString().trim();
        String number = this.formTeacher.editTextNumber.getText().toString().trim();
        String password = this.formTeacher.editTextPassword.getText().toString().trim();
        String confirmPassword = this.formTeacher.editTextConfirmPassword.getText().toString().trim();
        Boolean validTerms = this.formTeacher.checkBoxValidTerms.isChecked();

        formValidator.validName(firstName, "firstname");
        formValidator.validName(lastName, "lastname");
        formValidator.validEmail(email, "email");
        formValidator.validText(number, "number");
        formValidator.validPassword(password, "password");
        formValidator.validConfirmPassword(password, confirmPassword, "confirmpassword");
        formValidator.validCheckBox(validTerms, "terms");

        return formValidator.getErrors();
    }

    private void tryToPerformSignUp() {
        Teacher teacher = getTeacherFromForm();

        DAO dao = DAOFactory.make();

        if (!dao.containsTeacherByNumber(teacher.getNumber())) {
            if (!dao.containsTeacherByEmail(teacher.getEmail())) {
                performSignUp(teacher, dao);

                Session.logIn(teacher.getNumber(), teacher.getPassword());

                goToHomeActivityWithWelcome();
            } else {
                CustomDialog.showOkDialog(this, "Error sign", "Email (" + teacher.getNumber() + ") already exists");
            }
        } else {
            CustomDialog.showOkDialog(this, "Error sign", "Number (" + teacher.getNumber() + ") already exists");
        }
    }

    private Teacher getTeacherFromForm() {
        Teacher teacher = new Teacher();

        String firstName = this.formTeacher.editTextFirstName.getText().toString().trim();
        String lastName = this.formTeacher.editTextLastName.getText().toString().trim();
        String email = this.formTeacher.editTextEmail.getText().toString().trim();
        String number = this.formTeacher.editTextNumber.getText().toString().trim();
        String password = this.formTeacher.editTextPassword.getText().toString().trim();

        teacher.setNumber(number);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setEmail(email);
        teacher.setPassword(password);

        return teacher;
    }

    private void performSignUp(Teacher teacher, DAO dao) {
        dao.insertTeacher(teacher);
    }

    private void goToHomeActivityWithWelcome() {
        CustomDialogBuilder builder = new CustomDialogBuilder(this, CustomDialogBuilder.TYPE_LOAD);
        final AlertDialog dialog = builder.create();

        final Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.WELCOME, true);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                dialog.dismiss();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, SIGNUP_TIMEOUT);

        dialog.show();
    }
}