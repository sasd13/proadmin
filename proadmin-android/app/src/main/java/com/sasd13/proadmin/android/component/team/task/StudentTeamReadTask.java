package com.sasd13.proadmin.android.component.team.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.team.controller.TeamController;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class StudentTeamReadTask extends RequestorTask {

    private TeamController controller;
    private IStudentTeamService service;

    public StudentTeamReadTask(TeamController controller, IStudentTeamService service) {
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

        ServiceResult<List<StudentTeam>> result = (ServiceResult<List<StudentTeam>>) out;

        if (result.isSuccess()) {
            controller.onReadStudentTeams(result.getData());
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
