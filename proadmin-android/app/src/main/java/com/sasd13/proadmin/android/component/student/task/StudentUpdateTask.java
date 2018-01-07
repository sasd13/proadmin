package com.sasd13.proadmin.android.component.student.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.student.controller.StudentController;
import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentUpdateTask extends RequestorTask {

    private StudentController controller;
    private IStudentService service;

    public StudentUpdateTask(StudentController controller, IStudentService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.update((Student) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onUpdateStudent();
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
