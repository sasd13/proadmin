package com.sasd13.proadmin.android.controller.setting;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.update.TeacherUpdate;
import com.sasd13.proadmin.android.service.ITeacherService;
import com.sasd13.proadmin.android.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeacherUpdateTask extends RequestorTask {

    private SettingController controller;
    private ITeacherService service;

    public TeacherUpdateTask(SettingController controller, ITeacherService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.update((TeacherUpdate) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onUpdateTeacher();
        } else {
            controller.onFail(result.getHttpStatus(), result.getErrors());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
