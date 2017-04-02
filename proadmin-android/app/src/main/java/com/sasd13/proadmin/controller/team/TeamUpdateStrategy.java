package com.sasd13.proadmin.controller.team;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class TeamUpdateStrategy extends RequestorStrategy<TeamUpdateWrapper, Void> {

    private TeamController controller;
    private ITeamService service;

    public TeamUpdateStrategy(TeamController controller, ITeamService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Void doInBackgroung(TeamUpdateWrapper[] in) {
        service.update(in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Void out) {
        super.onPostExecute(out);

        //controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onCancelled(Void out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
