package com.android.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import proadmin.bean.member.Teacher;
import proadmin.db.DAO;
import proadmin.db.DAOFactory;
import proadmin.form.FormValidator;
import proadmin.gui.widget.dialog.CustomDialog;
import proadmin.session.Session;

public class SettingActivity extends MotherActivity {

    private class FormTeacherViewHolder {
        public TextView textViewNumber;
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private FormTeacherViewHolder formTeacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        createFormTeacher();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Teacher teacher = DAOFactory.get().selectTeacher(Session.getTeacherId());

        fillFormTeacher(teacher);
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
                updateTeacher();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createFormTeacher() {
        this.formTeacher = new FormTeacherViewHolder();

        this.formTeacher.textViewNumber = (TextView) findViewById(R.id.setting_form_user_textview_number);
        this.formTeacher.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        this.formTeacher.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        this.formTeacher.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    private void fillFormTeacher(Teacher teacher) {
        this.formTeacher.textViewNumber.setText(teacher.getNumber());
        this.formTeacher.editTextFirstName.setText(teacher.getFirstName(), TextView.BufferType.EDITABLE);
        this.formTeacher.editTextLastName.setText(teacher.getLastName(), TextView.BufferType.EDITABLE);
        this.formTeacher.editTextEmail.setText(teacher.getEmail(), TextView.BufferType.EDITABLE);
    }

    private void updateTeacher() {
        String[] tabFormErrors = validFormTeacher();

        if (tabFormErrors.length == 0) {
            String email = this.formTeacher.editTextEmail.getText().toString().trim();

            DAO dao = DAOFactory.get();

            if (!dao.containsTeacherByEmail(email)) {
                Teacher teacher = dao.selectTeacher(Session.getTeacherId());

                performUpdate(teacher, dao);
            } else {
                Teacher teacher = dao.selectTeacherByEmail(email);

                if (teacher.getId() == Session.getTeacherId()) {
                    performUpdate(teacher, dao);
                } else {
                    CustomDialog.showOkDialog(this, "Error update", "Email (" + email + ") already exists");
                }
            }
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private void performUpdate(Teacher teacher, DAO dao) {
        editTeacherWithForm(teacher);
        dao.updateTeacher(teacher);

        Toast.makeText(this, R.string.toast_saved, Toast.LENGTH_SHORT).show();
    }

    private String[] validFormTeacher() {
        FormValidator formValidator = new FormValidator();

        String firstName = this.formTeacher.editTextFirstName.getText().toString().trim();
        String lastName = this.formTeacher.editTextLastName.getText().toString().trim();
        String email = this.formTeacher.editTextEmail.getText().toString().trim();

        formValidator.validName(firstName, "firstname");
        formValidator.validName(lastName, "lastname");
        formValidator.validEmail(email, "email");

        return formValidator.getErrors();
    }

    private void editTeacherWithForm(Teacher teacher) {
        Teacher teacherFromForm = getTeacherFromForm();

        teacher.setFirstName(teacherFromForm.getFirstName());
        teacher.setLastName(teacherFromForm.getLastName());
        teacher.setEmail(teacherFromForm.getEmail());
    }

    private Teacher getTeacherFromForm() {
        Teacher teacher = new Teacher();

        String firstName = this.formTeacher.editTextFirstName.getText().toString().trim();
        String lastName = this.formTeacher.editTextLastName.getText().toString().trim();
        String email = this.formTeacher.editTextEmail.getText().toString().trim();

        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setEmail(email);

        return teacher;
    }
}