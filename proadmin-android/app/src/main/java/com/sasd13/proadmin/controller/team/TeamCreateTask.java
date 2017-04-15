package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;

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

        if (((ServiceResult) out).isSuccess()) {
            controller.onCreateTeam();
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
