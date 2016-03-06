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
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.constant.Extra;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.handler.SessionHandler;
import com.sasd13.proadmin.pattern.command.ILoader;
import com.sasd13.proadmin.ws.task.LoaderReadTask;
import com.sasd13.proadmin.ws.task.UpdateTask;

public class SettingActivity extends MotherActivity implements ILoader {

    private static class FormTeacherViewHolder {
        TextView textViewNumber;
        EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private View viewLoad, viewFormTeacher;
    private FormTeacherViewHolder formTeacherViewHolder;

    private LoaderReadTask<Teacher> readTask;
    private Teacher teacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        createSwitchableViews();
        createFormTeacherViewHolder();
    }

    private void createSwitchableViews() {
        viewLoad = findViewById(R.id.setting_view_load);
        viewFormTeacher = findViewById(R.id.setting_view_formteacher);
    }

    private void createFormTeacherViewHolder() {
        formTeacherViewHolder = new FormTeacherViewHolder();
        formTeacherViewHolder.textViewNumber = (TextView) findViewById(R.id.setting_form_user_textview_number);
        formTeacherViewHolder.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        formTeacherViewHolder.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        formTeacherViewHolder.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    @Override
    protected void onStart() {
        super.onStart();

        readTeacher();
    }

    private void readTeacher() {
        if (ConnectivityChecker.isActive(this)) {
            long teacherId = SessionHandler.getExtraIdFromSession(Extra.TEACHER_ID);

            readTask = new LoaderReadTask<>(this, Teacher.class, this);
            readTask.execute(teacherId);
        } else {
            ConnectivityChecker.showNotActive(this);
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
        teacher.setFirstName(formTeacherViewHolder.editTextFirstName.getText().toString().trim());
        teacher.setLastName(formTeacherViewHolder.editTextLastName.getText().toString().trim());
        teacher.setEmail(formTeacherViewHolder.editTextEmail.getText().toString().trim());
    }

    @Override
    public void doInLoad() {
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
    public void doInCompleted() {
        try {
            teacher = readTask.getResults().get(0);

            fillFormTeacherViewHolder();
            Cache.keep(teacher);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.title_error),
                    "Erreur de chargement des données"
            );
        }

        switchToLoadView(false);
    }

    private void fillFormTeacherViewHolder() {
        formTeacherViewHolder.textViewNumber.setText(teacher.getNumber());
        formTeacherViewHolder.editTextFirstName.setText(teacher.getFirstName(), TextView.BufferType.EDITABLE);
        formTeacherViewHolder.editTextLastName.setText(teacher.getLastName(), TextView.BufferType.EDITABLE);
        formTeacherViewHolder.editTextEmail.setText(teacher.getEmail(), TextView.BufferType.EDITABLE);
    }

    @Override
    public void doInError() {
        switchToLoadView(false);
    }
}