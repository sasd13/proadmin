package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.javaex.db.EntityDAO;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.db.DAO;
import com.sasd13.proadmin.db.SQLiteDAO;
import com.sasd13.proadmin.session.Session;

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

        DAO dao = SQLiteDAO.getInstance();

        dao.open();

        EntityDAO entityDAO = dao.getEntityDAO(Teacher.class);
        Teacher teacher = (Teacher) entityDAO.select(Session.getTeacherId());

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

        if (true) {
            tryToPerformUpdateTeacher();
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

    private void tryToPerformUpdateTeacher() {
        DAO dao = SQLiteDAO.getInstance();

        dao.open();

        EntityDAO entityDAO = dao.getEntityDAO(Teacher.class);
        Teacher teacher = (Teacher) entityDAO.select(Session.getTeacherId());

        performUpdateTeacher(teacher, entityDAO);

        dao.close();
    }

    private void performUpdateTeacher(Teacher teacher, EntityDAO entityDAO) {
        editTeacherWithForm(teacher);

        entityDAO.update(teacher);

        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
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