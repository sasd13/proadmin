package com.sasd13.proadmin.controller.setting;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.javaex.net.EnumHttpHeader;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeacherReadTask extends ReadRequestorTask {

    private SettingController controller;
    private ITeacherService service;

    public TeacherReadTask(SettingController controller, ITeacherService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<List<Teacher>> result = (ServiceResult<List<Teacher>>) out;

        if (result.isSuccess()) {
            controller.onReadTeacher(result.getData().get(0));
        } else {
            controller.onFail(result.getHttpStatus(), result.getHeaders().get(EnumHttpHeader.RESPONSE_ERROR.getName()));
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
