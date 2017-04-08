package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeamUpdateTask extends RequestorTask {

    private TeamController controller;
    private ITeamService service;

    public TeamUpdateTask(TeamController controller, ITeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object in) {
        return service.update((TeamUpdateWrapper) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onUpdateTeam();
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
