package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.service.ITeamService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeamReadStrategy extends ReadRequestorStrategy<Void, List<Team>> {

    private TeamController controller;
    private ITeamService service;

    public TeamReadStrategy(TeamController controller, ITeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public List<Team> doInBackgroung(Void[] voids) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(List<Team> out) {
        super.onPostExecute(out);

        controller.onReadTeams(out);
    }

    @Override
    public void onCancelled(List<Team> out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
