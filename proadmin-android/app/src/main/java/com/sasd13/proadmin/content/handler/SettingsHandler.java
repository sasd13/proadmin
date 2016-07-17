package com.sasd13.proadmin.content.handler;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.proadmin.SettingsActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.content.form.FormException;
import com.sasd13.proadmin.content.form.SettingsForm;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.ws.task.ReadTask;
import com.sasd13.proadmin.ws.task.UpdateTask;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public class SettingsHandler implements Promise {

    private SettingsActivity settingsActivity;
    private boolean isActionRead;
    private ReadTask<Teacher> readTaskTeacher;
    private WaitDialog waitDialog;

    public SettingsHandler(SettingsActivity settingsActivity) {
        this.settingsActivity = settingsActivity;
    }

    public void readTeacher() {
        isActionRead = true;

        long teacherId = SessionHelper.getExtraIdFromSession(settingsActivity, Extra.TEACHER_ID);

        readTaskTeacher = new ReadTask<>(this, Teacher.class);
        readTaskTeacher.execute(teacherId);
    }

    @Override
    public void onLoad() {
        if (isActionRead) {
            waitDialog = new WaitDialog(settingsActivity);
            waitDialog.show();
        }
    }

    @Override
    public void onSuccess() {
        if (isActionRead) {
            isActionRead = false;

            onReadTaskSucceeded();
        } else {
            onUpdateTaskSucceeded();
        }
    }

    private void onReadTaskSucceeded() {
        try {
            Teacher teacher = readTaskTeacher.getResults().get(0);

            Cache.keep(teacher);
            waitDialog.dismiss();
            settingsActivity.onReadSucceeded(teacher);
        } catch (IndexOutOfBoundsException e) {
            settingsActivity.onError("");
        }
    }

    private void onUpdateTaskSucceeded() {
        settingsActivity.onUpdateSucceeded();
    }

    @Override
    public void onFail() {
        if (isActionRead) {
            isActionRead = false;

            settingsActivity.onError("Echec lors de la tentative de récupération des données");
        } else {
            settingsActivity.onError("Echec lors de la tentative de mise à jour des données");
        }
    }

    public void updateTeacher(Teacher teacher, SettingsForm settingsForm) {
        try {
            editTeacherWithForm(teacher, settingsForm);

            UpdateTask<Teacher> updateTask = new UpdateTask<>(this, Teacher.class);
            updateTask.execute(teacher);
        } catch (FormException e) {
            settingsActivity.onError(e.getMessage());
        }
    }

    private void editTeacherWithForm(Teacher teacher, SettingsForm settingsForm) throws FormException {
        Teacher teacherFromForm = settingsForm.getEditable();

        teacher.setFirstName(teacherFromForm.getFirstName());
        teacher.setLastName(teacherFromForm.getLastName());
        teacher.setEmail(teacherFromForm.getEmail());
    }
}
