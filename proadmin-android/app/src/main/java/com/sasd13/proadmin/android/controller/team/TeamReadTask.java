package com.sasd13.proadmin.android.controller.team;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.service.v1.ITeamService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeamReadTask extends ReadRequestorTask {

    private TeamController controller;
    private ITeamService service;

    public TeamReadTask(TeamController controller, ITeamService service) {
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

        ServiceResult<List<Team>> result = (ServiceResult<List<Team>>) out;

        if (result.isSuccess()) {
            controller.onReadTeams(result.getData());
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
