package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidx.form.FormValidator;
import com.sasd13.androidx.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.db.DAOFactory;
import com.sasd13.proadmin.session.Session;
import proadminlib.bean.member.Teacher;
import proadminlib.db.DAO;

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

    private void createFormTeacher() {
        this.formTeacher = new FormTeacherViewHolder();

        this.formTeacher.textViewNumber = (TextView) findViewById(R.id.setting_form_user_textview_number);
        this.formTeacher.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        this.formTeacher.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        this.formTeacher.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DAO dao = DAOFactory.make();

        dao.open();
        Teacher teacher = dao.selectTeacher(Session.getTeacherId());
        dao.close();

        fillFormTeacher(teacher);
    }

    private void fillFormTeacher(Teacher teacher) {
        this.formTeacher.textViewNumber.setText(teacher.getNumber());
        this.formTeacher.editTextFirstName.setText(teacher.getFirstName(), TextView.BufferType.EDITABLE);
        this.formTeacher.editTextLastName.setText(teacher.getLastName(), TextView.BufferType.EDITABLE);
        this.formTeacher.editTextEmail.setText(teacher.getEmail(), TextView.BufferType.EDITABLE);
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

    private void updateTeacher() {
        String[] tabFormErrors = validFormTeacher();

        if (tabFormErrors.length == 0) {
            tryToPerformUpdateTeacher();
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
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

    private void tryToPerformUpdateTeacher() {
        String email = this.formTeacher.editTextEmail.getText().toString().trim();

        DAO dao = DAOFactory.make();

        dao.open();
        Teacher teacher = dao.selectTeacherByEmail(email);

        if (teacher == null) {
            teacher = dao.selectTeacher(Session.getTeacherId());

            performUpdateTeacher(teacher, dao);
        } else {
            if (teacher.getId() == Session.getTeacherId()) {
                performUpdateTeacher(teacher, dao);
            } else {
                CustomDialog.showOkDialog(this, "Error update", "Email (" + email + ") already exists");
            }
        }

        dao.close();
    }

    private void performUpdateTeacher(Teacher teacher, DAO dao) {
        editTeacherWithForm(teacher);

        dao.updateTeacher(teacher);

        Toast.makeText(this, R.string.toast_saved, Toast.LENGTH_SHORT).show();
    }

    private void editTeacherWithForm(Teacher teacher) {
        String firstName = this.formTeacher.editTextFirstName.getText().toString().trim();
        String lastName = this.formTeacher.editTextLastName.getText().toString().trim();
        String email = this.formTeacher.editTextEmail.getText().toString().trim();

        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setEmail(email);
    }
}