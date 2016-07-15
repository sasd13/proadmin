package com.sasd13.proadmin.content.handler;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.NetworkHelper;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.SettingsActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.ws.task.ReadTask;
import com.sasd13.proadmin.ws.task.UpdateTask;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public class SettingsHandler implements Promise {

    private SettingsActivity settingsActivity;
    private ReadTask<Teacher> readTaskTeacher;
    private WaitDialog waitDialog;

    public SettingsHandler(SettingsActivity settingsActivity) {
        this.settingsActivity = settingsActivity;
        waitDialog = new WaitDialog(settingsActivity);
    }

    public Teacher readTeacher() {
        Teacher teacher = null;

        if (NetworkHelper.isConnected(settingsActivity)) {
            long teacherId = SessionHelper.getExtraIdFromSession(settingsActivity, Extra.TEACHER_ID);

            readTaskTeacher = new ReadTask<>(this, Teacher.class);
            readTaskTeacher.execute(teacherId);
        } else {
            NetworkHelper.displayMessageNotConnected(settingsActivity);
        }

        return teacher;
    }

    @Override
    public void onLoad() {
        waitDialog.show();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }

    public void updateTeacher(Teacher teacher) {
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
}
