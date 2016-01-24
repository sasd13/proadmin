package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.net.ConnectivityChecker;
import com.sasd13.androidex.session.Session;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.ws.task.ReadTask;
import com.sasd13.proadmin.ws.task.UpdateTask;

public class SettingActivity extends MotherActivity implements IRefreshable {

    private class FormTeacherViewHolder {
        public TextView textViewNumber;
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private View viewFormTeacher, viewLoad;
    private FormTeacherViewHolder formTeacher;

    private Teacher teacher;
    private ReadTask<Teacher> readTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        createSwitchableViews();
        createFormTeacher();
    }

    private void createSwitchableViews() {
        viewFormTeacher = findViewById(R.id.setting_view_formteacher);
        viewLoad = findViewById(R.id.setting_view_load);
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

        readTeacher();
    }

    private void readTeacher() {
        if (ConnectivityChecker.isActive(this)) {
            readTask = new ReadTask<>(this, Teacher.class);
            readTask.execute(Session.getId());
        } else {
            finish();

            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    getResources().getString(R.string.message_error_connectivity)
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_setting, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting_action_accept:
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

        if (ConnectivityChecker.isActive(this)) {
            UpdateTask<Teacher> updateTask = new UpdateTask<>(this, Teacher.class);
            updateTask.execute(teacher);
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    getResources().getString(R.string.message_error_connectivity)
            );
        }
    }

    private void editTeacherWithForm(Teacher teacher) {
        teacher.setFirstName(formTeacher.editTextFirstName.getText().toString().trim());
        teacher.setLastName(formTeacher.editTextLastName.getText().toString().trim());
        teacher.setEmail(formTeacher.editTextEmail.getText().toString().trim());
    }

    @Override
    public void displayLoad() {
        switchToLoadView(true);
    }

    private void switchToLoadView(boolean switched) {
        if (switched) {
            viewLoad.setVisibility(View.VISIBLE);
            viewFormTeacher.setVisibility(View.GONE);
        } else {
            viewFormTeacher.setVisibility(View.VISIBLE);
            viewLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayContent() {
        try {
            teacher = readTask.getContent()[0];

            Cache.keep(teacher);

            fillFormTeacher();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        switchToLoadView(false);
    }

    private void fillFormTeacher() {
        formTeacher.textViewNumber.setText(teacher.getNumber());
        formTeacher.editTextFirstName.setText(teacher.getFirstName(), TextView.BufferType.EDITABLE);
        formTeacher.editTextLastName.setText(teacher.getLastName(), TextView.BufferType.EDITABLE);
        formTeacher.editTextEmail.setText(teacher.getEmail(), TextView.BufferType.EDITABLE);
    }

    @Override
    public void displayNotFound() {
        switchToLoadView(false);
    }
}