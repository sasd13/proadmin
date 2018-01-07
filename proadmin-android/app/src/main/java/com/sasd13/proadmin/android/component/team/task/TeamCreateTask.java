package com.sasd13.proadmin.android.component.team.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.team.controller.TeamController;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.service.ITeamService;
import com.sasd13.proadmin.android.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeamCreateTask extends RequestorTask {

    private TeamController controller;
    private ITeamService service;

    public TeamCreateTask(TeamController controller, ITeamService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.create((Team) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onCreateTeam();
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
