package com.sasd13.proadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.util.NetworkHelper;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.ws.task.ReadTask;
import com.sasd13.proadmin.ws.task.UpdateTask;

public class SettingActivity extends MotherActivity implements Promise {

    private static class FormTeacherViewHolder {
        TextView textViewNumber;
        EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private View viewLoad, viewFormTeacher;
    private FormTeacherViewHolder formTeacherViewHolder;

    private ReadTask<Teacher> readTaskTeacher;
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
        if (NetworkHelper.isConnected(this)) {
            long teacherId = SessionHelper.getExtraIdFromSession(Extra.TEACHER_ID);

            readTaskTeacher = new ReadTask<>(this, Teacher.class);
            readTaskTeacher.execute(teacherId);
        } else {
            NetworkHelper.displayMessageNotConnected(this);
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
            OptionDialog.showOkDialog(
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
        editTeacherWithForm();

        if (NetworkHelper.isConnected(this)) {
            UpdateTask<Teacher> updateTask = new UpdateTask<>(this, Teacher.class);
            updateTask.execute(teacher);
        } else {
            NetworkHelper.displayMessageNotConnected(this);
        }
    }

    private void editTeacherWithForm() {
        teacher.setFirstName(formTeacherViewHolder.editTextFirstName.getText().toString().trim());
        teacher.setLastName(formTeacherViewHolder.editTextLastName.getText().toString().trim());
        teacher.setEmail(formTeacherViewHolder.editTextEmail.getText().toString().trim());
    }

    @Override
    public void onLoad() {
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
    public void onSuccess() {
        try {
            teacher = readTaskTeacher.getResults().get(0);

            fillFormTeacherViewHolder();
            Cache.keep(teacher);
        } catch (IndexOutOfBoundsException e) {
            OptionDialog.showOkDialog(
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
    public void onFail() {
        switchToLoadView(false);
    }
}