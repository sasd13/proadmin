package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;

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
    public Object doInBackgroung(Object in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onReadTeams(((ServiceResult<List<Team>>) out).getResult());
        } else {
            controller.display(EnumErrorRes.find(((ServiceResult) out).getHttpStatus()).getStringRes());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
