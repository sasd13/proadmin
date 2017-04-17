package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.javaex.net.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class RunningTeamReadTask extends ReadRequestorTask {

    private ReportController controller;
    private IRunningTeamService service;

    public RunningTeamReadTask(ReportController controller, IRunningTeamService service) {
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

        ServiceResult<List<RunningTeam>> result = (ServiceResult<List<RunningTeam>>) out;

        if (result.isSuccess()) {
            controller.onReadRunningTeams(result.getData());
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
