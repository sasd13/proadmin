package com.sasd13.proadmin.android.component.team.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.team.controller.TeamController;
import com.sasd13.proadmin.android.model.Team;
import com.sasd13.proadmin.android.service.ITeamService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeamReadTask extends RequestorTask {

    private TeamController controller;
    private ITeamService service;

    public TeamReadTask(TeamController controller, ITeamService service) {
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
