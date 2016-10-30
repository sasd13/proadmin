package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.IWSPromise;
import com.sasd13.androidex.ws.rest.task.ReadTask;
import com.sasd13.androidex.ws.rest.task.UpdateTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.SettingsActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.form.FormException;
import com.sasd13.proadmin.form.SettingsForm;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public class SettingsService implements IWSPromise {

    private SettingsActivity settingsActivity;
    private boolean isActionRead;
    private ReadTask<Teacher> readTaskTeacher;

    public SettingsService(SettingsActivity settingsActivity) {
        this.settingsActivity = settingsActivity;
    }

    public void readTeacher(String number) {
        isActionRead = true;

        readTaskTeacher = new ReadTask<>(WSResources.URL_WS_TEACHERS, this, Teacher.class);
        readTaskTeacher.addParameter(EnumParameter.NUMBER.getName(), number);
    }

    public Teacher readTeacherFromCache(String number) {
        return null;
    }

    public void updateTeacher(Teacher teacher, SettingsForm settingsForm) {
        try {
            Binder.bind(teacher, settingsForm.getEditable());

            UpdateTask<Teacher> updateTask = new UpdateTask<>(WSResources.URL_WS_TEACHERS, this);
            updateTask.execute(teacher);
        } catch (FormException e) {
            settingsActivity.onError(e.getResMessage());
        }
    }

    @Override
    public void onLoad() {
        if (isActionRead) {
            settingsActivity.onLoad();
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

            settingsActivity.onReadSucceeded(teacher);
        } catch (IndexOutOfBoundsException e) {
            settingsActivity.onError(R.string.ws_error_data_retrieval_error);
        }
    }

    private void onUpdateTaskSucceeded() {
        settingsActivity.onUpdateSucceeded();
    }

    @Override
    public void onFail(int httpResponseCode) {
        settingsActivity.onError(R.string.ws_error_server_connection_failed);
    }
}
