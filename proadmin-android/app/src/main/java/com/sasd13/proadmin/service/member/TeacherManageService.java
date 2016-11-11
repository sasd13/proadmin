package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.gui.form.SettingsForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.member.TeacherBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.member.ITeacherUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.wrapper.read.member.ITeacherReadWrapper;

/**
 * Created by ssaidali2 on 15/07/2016.
 */
public class TeacherManageService implements IHttpCallback {

    private IManageServiceCaller<Teacher> serviceCaller;
    private UpdateTask<Teacher> updateTask;
    private Teacher teacher;

    public TeacherManageService(IManageServiceCaller<Teacher> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void updateTeacher(SettingsForm settingsForm, String number) {
        try {
            teacher = getTeacherToUpdate(settingsForm, number);
            updateTask = new UpdateTask<>(WSResources.URL_WS_TEACHERS, this);

            updateTask.execute(teacher);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private ITeacherUpdateWrapper getTeacherUpdateWrapper(SettingsForm settingsForm, Teacher teacher) {
        ITeacherUpdateWrapper teacherUpdateWrapper = new TeacherUpdateWrapper();

        teacherUpdateWrapper.setWrapped(getTeacherToUpdate(settingsForm, teacher));
    }

    private Teacher getTeacherToUpdate(SettingsForm settingsForm) throws FormException {
        Teacher teacherToUpdate = new TeacherBaseBuilder(settingsForm.getNumber()).build();

        teacherToUpdate.setFirstName(settingsForm.getFirstName());
        teacherToUpdate.setLastName(settingsForm.getLastName());
        teacherToUpdate.setEmail(settingsForm.getEmail());

        return teacherToUpdate;
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, updateTask.getResponseErrors());
        } else {
            serviceCaller.onUpdateSucceeded(teacher);
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
