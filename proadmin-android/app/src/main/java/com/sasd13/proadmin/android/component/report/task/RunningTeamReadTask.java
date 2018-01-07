package com.sasd13.proadmin.android.component.report.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.report.controller.ReportController;
import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamReadTask extends RequestorTask {

    private ReportController controller;
    private IRunningTeamService service;

    public RunningTeamReadTask(ReportController controller, IRunningTeamService service) {
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

        ServiceResult<List<RunningTeam>> result = (ServiceResult<List<RunningTeam>>) out;

        if (result.isSuccess()) {
            controller.onReadRunningTeams(result.getData());
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
