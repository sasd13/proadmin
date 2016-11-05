package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.form.FormException;
import com.sasd13.proadmin.form.SettingsForm;
import com.sasd13.proadmin.service.IManageServiceCaller;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.ws.WSConstants;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

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

    public void updateTeacher(Teacher teacherToUpdate, SettingsForm settingsForm) {
        try {
            teacher = new Teacher();

            Binder.bind(teacher, teacherToUpdate);
            Binder.bind(teacher, settingsForm.getEditable());

            updateTask = new UpdateTask<>(WSResources.URL_WS_TEACHERS, this);

            updateTask.setTimeout(WSConstants.DEFAULT_TIMEOUT);
            updateTask.execute(teacher);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onSuccess() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            handleErrors(updateTask.getResponseErrors());
        } else {
            serviceCaller.onUpdateSucceeded(teacher);
        }
    }

    private void handleErrors(List<String> errors) {
        EnumError error = EnumError.find(Integer.parseInt(errors.get(0)));

        serviceCaller.onError(EnumErrorRes.find(error).getStringRes());
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
