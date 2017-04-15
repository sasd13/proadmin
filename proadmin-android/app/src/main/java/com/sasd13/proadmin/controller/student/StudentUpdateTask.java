package com.sasd13.proadmin.controller.student;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentUpdateTask extends RequestorTask {

    private StudentController controller;
    private IStudentService service;

    public StudentUpdateTask(StudentController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.update((StudentUpdateWrapper) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onUpdateStudent();
        } else {
            controller.display(EnumErrorRes.find(((ServiceResult) out).getHttpStatus()).getResID());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
