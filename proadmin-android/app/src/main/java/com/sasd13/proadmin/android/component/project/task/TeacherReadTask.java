package com.sasd13.proadmin.android.component.project.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.project.controller.ProjectController;
import com.sasd13.proadmin.android.model.Teacher;
import com.sasd13.proadmin.android.service.ITeacherService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeacherReadTask extends RequestorTask {

    private ProjectController controller;
    private ITeacherService service;

    public TeacherReadTask(ProjectController controller, ITeacherService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.read((Map<String, Object>) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<List<Teacher>> result = (ServiceResult<List<Teacher>>) out;

        if (result.isSuccess()) {
            controller.onReadTeacher(result.getData().get(0));
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
