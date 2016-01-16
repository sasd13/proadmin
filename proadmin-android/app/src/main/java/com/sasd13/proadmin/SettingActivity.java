package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.core.bean.member.Teacher;

public class SettingActivity extends MotherActivity {

    private class FormTeacherViewHolder {
        public TextView textViewNumber;
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private FormTeacherViewHolder formTeacher;

    private Teacher teacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        createFormTeacher();
    }

    private void createFormTeacher() {
        formTeacher = new FormTeacherViewHolder();

        formTeacher.textViewNumber = (TextView) findViewById(R.id.setting_form_user_textview_number);
        formTeacher.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        formTeacher.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        formTeacher.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //teacher = (Teacher) entityDAO.select(Session.getId());

        fillFormTeacher(teacher);
    }

    private void fillFormTeacher(Teacher teacher) {
        formTeacher.textViewNumber.setText(teacher.getNumber());
        formTeacher.editTextFirstName.setText(teacher.getFirstName(), TextView.BufferType.EDITABLE);
        formTeacher.editTextLastName.setText(teacher.getLastName(), TextView.BufferType.EDITABLE);
        formTeacher.editTextEmail.setText(teacher.getEmail(), TextView.BufferType.EDITABLE);
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

        if (true) {
            performUpdateTeacher();
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    tabFormErrors[0]);
        }
    }

    private String[] validFormTeacher() {
        //TODO

        return null;
    }

    private void performUpdateTeacher() {
        editTeacherWithForm(teacher);
        //entityDAO.update(teacher);
        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    private void editTeacherWithForm(Teacher teacher) {
        teacher.setFirstName(formTeacher.editTextFirstName.getText().toString().trim());
        teacher.setLastName(formTeacher.editTextLastName.getText().toString().trim());
        teacher.setEmail(formTeacher.editTextEmail.getText().toString().trim());
    }
}