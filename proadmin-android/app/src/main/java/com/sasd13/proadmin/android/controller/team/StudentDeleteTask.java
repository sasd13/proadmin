package com.sasd13.proadmin.android.controller.team;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentDeleteTask extends RequestorTask {

    private TeamController controller;
    private IStudentService service;

    public StudentDeleteTask(TeamController controller, IStudentService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.delete((List<StudentTeam>) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onDeleteStudentTeams();
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
