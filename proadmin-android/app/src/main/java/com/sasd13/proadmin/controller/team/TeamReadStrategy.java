package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.service.ITeamService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeamReadStrategy extends ReadRequestorStrategy {

    private TeamController controller;
    private ITeamService service;

    public TeamReadStrategy(TeamController controller, ITeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.onReadTeams((List<Team>) out);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
