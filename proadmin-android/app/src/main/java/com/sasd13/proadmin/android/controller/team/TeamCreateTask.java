package com.sasd13.proadmin.android.controller.team;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.service.v1.ITeamService;
import com.sasd13.proadmin.android.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeamCreateTask extends RequestorTask {

    private TeamController controller;
    private ITeamService service;

    public TeamCreateTask(TeamController controller, ITeamService service) {
        super();

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
